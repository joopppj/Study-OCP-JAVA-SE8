package test;

import java.io.*;
import java.util.Locale;

public class UsingConsole{
	public static void main(String[] args) throws IOException{
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	// System.in provides a input stream, InputStreamReader convert InputStream to reader , then bufferedReader allows using enter to terminate user input
		String userInput = reader.readLine();	  
		System.out.println("you entered: "+userInput);*/
		// code above is an old to interact with users
		// after java se6 there is a new class called Console which allows coders to interact with users in a simpler way
		Console console=System.console();
		if(console==null)throw new RuntimeException("console not avaliable");
		// format() or printf() can help us print message to users directly
		console.writer().format(new Locale("jp","JP"), "hello world"); // we can use custom locale to format our string
		if(console!=null){					// console may return null when environment is not text-based
			console.flush();// flush() method can make all buffered output to be written immediately 
			String input=console.readLine();
			char[] input1=console.readPassword();// readPassword() is almost same as readLine() except for user cannot see what they are typing and it returns char[] for secure reason
			// String inputWithNotice = notice.readLint("please enter your name ");  // this overloaded version of readline() can add message prior to accepting user's input
			char[] input2=console.readPassword();
			boolean match = input1.equals(input2);
			// we should clear password immediately after using them 
			for(int i=0;i<input1.length;i++){input1[i]='x';input2[i]='x';}
			console.writer().println("you wrote::"+input);
		}
		
		
		
	}
}