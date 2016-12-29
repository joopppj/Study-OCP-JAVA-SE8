public class Basics{
      final class parent{}   // use final on class mean this class cannot be subclassed 
      /*class son extends parent{        this will cause complie error	  
      }*/
      
      public static void main(String[] args){
    	  String ss= "s";
    	  System.out.println(ss instanceof String); // instanceof can check if an instance is a specific type 
    	  // System.out.println(ss instanceof StringBuilder);  if the instance is not related to the type at all , it will throw complie error.
      }
}
