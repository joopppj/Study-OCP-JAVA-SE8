package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class concurrentCollection{
	//concurrentcollection is more convenient than synchronized
	private Map<String,Integer> map= new ConcurrentHashMap<String,Integer>();
	
	public static void main(String[] args ){
		//when we use ordinary collections types, sometims it can cause memory consistency error
		Map<String,Integer> map1= new HashMap<String,Integer>();
		map1.put("aa", 1);
		map1.put("bb", 2);
		//for(String key: map1.keySet())map1.remove(key);// this causes concurrent modification exception
		// this is because keyset() did not update properly after first key is removed
		Map<String,Integer> map2= new ConcurrentHashMap<String,Integer>();
		map2.put("aa", 1);
		map2.put("bb", 2);
		for(String key: map2.keySet())map2.remove(key);// this works fine after using concurrent
		
		// there are many other concurrent collectiontypes for example:
		Deque deque=new ConcurrentLinkedDeque();
		Queue queue=new ConcurrentLinkedQueue();
		
		// some of them are a little bit special
		//blocking queues are alomost same as queues, but they include methods that will wait a specific amount of time to complete an operation 
		BlockingQueue blockingQueue= new LinkedBlockingQueue<Integer>();
		try {
			blockingQueue.offer(1, 1, TimeUnit.SECONDS); // it will wait for 1 second then add item to this queue
			// return false if time is up before space is available 
			blockingQueue.poll(1, TimeUnit.SECONDS); // similarly it will wait for 1 second to remove an item
			// return null if time is up before the item is available 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// blockingDeque is similar to  blockingQueue , but it has more complete methods
		BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
		try {
			blockingDeque.offerFirst(1, 1, TimeUnit.SECONDS); // return false if time is up before space is available
			blockingDeque.offerLast(1, 1, TimeUnit.SECONDS); //  return false if time is up before space is available
			blockingDeque.pollFirst(1, TimeUnit.SECONDS);  // return null if the time is up before the item is available
			blockingDeque.pollLast(1,TimeUnit.SECONDS);  // return null if the time is up before the item is available
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ConcurrentSkipListSet and CConcurrentSkipListMap are concurrent version of TreeSet and TreeMap
		
		// CopyOnWrite collections are a little bit different, they can include underlying structure which keeps updating when the original object  is modified
		// but the original the reference to original object does not change, for example:
		List<Integer> list= new CopyOnWriteArrayList<>(Arrays.asList(4,3,52));
		for(Integer item:list){
			System.out.println(item);
			list.add(9);
		}								// it only show original structure 
		for(Integer item:list){
			System.out.println(item);  // now the underlying structure has replaced the original one
		}
		// copyonwrite collections use a lot of memory , so we only use multi-threaded situations where reads are far more common than writes
		
		//collections supply synchronized collections method 
		List<Integer> list1=Collections.synchronizedList(new ArrayList<>(Arrays.asList(1,2,3)));
		synchronized(list1){
			for(int data:list1)System.out.println(data+" ");
		}
		// but this method is not good since it can also throw concurrent exceptions
	}
}