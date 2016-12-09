package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InteractingWithPathsAndFiles{
	public static void main(String[] args){
	//Path interface has three methods toString(), getNameCount() and getName() , which allow us get information about path
	Path path1= Paths.get("/Users/kira/Documents/workspace/NIO.2");
	System.out.println("the path name:"+path1);
	for(int i=0;i<path1.getNameCount();i++){  // this method provide number of components of this path 
		System.out.println("component"+i+": "+path1.getName(i)); 
	}
	
	// path interface also provides getFileName() , getFile() and getParent() which return related path objects.
	System.out.println(path1.getFileName()); // getFileName() returns the file/directory itself which does not include any parent directories
	System.out.println(path1.getRoot());  // returns root element of the path, or null if the path is relative 
	System.out.println(path1.getParent());  // returns the closest parent directory element of the path 
	
	Path path2= Paths.get("workspace/NIO.2");
	System.out.println(path2.getRoot());   // return null since path2 is relative path
	
	// Path also provides two methods for us to determine if it is a absolute path / to convert a relative path to absolute path 
	System.out.println(path1.isAbsolute()); // return true since path1 is absolute 
	System.out.println(path2.toAbsolutePath());  // give us a absolute version of path2 since path2 isrelative .
	// there is another thing worth mentioning ,  sometimes we can get a different result on different operting system
	
	// subpath() can help us to create a new path with elements inside the original path 
	System.out.println(path1.subpath(0, 2)); // we get a path includes first element and  second element of original path , in this case, Users/kira
	// pay attention , the index 0 is not root directory , it is the directory right after the root .
	// if we use a index that is invalid  , we will get an exception 
	//System.out.println(path1.subpath(0, 100)); // obviously 100 is out of range so we get exception
	//System.out.println(path1.subpath(1, 1)); // obviously 1,1 are conflicts  so we get exception
	
	// . and .. are path symbols that represent current directory and parent directory 
	
	// relativize() return a relative path from one path to another path .
	Path path3= Paths.get("zz");
	Path path4= Paths.get("zz2");
	System.out.println(path3.relativize(path4)); // since they are in same directory , we just get ../zz2
	// relativized() can also be used on two absolute path , but it can not be used on one relative path and an absolute path
	// System.out.println(path1.relativize(path2.toAbsolutePath())); // this will throw exception  
	// if we use this method on windows with two absolute path , we should ensure the root directories are same.
	
	// resolve() is interesting , it can append a path to another path 
	Path path5= Paths.get("zzz");
	System.out.println(path5.resolve(path3)); // now we get zzz/zz
	// there is one thing we need to know : if the parameter is absolute path , resolve() will return the parameter directly 
	System.out.println(path5.resolve(path1)); // return path1 directly 
	
	// normalize() method can help us to clean up the path symbols and redundaries, replace them by real directories name 
	System.out.println((path5.resolve(path4)).normalize());   
	
	// toRealPath() can verify if the path exist in system then , it also implicitly calls the method normalize()
	try {
		System.out.println(path3.toRealPath());    // we need try block since it ay throw exception
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//exist() can help us determine if this file exist
	System.out.println(Files.exists(Paths.get("zz")));   // zz exist so it return true , 
	System.out.println(Files.exists(Paths.get("NotExist")));  // this file not exist so return false 
	
	// isSameFile() can help use determine if the two path are actually same
	try {
		System.out.println(Files.isSameFile(path3, path4));     // return false since they are not same files
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// createDirectory() can help us create a directory , but it will throw exception if the elements in the parameter do not exist
	/*try {
		Files.createDirectory(Paths.get("new folder"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	// createDirectories() is very similar to createDirectory(), except for it can create many directories at one time 
	/*try {
		Files.createDirectories(Paths.get("new folder1"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	// copy() can help use to copy files/ directories to different locations 
	/*try {
		Files.copy(Paths.get("new folder"), Paths.get("new folder5"));  // now we copied a directory to another location 
		Files.copy(path4, Paths.get("/new folder5/ff.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	//move() has two functions : renaming and moving file/ directory 
	/*try {
		Files.move(Paths.get("zz2"), Paths.get("ff"));  // rename zz2 to ff
		Files.move(Paths.get("ff"), Paths.get("/")); // move ff to root
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	// delete() / deleteIfExist() can delete a file  
	try {
		Files.delete(Paths.get("ff"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// NIO2 also includes method for reading and writing file contents using java.io streams
	// for reading , we can use newBufferedReader from i/o, with parameter of a path :
	try(BufferedReader reader=Files.newBufferedReader(Paths.get("zz2"))){
		String currentLine=null; 
		while((currentLine=reader.readLine())!=null)System.out.println(currentLine);
	}catch(IOException e){
		
	}
			
	// for writing , we can use newBufferedWriter() from i/o, with parameter of a path:
	try(BufferedWriter writer=Files.newBufferedWriter(Paths.get("zz2"), Charset.forName("UTF-16"))){
		writer.write("hello world");   //  now we have wrote hello world to the target file  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// pay attention , this code will create a new file with the content to replace that file which already exists, but we can choose another overloaded version to appending content instead of overwriting it.
	
	//readAllLines() can help us read all content form a file to store into a list
	try{
		final List<String> lines= Files.readAllLines(Paths.get("zz2"));
		for(String line:lines){
			System.out.println(line);
		}
			
		
	}catch(IOException e){
		
	}
	// pay attention this method cannot handle large files other wise there will be an error.
	
}}