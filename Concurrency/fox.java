package test;
import java.util.concurrent.*;;

	 class food{}
	 class water{}
	public class fox{
		// there are many threading problems , Dead lock is one of them.
		
		public void eatAndDrink(food food,water water){
			synchronized(food){
				System.out.println("got food");
				move();
				synchronized(water){
					System.out.println("got water");
				}
			}
		}
		public void drinkAndEat(food food,water water){
			synchronized(water){
				System.out.println("got water");
				move();
				synchronized(food){
					System.out.println("got food");
				}
			}
		}

		private void move() {
			// TODO Auto-generated method stub
			try{
				Thread.sleep(100);
			} catch (InterruptedException e){
			}
		}
		public static void main(String[] args){
			fox foxy=new fox();
			fox tails=new fox();
			food food=new food();
			water water=new water();
			ExecutorService service = Executors.newScheduledThreadPool(10);
			try{
				service.submit(()-> foxy.eatAndDrink(food, water));
				service.submit(()->tails.drinkAndEat(food, water));// both eatAndDrink() and drinkAndEat() hang and cannot finish
			}finally{
				if(service!=null)service.shutdown();
			}
			System.out.println("end");
		}
		
	}
	
	
