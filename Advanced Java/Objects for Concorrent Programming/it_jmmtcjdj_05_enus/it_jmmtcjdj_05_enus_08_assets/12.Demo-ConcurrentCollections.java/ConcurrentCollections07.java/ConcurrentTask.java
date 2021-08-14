// import java.util.Iterator;
// import java.util.concurrent.CopyOnWriteArrayList;

// public class ConcurrentTask implements Runnable {
	
// 	public CopyOnWriteArrayList<String> commonResource;
	
// 	public ConcurrentTask (CopyOnWriteArrayList<String> commonResource) {
// 		this.commonResource = commonResource;
// 	}
	
// 	@Override
// 	public void run()
// 	{
// 		String threadName = Thread.currentThread().getName();
		
// 		try {
// 			for(int i = 0; i< 20; i++) {
// 				Thread.sleep(100);
// 				commonResource.add(threadName + "-data-" + i);
// 			}
// 		}
// 		catch(Exception e) {
// 			e.printStackTrace();
// 		}
		
// 		System.out.println(threadName + " has finished execution");
// 	}
	
// 	public static void main (String[] args) throws InterruptedException {
		
// 		CopyOnWriteArrayList<String> commonRes = new CopyOnWriteArrayList<String>();
		
// 		Thread firstThread = new Thread(new ConcurrentTask(commonRes), "firstThread");
// 		Thread secondThread = new Thread(new ConcurrentTask(commonRes), "secondThread");
		
// 		firstThread.start();
// 		secondThread.start();
		
// 		Thread.sleep(1000);
		
// 		Iterator<String> itr = commonRes.iterator();

// 		while (itr.hasNext()) {
// 			String str = (String) itr.next();

// 			System.out.println("main : " + str);
// 			Thread.sleep(100);
			
// 			itr.remove();
// 		}
// 	}
	
// }