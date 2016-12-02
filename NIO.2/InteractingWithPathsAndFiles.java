package test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InteractingWithPathsAndFiles{
	public static void main(String[] args){
	//Path interface has three methods toString(), getNameCount() and getName() , which allow us get information about path
	Path path1= Paths.get("/Users/kira/Documents/workspace/NIO.2");
	System.out.println("the path name:"+path1);
	for(int i=0;i<path1.getNameCount();i++){  // this method provide number of components of this path 
		System.out.println("component"+i+": "+path1.getName(i)); 
	}
	}
	
}
