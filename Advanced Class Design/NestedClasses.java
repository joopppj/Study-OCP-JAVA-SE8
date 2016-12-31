public class NestedClasses{
	// a nested class is a class that is defined within another class
	// a non-static nested class is inner class , it has 3 types: 
	private int value=1;
	//first type is MemberInnerClass that is defined at same level as instance variables. it is basically same as ordinary class, except for two differences
	class MemberInnerClass{  
		//static int z;          1. it cannot include static methods or static fields
		public int value1=NestedClasses.this.value;        //2.  it can access outer variables,
		public int value;  //inner class's variables can have same names as outer variable has , but you need to declare which class to use when using them 
	}
	//fourth type is StaticNestedClass that is defined in member level 
	static class StaticNestedClass{
		//int value1=value; since it is static , it cannot access instance variable of enclosing class without a instance of enclosing class
		int z=1;
	}
	int zz=new StaticNestedClass().z;  // values in StaticNestedClass can be accessed from outside .
	private interface InnerInterface{// we can also declare inner interface within class,  inner interface can be private ,which is different from normal interfaces
	}
	abstract class AnonymousInnerClass{
		
	}
	public void method(){
		int localInt=1;
		localInt++;
		//second type is local inner class which is defined within a method, it is very different from normal class
		class LocalInnerClass{  // it cannot have access specifier/static fields and methods , cannot be declared static 
			// int z=localInt;  it does not have access to the local variables of enclosing methods unless those local variables are final/ effectively final
			
		}
		//third type is AnonymousInnerClass, to use it, we firstly need a abstract class and write concrete class in the place where we use it
		AnonymousInnerClass x=new AnonymousInnerClass(){	 // AnonymousInnerClass is great choice for class that get used only once	
		};
	}
	public static void main(String[] args){
		NestedClasses outer=new NestedClasses();
		outer.new MemberInnerClass();  // we can initialize an instance of inner class from outside like this 
		//new MemberInnerClass();  but we cannot do this, because  java do not know which outer class is associated
		
	}
}