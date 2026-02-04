class Main { 
    void show(int a, long b) {          
        System.out.println("int, long"); 
    } 
    void show(long a, int b) { 
        System.out.println("long, int"); 
    } 

    public static void main(String[] args) { 
        Main t = new Main(); 
        t.show(10, 20);    // Results in Ambiguous reference error
                           // as 10,20 can be int,long (or) long, int at the same time
        }     
    }
==========================================================================================================================
class Test { 
    void show(String s) { 
        System.out.println("String"); 
    } 
    
    void show(String... s) { 
        System.out.println("Varargs"); 
        } 
        
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.show("hello");   //Overloading can be determined by signature, type, no of args
                         // above there is one method with one string arg.. hence it is called
                         // output : Sting
        }
    }
==========================================================================================================================

/* Important and interesting */

class Test { 
    void show(Integer i) {     // if both are wrapper,one wrapper must be of its boxing type else argument mismatch error
        System.out.println("Integer"); 
    } 
    
    void show(long l) {      // int or long or float or double is accepted against wrapper
        System.out.println("long"); 
    } 
    
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.show(10); // Java always choses exact match or its primitive type(float,double,long) against wrapper ,
                   //  In Wrapper, it must refer to Integer and its specific wrapper, else argument mismatch error compilation failed
    } 
} 
==========================================================================================================================

/*Important and Interesting*/

class Test { 
    void show(String s) { 
        System.out.println("String"); 
    } 
    void show(Object o) { 
        System.out.println("Object"); 
        } 
        
     //    void show(Integer i) {              // along with String, Integer
    //        System.out.println("Integer");   // show(null) results in the ambigious error
     //       }                                // as null can refer to both String and Integer same time
        
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.show(null);          // null can refer to String and Object, but String is more specific
        t.show("CodingLyf");   // string can refer to String as more specific
        
    } 
}
==========================================================================================================================
    
    /*Overloading resolves at Compile time*/
    /*Every class, even subclass is of different type or datatype*/
    
    class A { }
    class B extends A { } 
    class Test { 
    
        void show(A a) {               //This method is A class type parameter
            System.out.println("A"); 
        } 
        
        void show(B b) {             //This method is B class type parameter different from A
            System.out.println("B");     // So clearly its method overloading, not overiding
        } 
        
    
        public static void main(String[] args) { 
            A obj = new B();       
            Test t = new Test(); 
            t.show(obj);  // Overloading happens at Compile time 
                          // During compile time, Reference type is picked to resolve amnbiguity
                           //   when upcasting is used
                          // Hence "A" will be printed
        } 
    }

==========================================================================================================================

    /* Method OVerloading always resolved using Reference type in Upcasting scenario */

    class Test { 
    
            void show(Object o) { 
                System.out.println("Object"); 
            } 
            
        void show(String s) { 
            System.out.println("String"); 
        } 
        public static void main(String[] args) { 
            Object obj = "Hello";   // Overloading happens based on Reference type
            Test t = new Test(); 
            t.show(obj);     // Here obj is Object, hence "Object" will be printed
        }                    // Even though it holds string value,,, reference is used to resolve method call
    } 

==========================================================================================================================

