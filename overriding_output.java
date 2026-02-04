class A { 
    public void msg() { 
        System.out.println("Class"); 
    } 
} 
interface B { 
    default void msg() { 
        System.out.println("Interface"); 
    } 
} 
class Test extends A implements B {             // Test class inherits A's msg , interface B's msg method
    public static void main(String[] args) {     // by default, interface all methods are Public, hence you will not get weaker access specifier exception
        Test m = new Test();                     // Always class method gets priority then the interface method.
        m.msg();                         // Class
    } 
} 

===============================================================================================================================
    interface A { 
    default void show() { 
        System.out.println("A"); 
        } 
    } 
interface B { 
    default void show() { 
        System.out.println("B"); 
        } 
    } 
class C implements A, B {   // java forces c to override show method to resolve conflict
    public void show() {    // overriding so java compiler will call this method
        A.super.show();     
        B.super.show();     // interface.super.method() will always call interface specific emthod
        System.out.println("C"); 
        } 
    } 
public class Test { 
    public static void main(String[] args) { 
        new C().show();      // C() leads to loading interface A,B and then show() method will call
        } 
    } 
// prints, A,B,C 

===============================================================================================================================

/*Important and interesting*/
abstract class Main { 
    public static void main(String[] args) {             // This runs because abstract class cannot instantiate, But can execute
        System.out.println("main method of abstract class. It executes without object");
    } 
 abstract private void greet();  // X This results in illegal combination of abstract and private
                                 // Because abstract method to be overridden in the sub classes. But subclass wont inherit private method
                                    //Hence abstract methods cannot be private
                                    
    static private void greet(){ // Can be accepted and executed in another static method 
    Sos("hi);
    }

} 

===============================================================================================================================

