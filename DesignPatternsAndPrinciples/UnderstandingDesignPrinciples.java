package test;
// encapsulating is very important in professional software development 
// encapsulation is to protect fields/methods from using by other users , int technical words: to set those variables/methods private
// if we have a class without encapsulation, its variable will easily be changed from outside even they should not
class Animal{
	public int wrongAge; 
	private int age;
	private boolean mammal;
	private Boolean pet;
	public boolean isMammal() {
		return mammal;
	}
	public void setMammal(boolean mammal) {
		this.mammal = mammal;
	}
	public Boolean isPet() {   //Boolean getter cannot start with is since it is not boolean
		return pet;
	}
	public void setPet(Boolean pet) {  //setters begin with set
		this.pet = pet;
	}
	public int getAge() {  //getters begin withget
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

public class UnderstandingDesignPrinciples{
	public static void main(String[] args){
		Animal animal= new Animal();
		animal.wrongAge=-1100;   
		//animal.age= -100;// so we need a encapsulation to protect important variable from being changed.
	}
}
