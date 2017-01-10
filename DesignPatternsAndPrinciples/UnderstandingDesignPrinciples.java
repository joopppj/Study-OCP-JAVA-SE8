package test;
// encapsulating is very important in professional software development 
// encapsulation is to protect fields/methods from using by other users , int technical words: to set those variables/methods private
// if we have a class without encapsulation, its variable will easily be changed from outside even they should not
class Animal{
	public int age; 
}

public class UnderstandingDesignPrinciples{
	public static void main(String[] args){
		Animal animal= new Animal();
		animal.age=-1100;     
	}
}