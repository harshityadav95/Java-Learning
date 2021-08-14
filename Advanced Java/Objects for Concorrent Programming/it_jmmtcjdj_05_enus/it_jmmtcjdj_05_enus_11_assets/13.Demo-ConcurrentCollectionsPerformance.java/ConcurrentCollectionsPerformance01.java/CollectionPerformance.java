import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionPerformance {
	
	private ArrayList<String> myList = new ArrayList<String>();
	List<String> mySyncList = 
			Collections.synchronizedList(new ArrayList<String>());
	private Vector<String> myVector =new Vector<String>();
	private CopyOnWriteArrayList<String> myCOWList = 
				new CopyOnWriteArrayList<String>();
	

	private static final int NUM_ITERATIONS = 100000;
	
	public void testArrayList() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myList.add("data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time ArrayList: " + (endTime-startTime) + "ms");
	}
	
	public void testSynchronizedList() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			mySyncList.add("data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time synchronizedList: " + (endTime-startTime) + "ms");
	}
	
	public void testVector() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myVector.add("data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time Vector: " + (endTime-startTime) + "ms");
	}
	
	public void testCOWArrayList() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myCOWList.add("data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time COWArrayList: " + (endTime-startTime) + "ms");
	}
	
	public static void main (String[] args){
		
		CollectionPerformance cp = new CollectionPerformance();
		
		cp.testArrayList();
		cp.testSynchronizedList();
		cp.testVector();
		cp.testCOWArrayList();
		
	}
}