
@FunctionalInterface 
interface MyFunc { 
    void test(); 
    default void show() { 
        System.out.println("Default"); 
    } 
} 

public class Test { 
    public static void main(String[] args) { 
        MyFunc f = () -> System.out.println("Lambda"); 
        f.test(); 
        f.show(); 
    } 
} 

//op: Lambda,default. And functional interface can have multiple Default/Static methods
===================================================================================

@FunctionalInterface 
    interface Parent { 
    void baseMethod(); 
        } 
        
@FunctionalInterface 
interface Child extends Parent { 
    // No new abstract methods allowed except override 
} 

class Test { 
    public static void main(String[] args) { 
        Child c = () -> System.out.println("Child"); 
        c.baseMethod(); 
    } 
} 

// It works as child interface class also contains only one abstractc method of parent 

===================================================================================

    /* Important and Interesting*/
    // Lamda leaves on heap so and class fields (instance/static) are can be referenced from lamda
    //But local variable are always copied in lambda but cannot refer. 
    //Because lamda lives even after method is executed means local variable of method are no more available
    // This would result in runtime error. And Local variable are made final 
    //Just to avoid any manipulation to the local variable and be in sync they are final if once used in lambda
    
public class LambdaCaptureValueDemo{
        int instanceVariable=10;  // this lives on heap
static  int staticVariable=20;  // this lives on heap

Runnable runDemo(){
    int localVariable =30;  
    Runnable localLambda= ()->{
        System.out.println("Local Value "+localVariable);
        System.out.println("instance Value "+instanceVariable);
        System.out.println("static Value "+staticVariable);
    };  
    
    localvariable=0;// not allowed as lambda copies local value.. and lambda can outlive the method.. 
                    //so to avoid dead stack reference,, it copies the value and makes it final
    return localLambda;
}
    public static void main(String[] args){
        LambdaCaptureValueDemo obj=new LambdaCaptureValueDemo();
        Runnable runLambda=obj.runDemo();
       runLambda.run();
       obj.instanceVariable=50;
       obj.staticVariable=60;
       runLambda.run();
    }
   
}


===================================================================================