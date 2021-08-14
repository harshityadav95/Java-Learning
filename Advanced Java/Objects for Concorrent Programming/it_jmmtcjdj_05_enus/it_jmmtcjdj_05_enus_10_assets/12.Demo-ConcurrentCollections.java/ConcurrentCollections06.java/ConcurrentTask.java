import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ConcurrentTask implements Runnable {
	
	public List<String> commonResource;
	
	public ConcurrentTask (List<String> commonResource) {
		this.commonResource = commonResource;
	}
	
	@Override
	public void run()
	{
		String threadName = Thread.currentThread().getName();
		
		try {
			for(int i = 0; i< 20; i++) {
				Thread.sleep(100);
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
		
		Thread.sleep(1000);
		
		Iterator<String> itr = commonRes.iterator();

		while (itr.hasNext()) {
			String str = (String) itr.next();

			System.out.println("str : " + str);
			Thread.sleep(100);
		}
	}
	
}