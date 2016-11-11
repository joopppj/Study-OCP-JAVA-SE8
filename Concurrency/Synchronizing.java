package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Synchronizing{
	private int count=0;
	private synchronized void  incrementAndReport(){ // we only need to add synchronized modifier to make a method synchronized
		synchronized(this){System.out.print((++count)+" ");}
	}
	public static void main(String[] args){
		ExecutorService service=Executors.newFixedThreadPool(20);
		Synchronizing example1=new Synchronizing();
		for(int i=0;i<10;i++)service.submit(()->example1.incrementAndReport());
		// when multiple threads are dealing with same tasks , it may cause some problems(unstable results)
		// the reason is that every thread has different speed , it is hard to predict the final result
		// pre increment++ is not thread safe because it includes two tasks: read and write, whichi is not a atomic operation
		
		// Concurrency API offers Atomic classes which support atomic operations
		AtomicInteger ii= new AtomicInteger(3); // for example , the atomic integer is conceptually same as integer, except for it supports atomic operation
		
		// but only atomic classes are not enough for us to print correct output, some threads could still be faster than other threads.
		// the other thing we need here is monitor, more , is synchronized block 
		synchronized(example1){
			for(int i=0;i<10;i++)service.submit(()->example1.incrementAndReport()); // now we can see the order of 11...20 is correct
		}
		// synchronized block can lock a block , which means a locked block can prevent other threads from entering block. it can ensure only one thread is running this block.
		// in fact we can also use synchronized block in the method to fix the ordering prolem see line 10 
		// Java also provide a more convenient way to make a method synchronized, see line9
		// we can also add synchronized modifier to static method 
		// but synchronization also has its weakness, it will cost more time to finish method executing than in a non-synchronization environment
	}
}