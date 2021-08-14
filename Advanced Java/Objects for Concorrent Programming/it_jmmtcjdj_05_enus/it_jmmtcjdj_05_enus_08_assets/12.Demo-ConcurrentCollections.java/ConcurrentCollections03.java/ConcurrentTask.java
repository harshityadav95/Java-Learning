import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentTask implements Runnable {
	
	private static final int NUM_ITERATIONS = 10000;

	public List<String> commonResource;
	
	public ConcurrentTask (List<String> commonResource) {
		this.commonResource = commonResource;
	}
	
	@Override
	public void run()
	{
		String threadName = Thread.currentThread().getName();
		
		try {
			for(int i = 0; i< NUM_ITERATIONS; i++) {
				commonResource.add(threadName + "-data-" + i);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(threadName + " has finished execution");
	}
	
	public static void main (String[] args) throws InterruptedException {
		
		List<String> commonRes = 
				Collections.synchronizedList(new ArrayList<String>());
		
		Thread firstThread = new Thread(new ConcurrentTask(commonRes), "firstThread");
		Thread secondThread = new Thread(new ConcurrentTask(commonRes), "secondThread");
		
		firstThread.start();
		secondThread.start();
		
		firstThread.join();
		secondThread.join();
		
		System.out.println("#elements in commonRes: " + commonRes.size());
	}
	
}