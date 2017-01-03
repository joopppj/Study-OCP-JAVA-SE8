package test;
@FunctionalInterface // if the interface is not functional interface ,this annotation will detect an error
// any interface that has exactly one abstract method is functional interface.
public interface AnyInterface{
	public abstract void sprint(String animal);
}
/*public class normalClass {
	
}*/
