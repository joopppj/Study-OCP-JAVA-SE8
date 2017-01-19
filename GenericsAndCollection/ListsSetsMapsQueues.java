package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class ListsSetsMapsQueues{
	public static void main(String[] args){
		// the Java collections framework a set of classes in java.util for storing objects
		//there are four main interfaces in the Java Collection framework:
		//List: a list is an ordered collection of elements that allows duplicate entries
		List<String> list=new ArrayList<>();
		list.add("one"); // arrayList is a resizable array. when elements are added the ArrayList automatically grows 
		//LinkedList is special since it implements both List and Queue. it has all List methods plus add /remove element from start/end of list
		List<String> linkedList=new LinkedList<>(); 
		
		// Set: it does not allow duplicates
		Set<Integer> set= new HashSet<>();
		set.add(1);set.add(1); // you cannot add same element into a set twice , so the second add() fails.
		Set<Integer> set1= new TreeSet<>();
		set.add(2);set.add(1);    // tree set can make sure the element are always in correct order, but it takes logn you check if a element is present
		
		// Queue: it allows orders like first-in first-out that is related to order of processing elements
		ArrayDeque<Integer> queue=new ArrayDeque<>();
		queue.offer(1);queue.offer(2);queue.poll();// use deque like a queue first-in first-out
		queue.push(1);queue.push(2);queue.poll();// use deque like a queue first-in last-out
	}
}