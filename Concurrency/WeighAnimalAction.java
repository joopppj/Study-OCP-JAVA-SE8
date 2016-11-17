package test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

// ForkJoin is a powerful framework which can help us split workload to different thread, which dramatically increases performance
public class WeighAnimalAction extends RecursiveAction{
	private int start;
	private int end;
	private Double[] weights;
	
	public WeighAnimalAction(Double[] weights, int start, int end){ // constructor of ForkJoinTask
		this.weights=weights;
		this.start=start;
		this.end=end;
	}
	@Override
	protected void compute() {
		if(end-start<=3)for(int i=start;i<end;i++){
			weights[i]=(double)new Random().nextInt(100);
			System.out.println("Animal weighed: "+i);
		}
		else {
			int middle=start+((end-start)/2);										// we write recursion here so the workload can be divided into parts
			System.out.println("[start="+start+", middle="+middle+", end="+end);
			invokeAll(new WeighAnimalAction(weights,start,middle),
					new WeighAnimalAction(weights,middle,end));
		}
	}
	public static void main(String[] args){
		Double[] weights= new Double[10];
		ForkJoinTask<?> task= new WeighAnimalAction(weights,0,weights.length);
		ForkJoinPool pool=new ForkJoinPool();
		pool.invoke(task);   // there is one worth mentioning, the workload is not necessarily divided into equal parts, so it may not enhance performance in principle
		
	}
	/*class WeighAnimalTask1 extends RecursiveTask<Double>{     // resursiveTask example
		private int start;
		private int end;
		private Double[] weights;
		public WeighAnimalTask1(Double[] weights,int start,int end){
			this.start=start;
			this.end=end;
			this.weights=weights;
		}
		protected Double compute() {
			if(end-start<=3){
				double sum=0;
				for(int i=start;i<end;i++){
					weights[i]=(double)new Random().nextInt(100);
					System.out.println("Animal weighed: "+i);
					sum+=weights[i];
				}
				return sum;
			}
			else{
				int middle=start+((end-start)/2);
				System.out.println("[start="+start+", middle="+middle+", end="+end);
				RecursiveTask<Double> otherTask= new WeighAnimalTask1(weights,start,middle);
				otherTask.fork();
				return new WeighAnimalTask1(weights,middle,end).compute()+otherTask.join();
			}
			
		}
		
	}*/
	
}