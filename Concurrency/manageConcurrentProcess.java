package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class manageConcurrentProcess{
	public static void doSomething(){
		System.out.print("1");
		System.out.print("2");
		System.out.print("3");
	}
	public static void safelyDoSomething(CyclicBarrier c1){
		try {
		System.out.print("1");
		c1.await();   // await() method can pause the process until all threads c1 manages  arrive at this point
		System.out.print("2");
		c1.await();
		System.out.print("3");}catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	
	public static void main(String[] args) throws InterruptedException{
	// The class CyclicBarrier can help us control the order of process
	ExecutorService service=Executors.newFixedThreadPool(3);
	manageConcurrentProcess manager=new manageConcurrentProcess();
	for(int i=0;i<3;i++)service.submit(()->manager.doSomething()); // sometimes this prints wrongly ordered result because of there are multiple threads
	
	Thread.sleep(100);
	// So at this time we need something to manage all process , CyclicBarrier can used here 
	CyclicBarrier c1=new CyclicBarrier(3); // the parameter in the constructor is the number of threads it can manage
	ExecutorService service1=Executors.newFixedThreadPool(3);
	System.out.println();
	for(int i=0;i<3;i++)service1.submit(()->manager.safelyDoSomething(c1)); // now the order is as expected , all thread will execute together one step by one step
	// just make sure the number of threads of CyclicBarrier is smaller or equal to the threads you need to manage
	
	// the other important manage method fork/join will have lots of code, So I dont write it here
	}
}