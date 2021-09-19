# Object Class

### getClass \(\)

```text

```

### hashcode\(\) + equals\(\)

```text
import java.io.*;
import java.util.*;


class Main {
  public static void main(String[] args) 
	{

		System.out.println("Start of the program ");

		Pen p1 = new Pen(1,"blue");
		Pen p2 = new Pen(1,"blue");

		System.out.println(p1);
		System.out.println(p2);

		System.out.println(p1.equals(p2));

		HashSet<Pen> box= new HashSet<>();
		box.add(p1);
		box.add(p2);
		
		System.out.println(box);

		System.out.println("End of Program");
		
  }
}
class Pen{

	int cost;
	String type;

	public Pen(int cost, String type)
	{
			this.cost=cost;
			this.type=type;
	}

	@Override
	public boolean equals(Object obj)
	{
		Pen temporary= (Pen) obj;

		boolean return_value= (this.cost == temporary.cost) &&
		this.type.equals(temporary.type);
		return return_value;

	}
	@Override
	public int hashCode()
	{
		return cost+type.hashCode();
	}

}
```

### wait\(\)



### toString\(\)



### clone\(\)



### 



### finalize\(\)



### notify\(\)



### notifyall\(\)







