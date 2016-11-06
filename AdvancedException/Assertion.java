package test;

public class Assertion{
	
	public static void main(String[] args){
		// we can use assertion to test our code at some point
		// generally assertion causes three types of outcomes
		// first assertion is not enabled , compiler omits assert 
		
		// the second is boolean expression in assert is true , nothing happens
		int j=0;
		assert j==0;
		
		// the third possible outcome is assertion is false,compiler will throw assertion error
		// rest of code will never get run
		// (error message could also be added after assert.)
		int i=1;
		assert i==0: "i is not 0";
		
		//Generally there are 5 ways of using assert in real world.
		// they are internal Invariants, precondition,postcondition,class invariants. control flow invariants
		// first three ones are very easy , I only practice the last two.
		
		// control flow invariants are used to ensure those unreachable code are never reacheable
		int k=100; 
		if (k<0) assert false:"it is impossible to reach here";
		
		// class invariants are used to ensure the validity of an object's state
		// they are typcally an private method which returns boolean
		class rectangle{
			private int width,length;
			public rectangle(int width,int length){
				this.width=width;
				this.length=length;
			}
			public int getArea(){
				assert isValid():"object variables are not valid";
				return width*length;
			}
			private boolean isValid() {
				// TODO Auto-generated method stub
				if (width<0||length<0)return false;
				return true;
			}
		}
		rectangle invalidRectangle=new rectangle(-1,9);
		System.out.println(invalidRectangle.getArea());
		// there is one thing worth pay attention to: if we want to check if parameters are valid in constructor ,we need to use illegalArgumentException
		// assertion is use in for dubugging purpose , which means we are sure content in assertion is true , we just want to verify it .
	}
}