// import java.util.Iterator;
// import java.util.HashMap;

// public class ConcurrentTask implements Runnable {
	
// 	public HashMap<String, String> commonResource;
	
// 	public ConcurrentTask (HashMap<String, String> commonResource) {
// 		this.commonResource = commonResource;
// 	}
	
// 	@Override
// 	public void run()
// 	{
// 		String threadName = Thread.currentThread().getName();
		
// 		try {
// 			for(int i = 0; i< 20; i++) {
// 				Thread.sleep(100);
// 				commonResource.put(threadName + "-key-" + i, 
// 						   threadName + "-val-" + i);
// 			}
// 		}
// 		catch(Exception e) {
// 			e.printStackTrace();
// 		}
		
// 		System.out.println(threadName + " has finished execution");
// 	}

// 	public static void main (String[] args) throws InterruptedException {
		
// 		HashMap<String, String> commonRes = 
// 				new HashMap<String, String>();
		
// 		Thread firstThread = new Thread(new ConcurrentTask(commonRes), "firstThread");
// 		Thread secondThread = new Thread(new ConcurrentTask(commonRes), "secondThread");
		
// 		firstThread.start();
// 		secondThread.start();
		
// 		Thread.sleep(1000);
		
// 		Iterator<String> itr = commonRes.keySet().iterator();

// 		while (itr.hasNext()) {
// 			String str = (String) commonRes.get(itr.next());

// 			System.out.println("main : " + str);
// 			Thread.sleep(100);
// 		}
// 	}
	
// }