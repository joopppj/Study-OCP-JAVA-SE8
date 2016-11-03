package test;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class Localization{
	public static void main(String[] args){
		// create a locale according to location of programmer 
		Locale locale=Locale.getDefault();
		System.out.println(locale);
		//locale has two formats: language ; language_country 
		
		// we create language format with choosing language 
		// we create language_country format with choosing
		System.out.println(Locale.JAPAN); //ja_JP
		System.out.println(Locale.CHINESE); // zh
		
		// or we can use constructor to create locale
		System.out.println(new Locale("jp")); //ja_JP
		System.out.println(new Locale("jp","JP")); // jp_JP
		System.out.println(new Locale("invalid"));
		// pay attention: we can also use invalid string to create Locale object but this will cause potential problem since it cannot match other Locale objects
		
		// builder pattern can also help build locale object
		Locale l1=new Locale.Builder().setLanguage("jp").setRegion("JP").build();
		Locale l2=new Locale.Builder().setRegion("JP").setLanguage("jp").build();
		//builder pattern can even change lowercase to uppercase automatically, so somthing like setRegion("jp") is okay
		
		// sometimes we need to set default locale , this is also possible
		Locale.setDefault(new Locale("MOON","moonish")); 
		System.out.println(Locale.getDefault()); // output moon_MOONISH 
		
		// we can use resource bundle to get property files' properties , which is really cool!
		ResourceBundle rb= ResourceBundle.getBundle("test.Zoo_fr",new Locale("france"));
		System.out.println(rb.getString("hello"));
		
		// we can also collect all keys and use function programming here to print information one by one
		Set<String> keys =rb.keySet();
		keys.stream().map(k->k+" "+rb.getString(k)).forEach(System.out::println); 
		
		// we can also use object called properties to replace properties file
		Properties props= new Properties();
		// we transfer all information from resourceBundle to properties object
		rb.keySet().stream().forEach(k->props.setProperty(k, rb.getString(k)));
		// now let us use information included in the properties file
		System.out.println(props.getOrDefault("hello", "not found")); // output Bonjour
		System.out.println(props.getOrDefault("fakekey", "not found")); // prints us the default string
		System.out.println(props.getProperty("fakekey")); // get null if we do not provide default string
		
		// Let us try to use Zoo_jp.java to create resuorceBundle.
		ResourceBundle rb1= ResourceBundle.getBundle("test.Zoo_jp", Locale.JAPAN);
		System.out.println(rb1.getObject("hello")); // output こんにちは！
		System.out.println(rb1.getObject("tax")); // output a object other than String
		
	}
}