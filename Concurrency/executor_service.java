package test;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class executor_service{
	static int counter=0;
	public static void use(Supplier<Integer> expression){}
	public static void use(Callable<Integer> expression){}
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService service =null;
		service=Executors.newSingleThreadExecutor();
		System.out.println("begin");
		service.execute(()->System.out.println("keep printing"));
		service.execute(()->{for(int i =0;i<10;i++)System.out.println(i);});
		service.execute(()->System.out.println("keep printing"));
		System.out.println("end");
		// after using thread, we should use shutdown()
		service.shutdown();
		//shutdown can make thread stop receiving tasks, but the thread is still executing old tasks 
		// the other method shutdownNow() can stop all running tasks
		// you can also us isShutdown() and isTerminated() to check if this thread is terminated and shutdown
		
		// another method submit() is very similar to execute(), it can return a future object which can help us check state of the result of callable object
		ExecutorService service1 = Executors.newSingleThreadExecutor();
		Future<?> result=service1.submit(()->{for(int i=0; i<100;i++)counter++;});
		// let us check if your cpu is fast enough to add counter to 100 in one nanosecond;
		try {
			result.get(1,TimeUnit.NANOSECONDS);
		}  catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("too slow to process it");
		} finally{
			System.out.println("the thread is done? "+result.isDone()+"; the thread is canceled? "+ result.isCancelled()); // we can use isDone and isCancel to check if a task is done or canceled
			if(service1!=null)service1.shutdown();
		}
		// the nanoSeconds in TimeUnit means 1/1000000000 seconds; microSconds means 1/1000000 seconds; milliSeconds means 1/1000 seconds
		
		// when we ensure a thread is shutdown, we also need to ensure a thread is terminated. Then we need to use the method awaitTerminated().
		if (service1!=null){
			service1.awaitTermination(1, TimeUnit.MINUTES);  // we give service1 one minute to finish its tasks 
			if(service1.isTerminated())System.out.println(" all tasks are finished");
			else System.out.println(" some tasks are still running");
		}
		
		// callable is very similar to runnable except for its method call() return generic type and can throw exception
		// the functional interface callable is very similar to supplier , except for callable can throw checked exception
		//use(()->{throw new IOException();});  this doesn't complies. because java doean't know which overloaded version to use
		use((Callable<Integer>) ()->{throw new IOException();}); // but it complies if we cast expression to callable object
		
		//unlike runnable, when using callable as parameter of submit, the return future object will get a generic type
		ExecutorService service2 = Executors.newSingleThreadExecutor();
		Future<Integer> result2= service2.submit(()->1);
		System.out.println(result2.get());
		// the callable version is usually preferred in practice 
		
		// the way to create ScheduledExecutorService, ScheduledExecutorService can schedule tasks in the future.
		ScheduledExecutorService service3= Executors.newSingleThreadScheduledExecutor();
		service3.scheduleAtFixedRate(()->{System.out.println("one second");}, 1, 1, TimeUnit.SECONDS); // for example we want to print message every second
		service3.scheduleWithFixedDelay(()->{System.out.println("two second");}, 0, 1, TimeUnit.SECONDS);
		// scheduleWithFixedDelay() is a little bit different, it will not send next task until the last task is finished
		
		// sometimes a single thread is not enough for many tasks, we may need newCacbedThreadPool(), newFixedThreadPool(int nThreads), newSchedualedFixedThreadPool(int nThreads)
		// CacbedThreadPool() creates a thread pool of unbounded size, system will allocate new thread if current thread is not enough, but it is not good for many complicated tasks (use too much memory of CPU)
		ExecutorService service4= Executors.newCachedThreadPool();
		
		// newFixedThreadPool(int nThreads) creates a Thread Pool of fixed size for different tasks working concurrently. it follows same manner if all threads are occupied 
		ExecutorService service5= Executors.newFixedThreadPool(3); // we create a thread pool of 3.
		
		// newSchedualedFixedThreadPool(int nThreads) is very similar to newFixedThreadPool(int nThreads) except for it is creating ScheduledExecutorService pool
		ScheduledExecutorService service6= Executors.newScheduledThreadPool(3);
		service6.scheduleAtFixedRate(()->{System.out.println("three second");}, 1, 1,TimeUnit.SECONDS);
		// when using newSingleThreadpool, it is possible that it will release endless tasks if task running time is longer than period time
		// but with thread pool , we can avoid this problem, because the thread that finished task can go back to pool and become avaliable again
		
		// decide the pool size if not easy 
		System.out.println(Runtime.getRuntime().availableProcessors()); // but we can use this method to check avaliable CPU resources 
	}
}