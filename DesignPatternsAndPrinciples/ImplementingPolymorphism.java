package test;
//Polymorphism is the ability of a single interface to support multiple underlying forms
interface human{
	public String speak();
}
class idiotOrTalent{
	
}
class idiot implements human{
	public String speak(){return "";}
}
class talent extends idiotOrTalent implements human {
	int age =10;
	public String speak(){return "";}
}
public class ImplementingPolymorphism{
	public static void main(String[] args){
		human idiot = new idiot();  // polymorphism allows one object to be referenced by the interface it implements
		human talent=new talent();
		//System.out.println(talent.age);  // this does not compile because if only methods and variables that are part of reference type can be accessed
	// instance type determine which properties exist 
	// reference type determine which properties are accessible
		//talent talent1=talent; // casting an object from a super class requires an explicit cast
		idiotOrTalent idiotOrTalent=new idiotOrTalent();
		talent talent2= (talent) idiotOrTalent;  // this throws classCastException at runtime since idiotOrTalent is not a talent object. java know sit
	}
}
