import java.util.ArrayList;

public class TestGenerics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	ArrayList<?> obj=new ArrayList<String>();
	obj.add("Data1");
	obj.add("data2");
	obj.add(new Integer(10));
	
	}

}
