import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IntroducingNIO2{
	public static void main(String[] args){
		// path is similar to File object, but we need to use get static method to create the instance of it
		Path path1= Paths.get("/Users/kira/Documents/workspace/NIO.2");
		Path path2= Paths.get("Documents","workspace","NIO.2");// or we can use vararg of  string
		Path path3= Paths.get("file:///Users/kira/Documents/workspace/NIO.2");
		// the other get() method takes URI as parameter, but pay attention: relative path will thrrow exception
		try {
			Path path4=Paths.get(new URI("http://www.google.com"));  // we an also create an url Path object
			URI uri4= path4.toUri();    // we can also convert path back to url
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file= new File("/Users/kira/Documents/workspace/NIO.2");
		Path path6= file.toPath(); // wen can also update legacy code by toPath();
		File file2= path6.toFile(); // or convert path to file
		System.out.println(System.getProperty("user.dir"));
		System.out.println(path3.isAbsolute());
		
	}
}
