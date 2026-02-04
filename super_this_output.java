
/* Compiler will not put no arg constructor if arg constructor is defined */

class A {                
    A(String s) {                 // third compiler finds here arg constructor is there
        System.out.println("A");  // hence it shows compiler error saying "actual and formal arguments differ in length"
    } 
} 
class B extends A { 
    B() {                    // Second : here automatically, compiler puts parent no arg constuctor
                            // super();
        System.out.println("B Default"); 
    } 
} 
public class Test { 
    public static void main(String[] args) { 
        new B();   // First ->  it goes to B no arg constructor
    } 
} 

// Solution is simple.. put an exta no arg A() construcor in A class explicitly
// Else.. directly call super class constructo with super(args) in B(), issue is resolved

===============================================================================

    class A { 
        A() { 
            System.out.println("A"); 
        } 
    } 
class B extends A { 
    B() { 
        System.out.println("B"); 
    } 
} 
public class Test { 
    public static void main(String[] args) { 
        new B(); 
    } 
} 

// OUTPUT IS : A B

===============================================================================

/*Important and Interesting*//*Important and Interesting*//*Important and Interesting*/
/*Important and Interesting*/

/* this() means, it is calling another same constructor which will call super constructor */

/* Hence compiler will not put super before this, else this results in 2 calls to super constructor */

/* Which is illegal as but the same superclass fields would be re-initialized,
potentially causing data loss, resource leaks, or inconsistent state. */


/* in constructor  the first statemetn should be "this" or "super" */
/* if you do not specify anythign in child, "super" is placed automatically by compiler */
/* if you specify this, compiler will not place any super constructor */

    class A { 
        A(int a) { 
            System.out.println("A "+a); 
        } 
    } 
    class B extends A { 
        B() { 
            this(10);                         // compiler sees this, and avoids super() call    
            System.out.println("B Default"); 
        } 
        B(int b) { 
            super(b); 
            System.out.println("B int"); 
        } 
    } 
    public class Test { 
        public static void main(String[] args) { 
            new B(); 
        } 
} 

// As above code is following constructor chaining correclty.. it will print below answer
// A 10, B int, B Default


===============================================================================

    class A { 
        int x = 10; 
    } 
class B extends A { 
    void display() { 
        x = 20;       //without datatype,  directly accessing variable always refers to the parent variable.
    //int x=10          // this would create local copy to this method
        
        System.out.println(x); 
        System.out.println(super.x); 
    } 
} 
public class Test { 
    public static void main(String[] args) { 
        new B().display();   // prints 20,20
    } 
}

===========================================================================

    /* Diamond problem example and why multiple inheritance not supported */

    class A { 
        int x = 10; 
    } 
    
class C{
    int x=30;
}

class B extends A,c { 
    x = 20;          // here java compiler gets ambiguity , this is diamond problem 
    }                // hence in classes, multiple inheriatance is avoided 
} 

=============================================================================
/*Important and Interesting*/
/*Interface variable are by default public static final*/
/*In Multiple inheritance of interfaces, we can only call methods that are implemented*/

public class Test {
    public static void main(String[] args) {
        D obj = new D();
        obj.print();
    }
}
interface A {
    default void print()
    {
        System.out.println("A interface");
    }
    int VALUE = 10; // public static final by default
}

interface B extends A {
     default void print()
    {
        System.out.println("B interface");
    }
    int VALUE = 20;
}

interface C extends A {
     default void print()
    {
        System.out.println("C interface");
    }
    int VALUE = 30;
}

class D implements B, C {
    public void print() {
        
        //A.super.print(); unlike class, in interface, we cannot call interface methods
                            // that are not implemented by the class
        
        B.super.print();    // same methods are to be explicitly mentioned in the same methiod
        C.super.print();   // we can specify which specific interface method with name.super.method()
        System.out.println(A.VALUE);// As interface variable are tied to interface
        System.out.println(B.VALUE); // we can directly call them using interface name
        System.out.println(C.VALUE); 
    }
}

/*interfacename.variaables,  interfacename.super.methodname() of implemented interfaces*/

======================================================================
