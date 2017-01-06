package test;
// this java file cannot compile 
@FunctionalInterface
// any interface that has exactly one abstract method is functional interface.
public interface fi{
	public abstract void sprint(String animal);
}
/*
 * spotting invalid lambdas
 * Duck d->d.quack() ; a,d->quack() ; Animal a, Duck d ->d.quack() ; these lambdas do not compile 
 * c -> return 10;  this does not compile because return statement should be included in a bracket 
 * a -> {return 10} this also does not compile because the semicolon is missing
 * (int x,y)-> 10; (a,int b,c)->10 ; these does not compile because if we specify data type of one parameter of inputs, we need to specify rest of them .
 * (a,b) ->{int a=0; return 5} this does not compile because parameter cannot be re declared 
 */
/*public class normalClass {
	
}*/