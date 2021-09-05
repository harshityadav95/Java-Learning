# 10. Strings

### String vs StringBuilder vs StringBuffer

| Index | String | String Buffer | String Builder |
| :--- | :--- | :--- | :--- |
| Storage | Constant String Pool | Heap | Heap |
| Modifiable | Immutable | Mutable | Mutable |
| Thread Safe | Yes | Yes | No |
| Speed | Fast | Very Slow | Fast |
| Synchronized | Yes | Yes | No |

### Substring 

```text
System.out.println(S.substring(start,end));
```

### Capitalize first word in Line 

```text
        /* Enter your code here. Print output to STDOUT. */
        System.out.println(A.length()+B.length());
        System.out.println(A.compareTo(B)>0?"Yes":"No");
        System.out.println(A.substring(0,1).toUpperCase()+A.substring(1,A.length())+" "+B.substring(0,1).toUpperCase()+B.substring(1,B.length()));
```

### Sliding Window String Match



```text
        String window =s.substring(0,k);
        smallest= window;
        largest = window;
        
        for(int i=1;i<=(s.length()-k);i++)
        {
            window=s.substring(i,(i+k));
            
            if(window.compareTo(smallest)<0)
            {
                smallest=window;
            }
            if(window.compareTo(largest)>0)
            {
                largest=window;
            }
            
        }
```

