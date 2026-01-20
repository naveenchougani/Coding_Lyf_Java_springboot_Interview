int[] a = {1, 2, 3}; 
int[] b = {1, 2, 3}; 
System.out.println(a == b);               // FALSE as arrays are object types 
System.out.println(Arrays.equals(a, b));   // TRUE as Arrays.equals compares content of arrays

=========================================================================================

  //*****  java passes its arguments as pass by value.. i.e,.  takes a copy of primitive value (or) copy of object reference ***///
  //*****  this results two important things.. one shared state among methods and mutation of the objects state ***/////
  ///****  Reassignment would just reflect to the current method, will not reflect original object ensuring no data loss **////

class Main { 
public static void main(String[] args) { 
  int a = 50; 
  change(a); 
  System.out.println(a);     // an example to show, arguments are "passed by value..." ie copy of a is passed, hence a remains unchanged
    } 
  public static void change(int x) { 
    x = 100; 
  } 
} 

=========================================================================================

  
public class Test { 

public static void main(String[] args) { 
  StringBuilder sb = new StringBuilder("Coding"); 
  change(sb); 
  System.out.println(sb);    // Coding LYF  as called method also working on the original reference
  } 
  public static void change(StringBuilder sb) { 
    sb.append(" Lyf"); 
    } 
}  
  
=========================================================================================

public class Test { 

  public static void main(String[] args) { 
    StringBuilder sb = new StringBuilder("Original"); 
    reassign(sb); 
    System.out.println(sb);  // "Original" as the called method is replacing the original reference with newly created object reference
  } 
  public static void reassign(StringBuilder sb) { 
    sb = new StringBuilder("New"); 
  } 
}   
=========================================================================================
public class Test { 

public static void main(String[] args) { 
    int[] nums = {1, 2, 3}; 
    modify(nums); 
    System.out.println(Arrays.toString(nums)); // 99,2,3 as modification is happening on original reference
  } 
  
  public static void modify(int[] arr) { 
    arr[0] = 99; 
  } 
} 
=========================================================================================
  
public class Test { 
    public static void main(String[] args) { 
      int[] nums = {1, 2, 3}; 
      modify(Arrays.copyOf(nums, nums.length)); 
      System.out.println(Arrays.toString(nums)); // 1,2,3 as Arrays.copyOf results in new object reference
                                                  // with original array content.. hence change is not visible
    } 
     public static void modify(int[] arr) { 
        arr[0] = 99; 
    } 
}  
=========================================================================================

  
  class Person { 
    String name = "Alex"; 
} 
public class Test { 
      public static void main(String[] args) { 
      final Person p = new Person(); 
      p.name = "Sam"; 
      System.out.println(p.name); 
  } 
} 

(or)

final class Person {             // even if class is FINAL  we can change object state or content
  String name = "Alex";
  public String toString(){
      return name;
  }
} 
public class Main { 
  public static void main(String[] args) { 
   Person p = new Person(); 
  p.name = "Sam"; 
    // p=new Person; allowed as final class just avoids the inheritance
  System.out.println(p.toString());
  change(p);
  System.out.println(p.toString()); 
  } 
  static void change(Person d)
  {
    //d=new Person(); allowed
      d.name="naveen";
  }
} 

(or)

class Person { 
    String name = "Alex";
    public String toString(){
        return name;
    }
} 
public class Main { 
public static void main(String[] args) { 
     final Person p = new Person();    // Even if Object variable is FINAL , we can change content or object state
    p.name = "Sam"; 
    //p=new Person; // Error cannot reasign the value to final variable
    System.out.println(p.toString());
    change(p);                                // JUST we cannot reassin any value to it in the same bloock  
    System.out.println(p.toString()); 
} 
  static void change(Person d)
  {
     // d=new Person();  allowed
      d.name="naveen";
  }
} 
=========================================================================================

