package test;

public final class Immutable{
	// the immutable object pattern is a creational pattern based on the idea of creating objects whose state does not change after they are created
	//like singleton, there are also many features immutable object pattern have
	// 1. all of instance vatriables should be private and final
	private final int value;
	private final StringBuilder string;
	// 2. in constructor , we have to set all properties of the object
	public Immutable(int value,StringBuilder string){
		this.value=value;
		this.string=string;
	}
	// 3. setter methods are not allowed since this obeject's state cannot be changed
	// 4. the referenced mutable object cannot be accessed or modified directly
	public StringBuilder getString(){return string;} //this is not allowed ! since string is a mutable type , it should not be modified or accessed
	// 5. methods cannot be overridden, there are multiple ways to follow this rule , we can mark this class/methods as final. or we can mark constructor as private and apply factory pattern

}