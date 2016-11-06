package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TryResources {
	//A resource can be file , database....
	//we need to ensure every resource be close after finishing using them
	//Java SE7 introduce a new approach called try-with-resources which can save this step 
	public static void sampleMethod(Path path1,Path path2) throws IOException{
		try(BufferedReader in =Files.newBufferedReader(path1);
		BufferedWriter out =Files.newBufferedWriter(path2)){
			out.write(in.readLine());
		}
	}
	// we do not need to write catch or finally blocks, they are added implicitly
	// we list all resources in the try clause 
	// all resources will be closed automatically 
	
	// we can also add catch/final block after 
	public static void sampleMethod2(Path path1,Path path2) throws IOException{
		try(BufferedReader in =Files.newBufferedReader(path1);
		BufferedWriter out =Files.newBufferedWriter(path2)){
			out.write(in.readLine());
		}catch(IOException e){
			
		}finally{
			
		}
			
	}
	
	// but there is one worth noticing: the resources created in try block will not exist in 
	// catch or finally block, since those sources are already closed in implicit finally block
	public static void main(String[] args){
	// but when using try-with-resources, we cannot put anything not closeable in try clause
	/*try(String wrongObject= new String()){
				System.out.println("you will never see this ");
			}*/
	
	// actually only class which implements AutoCloseable can be put in try clause
	
	// now we create a closeable object which implements AutoCloseable
	class closeable implements  AutoCloseable{
		public void close(){
			System.out.print("you can see this ");
		}
	}
	try(closeable right=new closeable()){
		
		}
	
	// when exception is thrown in closeable class we can use catch block to deal with it
	// if multiple exceptions are thrown , we use catch block to deal with first one and use getsurpressed() to handle rest
	class closeable1 implements  AutoCloseable{
		public void close(){
			throw new IllegalStateException("second exception");

		}
	}
	try(closeable1 x=new closeable1()){
		throw new IllegalStateException("first exception");
	}catch(IllegalStateException e){
		System.out.println(e.getMessage());
		for(Throwable t: e.getSuppressed()){
			System.out.println(t.getMessage());
		}
	}
	
	// when finally also throw exceptions, the suppressed exceptions collected on try will be lost!
	try(closeable1 x=new closeable1()){
		throw new IllegalStateException("suppressed exception");
	}finally{
		throw new IllegalStateException("this one will be actually thrown");// now the suppressed exception is lost 
	}
	
	/*try(closeable1 x=new closeable1();closeable1 y=new closeable1()){
		throw new IllegalStateException("suppressed exception");
	}catch(Exception e){
		System.out.println("ex");
	}
	finally{
		System.out.println("finally");// now the suppressed exception is lost 
	}*/
	}
	
}
