package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorAndComparable{
	// if we want to make an object comparable , we should let it implement Comparable then override compareTo()
	public class Duck implements Comparable<Duck>{
		private String name;
		private int weight;
		public Duck(String name){
			this.name=name;
		}
		@Override
		public int compareTo(Duck o) {
			return name.compareTo(o.name);
		}
		//three rule of compareTo(): 1 it return 0 if the current obeject is equal to the argument 2&3 it return /negative if current object is smaller/bigger than argument 
		public String toString(){
			return name;
		}
		public int getWeight(){
			return weight;
		}
		
	}
	
	
	// compareTo() and equals() may not be consistent. So we need comparator 
	public static void main(String[] args){
		Duck duck1=new ComparatorAndComparable().new Duck("A");
		Duck duck2=new ComparatorAndComparable().new Duck("B");
		List<Duck> ducks=new ArrayList<>();
		ducks.add(duck1);
		ducks.add(duck2);
		Collections.sort(ducks);
		System.out.println(ducks); // now the list is sorted by their elements'names. 
		
		// sometimes we want to sort objects by different ways other than by the variable we used in CompareTo(), then we need comprarator this time.
		Comparator<Duck> byWeight = new Comparator<Duck>(){
			public int compare(Duck d1, Duck d2){
				return d1.getWeight()-d2.getWeight();
			}
		};// we define a comparator here. a comparator is in fact an inner class.
		// we also use lambda to define comparator.
		//Comparator<Duck> byWeight=(d1,d2)->d1.getWeight()-d2.getWeight();
		Collections.sort(ducks,byWeight); // then use a overload version of sort method. 
		System.out.println(ducks);
	}
}