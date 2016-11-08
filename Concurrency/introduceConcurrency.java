public class introduceConcurrency{
	static int trumpVotes=0;
	static int hillaryVotes=0;
	//generally we have two ways to create  a thread 
	// first way: provide a runnable project to thread constructor then start it .
	public static class sample1 implements Runnable{
		public void run() {
			System.out.println("example1");
		}
	}
	
	// second way: create a class which extends thread then override run() method
	public static class sample2 extends Thread{
		public void run(){
			System.out.println("example2");
		}
	}
	public static void main(String[] args) throws InterruptedException{
		(new Thread(new sample1())).start();
		(new sample2()).start();
		
		// every thread starts task in separate operating system thread
		// which means the order of tasks actually completed is unknown until runtime
		System.out.println("start");
		(new Thread(new sample1())).start();
		(new sample2()).start();
		System.out.println("end"); // we can see only order of "start" and "end" can be sure 
		// in face , the order of execution of statements in a single run() method is also linear
		
		// an interesting application of thread is polling , we can intermittently check
		// data at some fixed interval. for example , if we want to simulate presidential election (the one whose thread is faster wins)
		
		
		new Thread(()->{
			for(int i=0;i<10;i++)trumpVotes++;
		}).start();
		new Thread(()->{
			for(int j=0;j<10;j++)hillaryVotes++;
		}).start();
		while(hillaryVotes<10&&trumpVotes<10)System.out.println("not yet");
		if (hillaryVotes>=trumpVotes)System.out.println("hillary wins");
		else System.out.println("trump wins");
		
		// sleep() method can pause associated thread , for example if we want to pause main() thread for 1 second 
		Thread.sleep(1000);
		System.out.println("1 second passed");
		
	}
	
}
