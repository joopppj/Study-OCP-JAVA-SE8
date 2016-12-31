public enum Enums{ //enum cannot extend other enum
	WINTER("s") {
		@Override
		public void printHours() { // we can give every value its method if we declared abstract method  below
			// TODO Auto-generated method stub
			
		}
	},SPRING("m") {
		@Override
		public void printHours() {  
			// TODO Auto-generated method stub
			
		}
		private void printExpectedVisitors(){  // except for implementing abstract class, we can also override a method
			System.out.println("zz");
		}
	},SUMMER("l") {
		@Override
		public void printHours() {
			// TODO Auto-generated method stub
			
		}
	},FALL("l") {
		@Override
		public void printHours() {
			// TODO Auto-generated method stub
			
		}
	};
	private String expectedVisitors;
	private Enums(String expectedVisitors){   // a constructor of enum must be private since it can only be called from within the enum
		this.expectedVisitors=expectedVisitors;  // the constructor body will be executed only once
	}
	private void printExpectedVisitors(){
		System.out.println(expectedVisitors);
	}
	public abstract void printHours();
	
	public static void main(String[] args){
		for(Enums i:Enums.values()) //value() return list of all values 
		System.out.println(i.name()+""+i.ordinal());  // name() and ordinal() return enums'names and their indexes
		Enums summer= Enums.SUMMER;
		switch(summer){
		case WINTER:
			System.out.println("not summer");
			break;
		case SUMMER:
			System.out.println("SUMMER");
			break;
		//case Enums.SUMMER:    this statement is not legal , Java already knows its enum type. 
		// case 0:   this is not legal either , you cannot compare enum with int
		}		
	}
}