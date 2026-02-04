
// If a thread subclass i,e, class extends thread should override run method
// else start method would create and completes thread without doing any work

public class Test implements Runnable { 
    public void run() { 
        System.out.printf("Naveen Thread "); 
    } 
    public static void main(String[] args) //throws InterruptedException  is not requires here
    { 
        Test obj = new Test(); 
        obj.run();  // regular method running in the main thread even Runnable instance
        
        //obj.start() ; // start is not a method of Runnable, it is of Thread class
    } 
} 

//or 

public class Test extends Thread { 
    public void run() { 
        System.out.printf("Naveen Thread "); 
    } 
    public static void main(String[] args) // throws InterruptedException because start method will not thrpow it 
    { 
        Test obj = new Test(); 
        obj.run();   // regular method running in the main thread even Thread instance
        
    // obj.start();  //This would have created new thread and executes run method
    } 
}

// op: Naveen Thread 
// Here run method would execute like a normal method, no thread is created
// Because start() method of Thread object would create new thread and executes run method
==============================================================================

    /*Important and Interesting*/
    
public class ThreadDemo implements Runnable{

public void run(){

System.our.println("Naveen");

}

public static void main(String[] args) // throws InterruptedException start method will not throw it
{
    
    Thread t=new Thread(new ThreadDemo());
        t.strat(); // will execute run method  by creating a new thread 
        t.start(); // this will throw IllegalStateException
    }

}
// op : Naveen (for first run)
//     IllegalState Exception(for second run)

// Why we cannot reuse the thread object to start again.. because
//Java Thread object is wrapper around os thread.. so when a thread executes
// OS thread is executing the run method, once finished, os thread is gone
// But thread objects lives in the heap with status Terminated with no association with OS thread
//  you can call methods like .getName(), .getId(), .getState() on the thread
// This is why you cannot reuse the same thread object to run and it throws Illeglastate exception
// If you want to run again you should again create a Thread object with our runnable instance to it
// So using ExecutorService framework is efficient in hanlding this..
==============================================================================
    
    // implements Runnable is redundant here,,
    
    public class Test extends Thread implements Runnable { 
        public void run() { 
            System.out.printf("CodingLyf "); 
        } 
        public static void main(String[] args) throws InterruptedException { 
            Test obj = new Test(); 
            obj.run(); 
            obj.start(); 
        } 
    }
    
    // above, run method would work as normal method with Thread instance
    // and obj.start() also creates new thread and executes run method
    // so Runnable is redundant
    
==============================================================================

//threadObject.join() might throw InterruptedException but not always

class ThreadJoinDemo{
    public static void main(String[] args) throws InterruptedException {
        
        Thread t1=new Thread(()->System.out.println("one"));
        Thread t2=new Thread(()->{
            try{
            t1.join();   // it waits until t1 completes and joins t2 thread
            System.out.println("two");
            }catch(Exception e) {
                System.out.println("Exception type:"+e.getCause());
            }
            }  );
        
        t2.start();  // starts the thread by running method
        t1.start();  // Startrs the thread and executes
        t2.join();  // main thread waits untill t2 completes and join main thread
        System.out.println("main"); 
    }
}

//so output : one, two , main

==============================================================================
//Deadlock scenario with joins

public class Main{
    public static void main(String args[]) throws Exception{
        
        Thread mainThread=Thread.currentThread();
        System.out.println("main thread started "+mainThread); // First 
        
        Thread regularThread=new Thread(()->{
            try{
            System.out.println("regular Thread started"); // Fourth : Now thread executes
            mainThread.join();                            // Fifth : Now it waits main thread to complete its execution
            System.out.println("regular Thread completed");    // At Thrid steps itself main is waiting for this thread
                                                               // now This thread wait for main thread which is waiting
                                                            // Untill either of anything gets interupted, they are in deadlock
            } catch(Exception ignored){}
        });
        
        regularThread.start(); //Second : creates thread and executes runnable implementation
        regularThread.join();  // Third :  main comes here directly without waiting for the above thread execution
                               // Now here main thread waits untill regularThread completes its execution
        System.out.println("main thread completed");
        
    }
}

// op:  main thread started -> 1st
//      regular thread started -> 4th  then deadlock

==============================================================================

        /*start method can only be run by Thread class object*/

    class CodingLyf implements Runnable { 
        public void run() { 
            System.out.println("Run"); 
        } 
    } 
class Test { 
    public static void main(String[] args) { 
        CodingLyf t = new CodingLyf(); 
                t.start();          // t is not a Thread object to run start method
                                    // Hence it shows compile time error saying not found
        
        // Thread t1=new Thread(t)    // this would solve the issue
          // t1.start()
        System.out.println("Main"); 
    }

==============================================================================

/* Tricky */ /**Instance field vs statoic field accessability*/

    class Test { 
        int a = 10;  // per Object
        static int b = 20; // per Class instances i.e, all objects have only one reference
         // i.e, any object can make changes to static but reflected for all the objects
         
        public static void main(String[] args) { 
            Test t1 = new Test(); 
            t1.a = 100; 
            
            t1.b = 200;   // now b is static and this value is reflected
                        // for all newly created objects...
            System.out.println("t1.a =" + t1.a + " t1.b =" + t1.b);  // 100,200
                        
            Test t2 = new Test(); 
            System.out.println("t2.a =" + t2.a + " t2.b =" + t2.b);  //10,200
                     // as b is modified by 1st object
        } 
    } 

==============================================================================

    /*static fields can only be class level, not method level or local scoped*/
    
    class Test { 
        static int i = 1; 
        public static void main(String[] args) { 
            static int i = 1; 
        } 
    } 
    
// static fields are always class level, compile time error

===============================================================================