abstract class Animal{
	String name="???";
	public void printName(){
		System.out.println(name);
	}
	public void pet(){
		System.out.println("pet a animal");
	}
}
class lion extends Animal{
	String name="lion";
	public void printName(){
		System.out.println(name);
	}
	public void pet(){
		System.err.println("you cannot pet a lion");
	}
}

public class Basics{
      final class parent{}   // use final on class mean this class cannot be subclassed 
      /*class son extends parent{        this will cause complie error	  
      }*/
      
      public static void main(String[] args){
    	  String ss= "s";
    	  System.out.println(ss instanceof String); // instanceof can check if an instance is a specific type 
    	  // System.out.println(ss instanceof StringBuilder);  if the instance is not related to the type at all , it will throw complie error.
      
    	  Animal lion=new lion();    
    	  lion.pet();					// when you call a method on an object, it will execute the overridden method if exists.
    	  lion.printName();		// but when you call an variable  on an obeject, it will always use the variable of reference type rather than instance type
    	 
      }
}
