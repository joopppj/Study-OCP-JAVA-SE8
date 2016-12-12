package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class PresentingNewStreamMethods{
	public static void main(String[] args){
		// After java se8 , we can use stream to make many complex operation simpler , Files.walk() can return a stream<Path> which traverse the directory in DFS. 
		Path path1=Paths.get("zzz/..");
		try {
			Files.walk(path1).filter(p->p.toString().endsWith("2")).forEach(System.out::println); // here we just print out all file has a name ends with '2' in current directory 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// by using this approach , we can easily get all specific type file under a specific directory, (EX, we can just change the "2" to ".java" if we want to get all java files under the current directory 
		// there is another overloaded version of walk() which takes another int parameter (the max directory depth), this is useful in practice because we can stop JVM from searching too deep
		
		// we can also use find() which works similarly as walk() works, except for it includes max directory depth and a bipredicate as parameters
		try {
			Stream<Path> stream=Files.find(path1, 10, (p,a)-> path1.toString().endsWith("2")&&a.lastModifiedTime().toMillis()>10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// list() method can list all files and directories in current path 
		try {
			Files.list(path1).filter(p->!Files.isDirectory(p)).map(p->p.toAbsolutePath()).forEach(System.out::println); // here we find out all files under this directory  then print out their absolute paths
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this method is very similar to listFiles() method except for it relies on stream.
		
		Path path2= Paths.get("zz2");
		// Files.readAllLines() cannot be used on very large file , otherwise it will cause OutOfMemory Error problem
		// so we need another method called lines to deal with big file 
		try {
			Files.lines(path2).forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// in face we can use many lambda and stream operation to do very complex operation on this file simply
		
		// below is the chart which includes relative io methods and nio.2 methods
		/*  file.exist()    ___     Files.exists(path)
		 *  file.getName()   ___    path.getFileName()
		 *  file.getAbsolutePath()  ___    path.toAbsolutePath()
		 *  file.isDirectory()    ___  Files.isDirectory(path)
		 *  file.isFile()    ___   Files.isRegularFile(path)
		 *  file.isHidden()    ___  Files.isHidden(path)
		 *  file.length()      ___     Files.size(path)
		 *  file.lastModified()       ___     Files.getLastModifiedTime(path)
		 *  file.setLastModified()    ___    Files.setLastModifiedTime(path,fileTime)
		 *  file.delete()    ___     Files.delete()
		 *  file.renameTo(otherFile)    ___    Files.move(path, otherpath)
		 *  file.mkdir()      ___      Files.createDirectory(path)
		 *  file.mkdirs()      ___       Files.createDirectories(path)
		 *  file.listFiles     ___      Files.list(path)
		 */
		 
	}
}