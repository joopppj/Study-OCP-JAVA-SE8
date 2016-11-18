
import java.io.File;
import java.io.IOException;

public class fileAndDirectory{
	public static void main(String[] args) throws IOException{
		File file= new File("/Users/kira/Documents/workspace/IO/text.txt");
		if(file.createNewFile()){       // create a file in specific directory 
			System.out.println("success");
		} else{
			System.out.println("fail");
		}
		File file1= new File("/Users/kira/Documents/workspace/IO/ff.rtf");
		System.out.println(file1.exists());  // test if a file exists on that directory
		System.out.println(System.getProperty("user.dir")); // get the current directory
		File file2= new File("/Users/kira/Documents/workspace/IO");	 //if the File is a directory , we can use listFiles to get all names of subfiles.
		if(file2.exists()){
			if(file2.isDirectory()){
				for(File subfile:file2.listFiles()){
					System.out.println(subfile.getName());
				}
			}
		}
	}
}
