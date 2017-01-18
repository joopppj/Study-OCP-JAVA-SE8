package test;

import java.util.List;

// the syntax for introducing a generic is to declare a formal type parameter inangle brackets
// we can contain multiple generic values
 class crate<T,S>{
	private T contents;
	public T emptyCrate(){
		return contents;
	}
	public void packCrate(T contents){
		this.contents=contents;
	}
	//name conventions for generics
	/*E for an element
	 *K for a map key
	 *V for a map value
	 *N for a number
	 *T for generic data type
	 */
	// not only interface/class, generics can also be declared on method level
	public static <T> T genericMethod(T t){
		return t;
	}
	public static void  main(String[] args){
		String animal="animal";
		crate<String,String> CrateForString = new crate<>();
		String answer = CrateForString.emptyCrate();
		// we can get what we want without nothing anything about the class crate 
	}
}
 // interface can also use generics
public interface workingWithGenerics<T>{
}
// there are 3 ways a class can approach implementing an interface with generics
// first, the class has a specific generic type
class workingWithGenerics1<String> implements workingWithGenerics{
}
// second, the class also creates a generic type.
class workingWithGenerics2<T> implements workingWithGenerics{
}
// third, the class does not declare any generic type.
class workingWithGenerics3 implements workingWithGenerics{
}
// something generic types cannot do: 1. call new T() 2 create an array of T 3 call instanceof 4 Use primitive type as generic type parameter 5 create a static variable as a generic type parameter
// a bounded parameter type is an unknown generic that specify a bound for the generic, and there are 3 kinds of bound.
class bound{
//1 unbounded wildcard  , by unbounded , we mean the generic can be any type.
public static void unboundedPrint(List<?> list){ //this methods can take any generic type arraylist as parameter.
	for(Object x: list)System.out.println(x);  
}
      
}
