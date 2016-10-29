package primitive_stream;

import java.util.*;
import java.util.OptionalDouble;
import java.util.function.*;
import java.util.stream.*;

class exercise1{
	public static void main(String[] args){	
		// create primitive stream
		// create an empty primitive stream
		DoubleStream empty= DoubleStream.empty();
		
		//create an int stream which contains some elements 
		IntStream ints= IntStream.of(1,2,3);
		ints.forEach(System.out::println); //output 123
		
		// we can also use generate or iterate to create infinite stream 
		DoubleStream random= DoubleStream.generate(Math::random);
		random.limit(3).forEach(System.out::println); // out put 3 random numbers
		
		// when we want to count from 1 to 3 we can write this IntStream
		IntStream count =IntStream.iterate(1, n->n+1);
		count.limit(3).forEach(System.out::println); 
		// but java provides a better method called range to deal with this using simple code
		IntStream range= IntStream.range(1, 4);
		range.forEach(System.out::println);
		
		// also range Closed may be clearer, since the end index is included
		IntStream rangeClosed= IntStream.range(1, 3);
		rangeClosed.forEach(System.out::println);
		
		// map methods between different types of stream
		// mapToInt/mapToDouble/mapToLong change object stream to primitive stream
		//(sure we can also use flatMapToInt.....)
		Stream<String> strings = Stream.of("one","two","three");
		strings.mapToInt(n->n.length()).forEach(System.out::println); // output lengths of all strings in the stream 
		
		
		// Optionals related to primitives stream and realated methods sum() average() max() min()
		IntStream ints100=IntStream.rangeClosed(1,100);
		OptionalDouble optionalDouble= ints100.average();
		
		System.out.print(optionalDouble.getAsDouble());   // get the double from the optional
		IntStream emptyInts=IntStream.of();
		System.out.println(emptyInts.average().orElseGet(()->Double.NaN)); // if the DoubleStream is empty , we use the special value
		IntStream emptyInts2=IntStream.of();
		System.out.println(emptyInts2.sum()); // sum() is tricky, even the stream is empty it still outputs double with value 0.
		
		// summarizing statistics
		// if we want to get two or more values from sum,average,max and min, we cannot use the old way since only one terminal operation could be used
		// Java can solve this problem by using Int/Double/LongSummaryStatistics
		LongSummaryStatistics longStatistics= LongStream.of(1,2,3).summaryStatistics();
		System.out.println(longStatistics.getMax()); //output 3 , the max value
		System.out.println(longStatistics.getMin()); //output 1 , the min value
		
		
		// functional interfaces for primitives
		// functional interfaces for boolean
		BooleanSupplier b1 = ()->Math.random()-Math.random()>0; 
		System.out.println(b1.getAsBoolean());// I actually built a simple coin simulator! it has fifty-fifty chances to get true/false.
		
		// many more interfaces for other primitives ...... to manny to list 
		
	}
}