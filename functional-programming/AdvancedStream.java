package test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedStream{
	// a few intermediate operations for stream are also available on Optional
	// if we want to print a number from an Optional<Integer> , ONLY IF the number is 2-digit and positive 
	// we can just use chaining operations on an optional
	private static void twoDigits(Optional<Integer> optional){
		 optional.map(n->""+n).filter(n->n.charAt(0)!='-').filter(n->n.length()==2).ifPresent(System.out::println);		 
	 }

	//
	public static void main(String[] args){
		 
		 // if we want to print a number from an Optional<Integer> , ONLY IF the number is 2-digit
		twoDigits(Optional.of(33));// output 33
		twoDigits(Optional.of(-3));// fail
		
		// if we want to get string length from a optional string. there is a quick way:
		Optional<String> optionalString= Optional.of("google");
		Optional<Integer> length=optionalString.map(String::length);
		// there is one thing to notice , if the function in map() return another option , we should use flatMap which helps us remove  layers
		
		//some useful collectors we can use on our collect(), the terminal operation on stream
		// join(CharSequence), it creates a single String object which include delimiter between two elements 
		Stream<String> strings =Stream.of("kira","jooppp","Zhousenye Liu");
		System.out.println(strings.collect(Collectors.joining("|")));  //output kira|jooppp|Zhousenye Liu
		
		// averagingInt/Double/Long() , calculates average
		Stream<String> strings1 =Stream.of("kira","jooppp","Zhousenye Liu");
		System.out.println(strings1.collect(Collectors.averagingInt(String::length))); // output (4+6+13)/3= 7.6666
		
		// toMap(), create a map using two functions,one for key, one for value, sometimes another one for merging
		Stream<String> strings2 =Stream.of("kira","jooppp","Zhousenye Liu");
		Map<String,Integer> strings2Map=(Map) strings2.collect(Collectors.toMap(s -> s, String::length));
		System.out.print(strings2Map); //output {jooppp=6, Zhousenye Liu=13, kira=4}
		// but if we have duplicates , java gives exception
		// we can solve it by providing binaryOperator (the merge function)
		// and the map type we get is not stable ,so we need the fourth parameter to make sure we get want we want,(lets say we want treeMap)
		Stream<String> strings3 =Stream.of("kira","KIRA","zero");
		Map<Integer,String> strings3Map=(Map) strings3.collect(Collectors.toMap(String::length,s -> s,(s1,s2)->s1+" "+s2,TreeMap::new));
		System.out.print(strings3Map);
		System.out.print(strings3Map.getClass());// output treeMap
		
		// collecting by using grouping, groupingBy()
		Stream<String> strings4 =Stream.of("kira","KIRA","zero");
		Map<Boolean,List<String>> map4=strings4.collect(Collectors.groupingBy(n->n.startsWith("k")));
		System.out.print("\n"+ map4); // output {false=[KIRA, zero], true=[kira]}
		
		// if we don't want List, we want set as our values , just use Collectors.toSet to our 3rd parameter of groupingby()
		// we can also change the return map by providing 2nd parameter
		Stream<String> strings5 =Stream.of("kira","KIRA","zero","kira");
		Map<Boolean,Set<String>> map5=strings5.collect(Collectors.groupingBy(n->n.startsWith("k"),TreeMap::new,Collectors.toSet()));
		System.out.print("\n"+ map5); // output  still {false=[KIRA, zero], true=[kira]}
		
		//partitioning is subset of grouping, it could only give two keys: true and false
		//rewrite strings5 is :
		Stream<String> strings6 =Stream.of("kira","KIRA","zero","kira");
		Map<Boolean, Set<String>> map6=strings6.collect(Collectors.partitioningBy(n->n.startsWith("x"),Collectors.toSet()));
		System.out.print("\n"+ map6); // output  {false=[zero, KIRA, kira], true=[]}
		// we cannot change our map type using partitioningBy
		// we can also use Collectors.counting() to get something like {false=3, true=0} + also, one important difference between grouping and partitioning is grouping only return necessary keys while partitioning always give true and false as keys!
		
		
		// we can even use maping to show more complicated logic:
		// we group strings by their lengths and then for every value we show the min value of first charactors of all strings
		Stream<String> omg=Stream.of("kami","kamisama","god");
		Map<Integer, Optional<Character>> lastMap= omg.collect(Collectors.groupingBy(String::length, Collectors.mapping(s->s.charAt(0), Collectors.minBy(Comparator.naturalOrder()))));
		System.out.print("\n"+ lastMap); // output {3=Optional[g], 4=Optional[k], 8=Optional[k]}
		
		/// This should be (one of) the most complicated grammar a java se8 programmer need to know ,I guess..
		
		
		
	 }
}
