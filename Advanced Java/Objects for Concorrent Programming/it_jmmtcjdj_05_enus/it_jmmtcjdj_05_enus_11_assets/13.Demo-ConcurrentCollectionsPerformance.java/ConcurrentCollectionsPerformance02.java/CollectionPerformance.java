import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionPerformance {
	
	private HashMap<String, String> myHashMap = new HashMap<String, String>();
	private Hashtable<String, String> myHashTable = new Hashtable<String, String>();
	private ConcurrentHashMap<String, String> myConcurrentHashMap = 
				new ConcurrentHashMap<String, String>();
	

	private static final int NUM_ITERATIONS = 10000;
	
	public void testHashMap() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myHashMap.put("key-" + i, "data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time HashMap: " + (endTime-startTime) + "ms");
	}
	
	public void testHashTable() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myHashTable.put("key-" + i, "data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time HashTable: " + (endTime-startTime) + "ms");
	}
	
	public void testConcurrentHashMap() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myConcurrentHashMap.put("key-" + i, "data-" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time ConcurrentHashMap: " + (endTime-startTime) + "ms");
	}
	
	public static void main (String[] args){
		
		CollectionPerformance cp = new CollectionPerformance();
		
		cp.testHashMap();
		cp.testHashTable();
		cp.testConcurrentHashMap();
		
	}

}