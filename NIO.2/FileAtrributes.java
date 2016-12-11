package test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

public class FileAtrributes{
	
	public static void main(String[] args) throws IOException{
		// File class includes three methods which can determine if it is a directory/regular file/symbolic link
		// there is one thing worth mentioning : if a symbolic points to a real file or directory, JVM will check the target of this link, so it is possible that an object can be both of symbolic and other types
		System.out.println(Files.isDirectory(Paths.get("zz2")));
		Path path2=Paths.get("zz2");
		System.out.println(Files.isRegularFile(Paths.get("zz2")));
		System.out.println(Files.isSymbolicLink(Paths.get("zz2")));
		
		// isHidden() can check if a file is hidden , (in linux/unix, the hidden file's name is always started with a dot .
		try {
			System.out.println(Files.isHidden(path2));   // since this file is not hidden , it will return false
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// the methods isExecutable() and isReadable() can determine if the user has permission to read/execute a file.
		System.out.println(Files.isExecutable(path2));
		System.out.println(Files.isReadable(path2));
		
		// note these methods above do not throw ioexception , but they can return false if the target file does not exist
	
		// size() method can return size of the file in bytes(but we cannot use it on directory)
		try {
			System.out.println(Files.size(Paths.get("zz2")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// getLastModifiedTime() and setLastModifiedTime() can tell/set us the time the file was edited last time
		try {
			System.out.println(Files.getLastModifiedTime(path2).toMillis());			
			Files.setLastModifiedTime(path2, FileTime.fromMillis(System.currentTimeMillis()));
			System.out.println(Files.getLastModifiedTime(path2).toMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// there is helper class called UserPrincipal which represent the user, we can create it by calling getUserPrincipalLookupService() on a file system
		try {
			UserPrincipal owner= FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("kira"); // it will throw exception if this user name is not found
			System.out.println(Files.getOwner(path2));
			Files.setOwner(path2, owner);// we can also change user by using setOwner.
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// we have use many methods to get/set different file attributes , but they can only get/set one by one time.
		// if we want get many attributes at one time, we need a view to do it, the reason why we need view is that this is far more efficient than get attribute one by one.
		// there are 3 kinds of attributes/view class, I only talk about BasicFileAttributes/BasicFileAttributeView here
		BasicFileAttributes data=Files.readAttributes(path2, BasicFileAttributes.class);
		System.out.println(data.isDirectory());
		System.out.println(data.isRegularFile()); // like this ,we can get many information by just one class
		System.out.println(data.isOther()); // isOther() determine if this path are not files/directories/symbolic links. such as path to devices
		System.out.println(data.fileKey()); // fileKey() return s unique value which represents the file in the file system
		
		// but attributes class can only read information , we need  a view class if we want edit information
		// in general we can only use view to modify the data/time information about a file.(since the other attributes are impossible to be changed)
		BasicFileAttributeView view=Files.getFileAttributeView(path2, BasicFileAttributeView.class);
		// we can also convert view to attributes
		BasicFileAttributes data1=view.readAttributes();
		// let us say we want to add 1 millisecond to the file's lastModifiedTime attribute.
		FileTime  lastModifiedTime= FileTime.fromMillis(data1.lastModifiedTime().toMillis()+1);
		view.setTimes(lastModifiedTime,null,null); // for those attribute we do not want to change , we leave nulls here.
	}
}