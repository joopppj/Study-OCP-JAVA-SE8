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
//1 unbounded wildcards, by unbounded , we mean the generic can be any type.
public static void unboundedPrint(List<?> list){ //this methods can take any generic type arraylist as parameter.
	for(Object x: list)System.out.println(x);  
}
//2 upper-bounded wildcards 
public static long UpperBoundedSum(List<? extends Number> list){  // for example, this method can take any list that includes any types which extends Number
	long count=0;for(Number number:list)count+=number.longValue(); return count;
	// there is another interesting fact about upper-bounded and unbounded wildcards , a arrayList with upper-bounds wildcard is immutable 
}
//3 lower_bounded wildcards
public static void LowerBoundedPrint(List<? super String> list){ // for example this method can take any list that includes any types which are superclass of String
	for(Object x: list)System.out.println(x);
	//list.add(new Object()); this is not allowed since the generic type could be String and object can not fit in there 
}
// T <?> wrongMethod(List<? extends T> list){return new T() } we cannot use wildcards on return type/ initialize a generic type. 
// wrongMethod2(List<X extends T> list){} this is also wrong because wildcard must have ? in it. any other letters cannot replace it !
}
