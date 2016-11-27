package test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class WorkWithStream{
	// FileInputStream and FileOutputStream are most basic file stream, which read bytes from file / write bytes to a file
	// when use read() on FileInputStream , when it returns -1 , it means the end of file is reached 
	// when call read() or use write, it returns int insdead of byte/ take int as parameter 
	public static void copy(File source, File destination) throws IOException{
		try(
			InputStream in= new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination))
		{ int b;
		while((b= in.read())!=-1){      // read one byte at a time until it read end of file
			out.write(b);       // write one byte to out at a time
		}
		}		
	}
	// this method may not perform very fast because it does not use byte arrays. To get better performance , we need bufferStream
	public static void copy1(File source, File destination) throws IOException{
		try(
			InputStream in= new BufferedInputStream(new FileInputStream(source));		// use buffer stream 
			OutputStream out = new BufferedOutputStream(new FileOutputStream(destination)))
		{ byte[] buffer = new byte[1024];
		int lengthRead;
		while((lengthRead= in.read(buffer))>0){      // read 1024 byte(at most) at a time until it read end of file
			out.write(buffer,0,lengthRead);       // write 1024 byte(at most) to out at a time
			out.flush();     // make sure the data is written to disk
		}
		}		
	}
	
	// unlike input/output stream, reader and writer read/write char instead of bytes
	public static List<String> readFile(File source) throws IOException{
		List<String> data= new ArrayList<String>();
		try(BufferedReader reader=new BufferedReader(new FileReader(source))){
			String s;
			while((s=reader.readLine())!=null){
				data.add(s);
			}
		}
		return data;
		
	}
	public static void writeFile(List<String> data, File destination)throws IOException{
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(destination))){
			for(String s:data){
				writer.write(s);
				writer.newLine();
			}
		}
	}
	public static void main(String[] args) throws IOException{
		
		File source=new File("x.txt");
		File destination=new File("y.txt");
		List<String> data= readFile(source);
		for(String record:data){
			System.out.println(record);
		}
		writeFile(data,destination);
		// print stream and PrintWriter can write object to text-based out put stream , they are extremely convenient to use in practice 
		// a good sample PrintWriter application can look like this:
		try(PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(datafile)))){
			out.print("game start");
			out.println();
			out.printf("game over");
		}
	}
	
}
