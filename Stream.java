package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class excercise{	
	public static void main(String[] args){	
		// create stream sources
		Stream<String> strings=Stream.empty();	//create an empty string stream
		
		Stream<Integer> s=Stream.of(1,1); 	//create a finite Integer stream with two 1s
		
		List<String> stringList= Arrays.asList("a","b");
		Stream<String> stringStream= stringList.stream();// create string stream by coverting list to stream
		Stream<String> stringStreamParallel= stringList.parallelStream();// create parallel stream
		
		Stream<Double> randoms=Stream.generate(Math::random);// create an infinite Double stream with random numbers
		Stream<Integer> natureNumbers = Stream.iterate(1,n->n+1);// create infinite Integer stream with all nature numbers
		
		// stream cannot be printed directly.
		System.out.println(s);// outputs ugly hash code
		
		// common terminal stream operation 
		//count() count elements in a finite stream
		Stream<String> a=Stream.of("");
		System.out.println(a.count()); //output 1
		
		//min()/max() find largest and smallest element in a finite stream with comparator and Optional
		Stream<Integer> b= Stream.of(1,2,3);
		Optional<Integer> min = b.min((s1,s2)->s1-s2);
		min.ifPresent(System.out::println);
		
		//findAny find if there is any elements then print it out
		//findFirst find the first elements then print it out
		Stream<String> c=Stream.generate(()->"a");
		c.findAny().ifPresent(System.out::println);   	//outputs a
		//c.findFirst().ifPresent(System.out::println); 		//should outputs a but only one terminal operation could be used, so exception
		
		//allMatch/anyMatch/noneMatch() find if all/any/none of elements fit the predicate
		Stream<Integer> d= Stream.of(1,2,3);
		System.out.println(d.allMatch(x->(x>0))); 
		
		//forEach() use a consumer method to every element in stream
		Stream<Integer> e= Stream.of(1,2,3);
		e.forEach(System.out::println);   // print 1,2,3
		
		//reduce()   like simple induction , reduce can be used to merge all elements to a single one 
		Stream<Integer> f= Stream.of(1,2,3);
		System.out.println(f.reduce(1,(x,y)->x*y));   //we get 1*1*2*3=6
		// if only one element in the stream , that element got printed out, if no element in that stream , an empty optional returned 
		
		//collection()  same as reduce but only use mutable object as start .
		Stream<String> g=Stream.of("g","o","o","g","l","e");
		StringBuilder google=g.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		System.out.println(google.toString());//print out google
		//List<String>  googleString =g.collect(Collectors.toCollection(ArrayList::new)); // the other way which takes one collector to use collect()
		//System.out.println(googleString);
		
		
		//common intermediate operations
		//filter() returns a stream that include elements which meet predicate
		Stream<String> aa=Stream.of("g","o","o","g","l","e");
		aa.filter(x->x.startsWith("g")).forEach(System.out::println);  //output g,g 
		
		//distinct() returns a stream which does not include duplicates
		Stream<String> bb=Stream.of("g","o","o","g","l","e");
		bb.distinct().forEach(System.out::println);			//output gole 
		
		//limit()/skip()  return limited n elements / skip first n elements
		Stream<String> cc=Stream.of("g","o","o","g","l","e");
		cc.skip(1).limit(2).forEach(System.out::println);         //output oo
		
		//map()  it use function method to change stream to another type's stream
		// for example if we want to get stream of every length of every string in the stream
		Stream<String> dd=Stream.of("1","22","333","4444","55555","666666");
		dd.map(String::length).forEach(System.out::println); //output 123456
		
		//
		
	}
}