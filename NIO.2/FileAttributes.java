import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAtrributes{
	
	public static void main(String[] args){
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
	}
}
