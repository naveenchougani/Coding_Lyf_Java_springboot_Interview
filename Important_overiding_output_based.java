
class A { 
    void show() { 
    System.out.println("A"); 
    } 
} 
class B extends A { 
    void show() { 
    System.out.println("B"); 
    } 
    void greet(){
       System.out.println("Hi"); 
    }
} 
public class Main { 
          public static void main(String[] args) { 
          A obj = new B();      // UpCasting,  parent refers to the Child Object , child object to p - YES.
          obj.show();           // Methods always checked in the Reference Type i.e A class
                                // Then execute the method of the real Object i.e, B's method
            
          obj.greet();  //Method checked in A, not present , compile time error  
                        // always it checked in Parent or reference class, then execute object method
          } 
} 
/**  "Methods" always be "checked first in the Reference/Parent class", then execute overriden method if any in child class **/
/** Simply Overridden method is executed at runtim **/
========================================================================================================

  class A { 
        int x = 10;
        static int y=10;
        static void show() { 
              System.out.println("A static"); 
              } 
        } 
class B extends A { 
      int x = 20; 
       static int y=20;
          static void show() { 
              System.out.println("B static"); 
              } 
      }
public class Main { 
  public static void main(String[] args) { 
        A obj = new B();   // -> Child Object to Parent YEs 
        System.out.println(obj.x+" "+obj.y);  // 10 10 (Parent class A values)
        obj.show()   // Here A is reference and static method/ static variables/instance variable is of A type not object type
        }                                     
} 

/**  "Variable/Static variables/static methods" always be resolved at compile time by the Reference type, irrespective of object created **/

========================================================================================================

  class A {} 
  class B extends A {} 
  class C extends B {} 
  public class Test { 
  public static void main(String[] args) { 
  A a = new C();   // Child object to Parent/Grand Parent (A<-B<-C) (A<-C) -> YES as child contains all super class methods
  B b = (B) a;     // Same Child object to its immediate parent(B<-C) -> YES
    
  A obj_b=new B(); // Child objet to parent -> YES
    
  C c=(C)obj_b;   // Parent object to Child -> No (ClassCastException)
  C c1=new A();   // Grand Parent to Child -> No (compile time cannot convert error)
  C c2=new B();   // immediate parent to Child -> no same above error
  
  System.out.println("Cast successful"); 
  } 
} 

/** (DownCasting) "Child c=(Parent) p" or  -> run time class cast exception
                  "Child c= new Parent()"  -> compile time cannot convert exception
  is motsly discouraged as Super class may not contain all the extra methods of child class. 
  So compiler might throw runtime class cast exception / compile time errors **/
========================================================================================================

  class A { 
      private void show() { 
      System.out.println("A"); 
      } 
    protected void greet() { 
      System.out.println("hi"); 
      } 
    } 
class B extends A { 
    public void show() { 
    System.out.println("B"); 
        } 
     void greet() { 
      System.out.println("hi"); 
      }
  
    // public/protected void greet() { 
    //               System.out.println("hi"); 
    //             }
   // this could have solved the overriding issue
     
    } 
public class Test { 
    public static void main(String[] args) { 
    A obj = new B(); // Child object to Parent : YES
    obj.show();  // Checks in A class.. it is Privare, hence private method access compile time error
                // and no method overriding,as private methods are not inherited
      
    obj.greet();  // Checked in A exist, but weaker access modifier in B throws compile time error
                  // Hence overriding method must have same or higher access specifier
    } 
}
========================================================================================================
  class Parent {
    public void show() { ... } // Anyone can access this
}

class Child extends Parent {
    private void show() { ... } // ERROR: Trying to hide it
}
public class Test {
  public static void main(String[] args) { 
  Parent p = new Child(); 
  p.show();   // The compiler thinks this is public, but the object is actually private!
  }
}
//If the parent says, "Anyone can call the show() method" (by making it public),
//but the child tries to say, "Only I can call this method" (by making it private),
//the child is breaking the contract as it should be able to fulfill the parent class contract


/** OVerriding method must have higher access specifier is an example of Liskov Substitution Principle **/
// Hence compiler force to have equal or higher access specifier in overriding method
========================================================================================================

  class FilePrinter {
    // Contract: This method might throw an IOException
    void print() throws IOException { ... }
}

class NetworkPrinter extends FilePrinter {
    // ILLEGAL: This adds a new checked exception the caller doesn't know about
    @Override
    void print() throws SQLException { ... } 
}

FilePrinter fp = new NetworkPrinter(); // Polymorphism
try {
    fp.print();
} catch (IOException e) {
    // The compiler ONLY forces you to catch IOException and 
    // SQLException is never catched and program will crash
}
// Hence subclass should throw, lower or narrowed checked exception than parent
// As checked exception need try catch block.. 
// where compiler force you to handle parent class exception to make sure child also does the same
  // or its subclass / narrower exception to make that catch block is able to handle it
  // as sqlexception is cannot be catched again a IOException..

// Also subclass can throw any un-checked exception as try catch is not required  

========================================================================================================
class A {  
   int a = 20; 
      {
        System.out.println("A instance block : " + a);   
      }
    A() {   
    show(); 
    } 
      static{
           System.out.println("Parent static block");
      }
      void show() { 
      System.out.println("A show"); 
      } 
} 
class B extends A {
      B(){
         System.out.println("B construcor: "); 
    }
      int x = 10; 
      {
        System.out.println("B instance block show: " + x);   
      }
        static{
        System.out.println("child static block" );
        }
      void show() { 
      System.out.println("B show: " + x); 
      } 
} 
public class Main { 
    public static void main(String[] args) { 
        new B(); 
    } 
} 

//Parent static block
// child static block
// A instance block : 20
// B show : 0
// B instance block show: 10
// B construcor:

// 1. In Inheritance.. when child class constructor is called First Parent class loaded and static blocks executed
// 2. As Parent class is loaded, next Child class is loaded and its static block is executed
// 3. Child Constructor starts executing by allocating memory (new keyword) to instance variables with default values
// 4. Parent constructor is executed first,
// 5. If parent contains any static blocks/initializaiton, they will run first 
// 6. Hence it printed  A instance block : 20
// 6. Now next constructor statments execute as show method and its overridden in child class will execute
// 5. Child class method is executed but x vlaue is 0 as defaults "B show : 0"
// 6. Now after parent constructor, child class instance blocks/variables initialization are executed
// 7. Now next constructor statements if any are executed after the instance blocks/varianle initilizations are completed

//** imp, classes are loaded lazily
//  main method class is loaded
// new B() is hit.. and B extends A , so A is loaded and its staic block executed
// now B is loaded and its static block executed
// then new will allocate memory for B class, and calls parent class construcot as new B() statemetn executed
// now it allocates memory and runs instance blocks and then constructor statements of A,
// then B completes initialization and runs instance blocks and then constructor statements
========================================================================================================

  class A { 
    A() { 
    show(); 
    } 
    static void show() { 
    System.out.println("A show"); 
    } 
} 
class B extends A { 
    static int x = 10; 
    static void show() { 
    System.out.println("B show: " + x); 
    } 
} 
public class Main { 
    public static void main(String[] args) { 
    new B().show(); 
    } 
} 

// A show
// B show : 10

// As mentioned Static methods are not overridden, they are loaded when classes are loaded
// when B().show() is called first B constructor is called reuslting in calling A construcor
// A constructor call A show method
// After that B construcot is completed and object is ready and it called its own static show method
// B object show method results in B show: 10
========================================================================================================
