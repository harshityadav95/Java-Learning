import java.util.concurrent.atomic.AtomicInteger;

public class CommonCounter{
	
	private AtomicInteger firstNum = new AtomicInteger(0);
	private int secondNum = 0;
	
	public void incrementCounter() {
		
		firstNum.incrementAndGet();
		secondNum++;
	}
	
	public int getFirstNum() {
		return firstNum.get();
	}
	
	public int getSecondNum() {
		return secondNum;
	}
}