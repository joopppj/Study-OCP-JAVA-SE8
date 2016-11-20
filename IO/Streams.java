package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

public class Streams{
	public static void main(String[] args){
	
		// there are high/low level streams.
		// low level stream connects directly  with source of data, such as FileInputStream and FileReader
		// high level stream is built on the top of low/high level stream, such as BufferedReader and ObjectInputStream
		try(BufferedReader bufferedReader=new BufferedReader(new FileReader("/Users/kira/Documents/workspace/IO/text.txt"))){  // this is a good example how high level stream warp low-level stream
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// but there are some rules when wrapping
		// new BufferedInputStream(new FileOutputStream())  does not compile since we cannot mix input and output
		// new BufferedInputStream(new FileReader()) does not compile since we cannot mix input stream/ output stream with reader/writer
		// new BufferedInputStream(new InputStream()) does not compile since we cannot instantiate an instance of abstract class
		
		// mark() and reset() are two important method for input and reader type stream , they are like save and load features in game, which means mark() can save stream's state , while reset() can load stream's state.
		// but not all of those streams support mark() and reset() , but we can use markSupported() to check if mark() is supported 
		// suppose we have a stream whose next values are abcde
		try {
			BufferedReader fr= new BufferedReader(new FileReader("/Users/kira/Documents/workspace/IO/text.txt"));
			System.out.println((char)fr.read());
			if(fr.markSupported()){  // check if this stream support mark();
				fr.mark(0);
				System.out.print((char)fr.read());  // print b
				System.out.print((char)fr.read());  // print c
				fr.reset();						// go back to state of line 38
			}
			System.out.print((char)fr.read());  // print b
			System.out.print((char)fr.read());	// print c
			System.out.print((char)fr.read());  // print d
			System.out.print((char)fr.read());  // print e
			fr.close();// remember to close stream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// skip() is another important method for stream, i can skip certain number of byte in the source when reading data from stream
		try {
			InputStream rr= new FileInputStream("/Users/kira/Documents/workspace/IO/text.txt");
			System.out.println("\n"+(char)rr.read());
			rr.skip(2);		// it skips b and c now
			System.out.print((char)rr.read());  
			System.out.print((char)rr.read());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// skip() and read() are similar, but skip() is faster when skip many data since it uses array to skip data
	}
}