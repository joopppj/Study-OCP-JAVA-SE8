package test;

import java.util.*;
// in addition to create properties file , we can also create this .java profile
// this way has a benefit that we can use all java types as our values 
public class Zoo_jp extends ListResourceBundle{
	protected Object[][] getContents(){
		return new Object[][]{
			{"hello", "こんにちは！"},
			{"open" , "営業中"},
			{"tax", new Double(0.8)}
		};		
	}
}