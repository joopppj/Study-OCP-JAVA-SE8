package test;
//Polymorphism is the ability of a single interface to support multiple underlying forms
interface human{
	public String speak();
}
class idiot implements human{
	public String speak(){return "";}
}
class talent implements human{
	public String speak(){return "";}
}
public class ImplementingPolymorphism{
	public static void main(String[] args){
		human idiot = new idiot();  // polymorphism allows one object to be referenced by the interface it implements
	}
}
