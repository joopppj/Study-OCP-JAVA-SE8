package test;

public class Singleton{
	private int value=0;
	// the singleton pattern is a creational pattern focused on creating only one instance of an object in memory.
	// singleton pattern has these features: 
	
	// 1. it has a private static final instance variable  which is the "" only one instance of this class
	private static final Singleton instance= new Singleton();
	// it is also usual to use static initialization block create this instance
	/*private static final Singleton instance;
	static{
		instance= new Singleton();
		// doing some additional steps here
	}*/
	// another usual technique is lazy instantiation which means the instance is only created after using getInstance() first time.
	/*private static final Singleton instance;
	public static Singleton getInstance(){
		if(instance==null)instance=new Singleton();    this is not thread safe since two threads can create two instance at the same time
		return instance;
	}
	the best solution to thread safe problem is double-checked locking
	private static volatile Singleton instance;  the volatile keyword optimize the code such that the object is accessed before it is finished being constructed
	public static Singleton getInstance(){
		if(instance==null){
			synchronized(Singleton.class){
			if(instance==null){
				instance = new Singleton();
			}
			}
		}
	}  this ensure synchronized is only applied only when instance is null,
	*/
	
	// lazy initialization improve performance and reduce memory usage . but there will be a delay the first tiem when a particular resource is needed 
	// 2. it has a public static method called getInstance() to get this instance 
	public static Singleton getInstance(){
		return instance;
	}
	// 3. singleton's constructor must be private. and singleton class is effective final since not subclass can call super()
	private Singleton(){}
	// 4. all other methods shoud be marked synchronized
	public synchronized void addValue(){
		value++;
	}
}