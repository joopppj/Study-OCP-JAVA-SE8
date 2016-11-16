package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parallel_Streams{
	public static void helper1(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		// there are two ways how to create parallel streams
		Stream<Integer> stream= Arrays.asList(1,2,3).stream();
		Stream<Integer> parallelStream=stream.parallel();
		// we firstly create a normal stream then change this stream to parallel one
		
		// or we just create a parallel stream directlly 
		Stream<Integer> parallelStream2= Arrays.asList(1,2,3).parallelStream();
		
		//when we use normal stream to print out all contents in the stream (single-thread)
		Arrays.asList(1,2,3).stream().forEach(s->System.out.print(s)); // the output is in order
		//but when we use parallel stream to print contents in the stream (multiple threads)
		parallelStream.forEach(System.out::print); // this time the output is not in order
		parallelStream2.forEachOrdered(System.out::print);// the method forEachOedered() can make output in order , but it lost some performance 
		System.out.println();
		// let's how multiple threads help increase performance
		long start1=System.currentTimeMillis();
		List<Integer> data= Arrays.asList(1,2,3,4,5,6,7,8,9,0);
		data.forEach(s->helper1());
		double timeCost1=(System.currentTimeMillis()-start1)/1000;// it should print around1 second since it runs helper1 ten times.
		System.out.println(timeCost1+"seconds cost");
		long start2=System.currentTimeMillis();
		helper1();
		double timeCost2=(System.currentTimeMillis()-start2)/1000;// it should print around 0.1 second since it runs helper1 only ine time
		System.out.println(timeCost2+"seconds cost");
		// so we can see parallel can vastly increase performance 
		
		Arrays.asList("one","two","three").parallelStream().map(s->s.toUpperCase()).forEach(System.out::print); // we can this is not in order
		// this is because map() and forEach() are both independent operations, they can be completed independently
		
		// a stateful lambda expression is on whose result depends on any state that might change during execution of pipeline
		// we should avoid any stateful lambda expressions
		List<Integer> data1=Collections.synchronizedList(new ArrayList<>());
		Arrays.asList(1,2,3,4,5).parallelStream().map(i->{data1.add(i);return i;}).forEachOrdered(System.out::print);
		System.out.println();
		for(Integer e:data1)System.out.print(e+" "); // we can see that even we use forEachOrdered() to force the printing content in order, the stateful lambda still makes the output become random
		// notice that if I use a normal arrayList here , it will cause some data lost 
		
		// Also because of the output of parallel is unexpected , the reduct operation can also cause unexpected result
		System.out.print(Arrays.asList(1,2,3,4,5).parallelStream().findAny().get()); // this output could not be 1, it could be any number in the stream
		// another thing worth mentioning is that those operation that is based on order ,such as limit() may actually perform more slowly in parallel environment
		// but these operations can give correct output , on the plus side
		
		
		// if we do not need stream ordered , we can use unordered(), it can vastly increase performance in parallel environment 
		Arrays.asList(1,2,3,4,5,6).stream().unordered().parallel();
		
		
		// when using reduce() method on parallel stream , there are three rules we need to follow so that we can ensure the order is correct
		// first , the identity need to be defined such that for all elements , combiner.apply(identity,n) is equal to n
		System.out.println(Arrays.asList("z","e").parallelStream().reduce("X",String::concat ));// here our identity is "X", which is not illegal
		// secondly and thirdly our accumulator and combiner must be associative and stateless such that (a op b) op c is equal to a op (b op c)
		System.out.println(Arrays.asList(1,2,3,4).parallelStream().reduce(0,(a,b)->(a-b))); // the lambda we use here is not associative so the answer we get could be -10 or some other value
		// java recommends using three-argument reduce() method
		
		// using collect () has same problem , parallel may affect the order of result 
		System.out.println(Stream.of("2","0","1","6").parallel().collect(Collectors.toSet())); // the order of result is unexpected
		// but we can do nothing with it and we can only use some ordered collections like tree set here
		// but to use collection in parallel environment , we need to follow three rules
		//1 the parameter of the collect operation has the Collector.Characteristics.CONCURRENT characteristic
		//2 Either the stream is unordered, or the collector has the characteristics Collector.Characteristics.UNORDERED
	}
}