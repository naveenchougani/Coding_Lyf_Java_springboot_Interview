// Q1. If a customer is a "Premium" member and purchases more than 5 items, give 20% 
discount. If not premium but buys more than 10 items, give 10% discount. Otherwise, no 
discount. 

// Here, Customer might have 2 types of subscription, and based on no of item, give discount
// Instead of if-else, we can go with swith along with ternary operator and it returns value

// code snippet

class SwitchDemo{
    
    public static void main(String[] args) {
    
        String subscriptionType="Premium";
        
        int itemsPurchased=7;
        
        int discount = switch(subsciptionType) {
        
        case "Premium" -> itemsPurchased>5 ? 20 : 0;
        default        -> itemsPurchased>10 ? 10 : 0;
        
        }
    }
}

=======================================================================================
//Q2. write ATM operations such as , Withdraw, Deposit,CheckBalance

  import java.util.*;
 
 public class Main{
     
    public static void main(String args[]) {
        
        double balance=5000.0;
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your choic: Withdraw,depoist,check_balance");
        
        String operation = sc.nextLine().toLowerCase();
        
        switch(operation){
            
            case "withdraw"->{
                System.out.println("Enter amount to withdraw");
                double withdrawAmount = sc.nextDouble();
                if(withdrawAmount<balance)
                {
                    balance = balance-withdrawAmount;
                    System.out.println("Withdraw Successful. New Balance "+balance);
                } else {
                    System.out.println("Insufficient funds");
                }
            }
            case "check_balance" -> System.out.println("Balance : "+balance);
            case "deposit" ->{
                System.out.println("Enter amount to deposit");
                double deposit = sc.nextDouble();
                balance+=deposit;
                System.out.println("Deposit successful. Current Balance"+balance);
            }
            default-> System.out.println("Invalid option");
                    
                }
    }
            
}

=======================================================================================
//Q3. Traffic signal with latest switch 

 import java.util.*;
 
 public class Main{
     
    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
    System.out.println("Enter your Traffic Light: Red,Green,Yellow");
        String trafficLight = sc.nextLine().toLowerCase();
        
        switch(trafficLight)
        {
            
            case "red"->System.out.println("Stop");
            case "yellow" ->{
                System.out.println("are Pedestrain Crossing?");
                boolean isWaiting=sc.nextBoolean();
                if(isWaiting){
                System.out.println("Pedestrain Crossing. Stop");
                }else{
                    System.out.println("Slow Down");
                }
            }
            case "green" ->{
                System.out.println("is Emergency Vehicle?");
                boolean isEmergency=sc.nextBoolean();
                if(isEmergency){
            System.out.println("Give way to emergency vehicle");
                }else{
                    System.out.println("Go");
                }
            }
            default-> System.out.println("Invalid option");                
        }
    }           
}


=======================================================================================
//Q4. Password checker using Character class functions

class PasswordChecker{
    public static void main(String args[]){
        String password = "Abc@1234";
        int upperCount=0,lowerCount=0,digitCount=0,specialCount=0;
        
        for(char ch: password.toCharArray()){
            
                if(Character.isUpperCase(ch)) upperCount++;
                else if(Character.isLowerCase(ch)) lowerCount++;
                else if(Character.isDigit(ch)) digitCount++;
                else specialCount++;
            
        }
        System.out.println(upperCount+"-"+lowerCount+"-"+digitCount+"-"+specialCount);
        if( password.length()>=8 &&
            upperCount>0 && digitCount>0 &&
            lowerCount>0 && specialCount>0) {
                System.out.println("Strong");
            } else{
                System.out.println("weak");
            }
    }
}

=======================================================================================
//Q5.  Print even and odd using two threads using semaphores

import java.util.*;
import java.util.concurrent.Semaphore;

public class EvenOddPrinter{
    private static int max;
    private final Semaphore even=new Semaphore(0);
    private final Semaphore odd=new Semaphore(1);
    
    private void printNumbers(){
        
        Thread oddThread=new Thread(()->
        {
           for(int i=1;i<=max;i+=2)
           {
               try{
                   odd.acquire();  // if permits are 0 in acquire state, in wait state
                                   // if permits are >0 in acquire state, entering synchronized block
                                   
                   System.out.println("Odd: "+i);
                   even.release(); // assign permit to it and wake up it and continue
               } catch(InterruptedException e){
                   Thread.currentThread().interrupted();
               }
           }
           
        });
        
        Thread evenThread=new Thread(()->
        {
           for(int i=2;i<=max;i+=2)
           {
               try{
                   even.acquire();  // if permits are 0 in acquire state, in wait state
                                   // if permits are >0 in acquire state, entering synchronized block
                   System.out.println("Even: "+i);
                   odd.release(); // assign permit to it and wake up it
               } catch(InterruptedException e){
                   Thread.currentThread().interrupted();
               }
           }
        });
        
        oddThread.start();
        evenThread.start();
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the max number");
        max=sc.nextInt();
    
        EvenOddPrinter printer=new EvenOddPrinter();
        printer.printNumbers();
    }
}

// (or)

// Using traditional approach wait and notify

import java.util.*;

public class EvenOddPrinter{
    private static int max;
    private int counter=1;
    
    
    private void printNumbers(){
        
        Thread oddThread=new Thread(()->
        {
            // wait(), notify(), notifyAll() are methods of Object.
            // whenever you call above methods, you must have an object to call them
            // And Synchronized on method level, or synchronized(this) block gives you an object for thread
            // so now wait() and notify () can be called with lock object, current this object
            // you must make this to avoid race condition and java enforces to follow this
            // Else you will get  IllegalMonitorStateException saying thread is not the owner
            // As respective thread is not acquired lock and trying to call wait and notify methods
            // Because threads can only execute wait notify method by acquiring locks.
            
            synchronized(this){  
           while(counter<=max)
           {
              while(counter%2==0){  // if it is even, i will wait nd handle spurious waits
               try{                
                   wait();           // releases lock and goes to wait state
               } catch(InterruptedException e){
                   Thread.currentThread().interrupted();
               }
              }
                System.out.println("Odd: "+counter++); // if its odd, print it
                notify();  // notify waiting thread(wakes up waiting thread) and continue execution
             }
           }
           
        });
        
        Thread evenThread=new Thread(()->
        {
            // Before calling wait notify, make sure you acquire a lock
            // lock can be custom object or current class object
            // Make sure all the threads are having same monitor object lock
            synchronized(this){  
            while(counter<=max)
           {
               while(counter%2!=0){ // if it is odd, i will wait  nd  handle spurious waits
               try{
                  wait();  // releases lock and goes to wait state
               } catch(InterruptedException e){
                   Thread.currentThread().interrupted();
               }
             }
              System.out.println("Even: "+counter++);
                notify(); // notify waiting thread(wakes up waiting thread) and continue execution
           }
        }});
        
        oddThread.start();
        evenThread.start();
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the max number");
        max=sc.nextInt();
    
        EvenOddPrinter printer=new EvenOddPrinter();
        printer.printNumbers();
    }
}


=======================================================================================
//Q6. Print Letters and number alternatively A,1,B,2.. using semaphores

import java.util.concurrent.Semaphore;

class PrintLetterNumber{
    private int num=1;
    private final Semaphore letter=new Semaphore(1);
    private final Semaphore number=new Semaphore(0);
    
    private void print(){
        
    Thread letterThread=new Thread(()->{
        for(char ch='A';ch<='Z';ch++)
        {
            try{
                letter.acquire();
                System.out.print(ch+",");
                number.release();
            }catch(InterruptedException e){
                Thread.currentThread().interrupted();
            }
        }
        
    });
    Thread numberThread=new Thread(()->{
        for(char ch='A';ch<='Z';ch++)
        {
            try{
                number.acquire();
                System.out.print(num+",");
                num++;
                letter.release();
            }catch(InterruptedException e){
                Thread.currentThread().interrupted();
            }
        }
    });
    
    letterThread.start();
    numberThread.start();
        
    }
    
    public static void main(String args[]){
        PrintLetterNumber printer=new PrintLetterNumber();
        printer.print();
        
    }
}

// or

// using traditional wait and notify approach

import java.util.concurrent.Semaphore;

class PrintLetterNumber{
    private int num=1;
    private boolean isLetter=true;
    
    private void print(){
        
    Thread letterThread=new Thread(()->{
        for(char ch='A';ch<='Z';ch++)
        {
            synchronized(this)
            {
                
            while(!isLetter)
            {
                try{
                wait(); 
                }catch(InterruptedException e){
                Thread.currentThread().interrupted();  
                    }
            }
             System.out.print(ch+",");
             isLetter=!isLetter;
             notify();
            
         }
        }
        
    });
    Thread numberThread=new Thread(()->{
         for(char ch='A';ch<='Z';ch++)
        {
            synchronized(this) {
            while(isLetter)
            {
                try{
                wait(); 
                }catch(InterruptedException e){
                Thread.currentThread().interrupted();  
                }
            }
             System.out.print(num+",");
             num++;
             isLetter=!isLetter;
             notify();
          }
        }
    });
    
    letterThread.start();
    numberThread.start();
        
    }
    
    public static void main(String args[]){
        PrintLetterNumber printer=new PrintLetterNumber();
        printer.print();
    }
}


=======================================================================================

// Q7. Producer Consumer problem
// assume producer means a thread generates/produces value, consumer consumes/recives the value
// Approach using BlockingQueue which one stores the value

import java.util.*;
import java.util.concurrent.*;

public class ProduceConsumerBQ{
    private final BlockingQueue<Integer> bq=new ArrayBlockingQueue
        <>(5);
    private int num=1;
    private static int count=1;
    
    private void produce() throws InterruptedException{
        bq.add(num); // this blocks if queue is full
        System.out.println("Produced "+num);
        num++;
    }
    
    private void consume() throws InterruptedException{
        int value = bq.poll(); // this blocks if queue is empty
        System.out.println("Consumed "+value);
    }
    
    public static void main(String args[]) throws InterruptedException{
        
        ProduceConsumerBQ bqpcd=new ProduceConsumerBQ();
        
        Thread producerThread=new Thread(()->{
            while(true){
                try{
                bqpcd.produce();
                Thread.sleep(500);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupted();
                }
                if(count==20) break;
                count++;
            }
        });
        Thread consumerThread=new Thread(()->{
            while(true){
                try{
                if(bqpcd.bq.size()>0){
                bqpcd.consume();
                }
                Thread.sleep(500);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupted();
                }
                if(count==20) break;
                count++;
            }
        });
        
        
        producerThread.start();
        consumerThread.start(); 
        producerThread.join();
        consumerThread.join();
        while(bqpcd.bq.size()>0)
        {
            bqpcd.consume();
        }
        }
}


// or

// using traditional approach
import java.util.*;

public class ProducerConsumerDemo{
    
    Queue<Integer> queue=new LinkedList<>();
    final int capacity=5;
    private int num=1;
    
    private synchronized void produce() throws InterruptedException{
        while(queue.size()==capacity){
            wait();
        }
        queue.add(num);
        System.out.println("Produced : "+num);
        num++;
        notify();
        
    }
    
    private synchronized void consume() throws InterruptedException{ 
        while(queue.isEmpty()){
            wait();
        }
        int value=queue.poll();
        System.out.println("Consumer : "+value);
        notify();
    }
    
    public static void main(String args[]){
        ProducerConsumerDemo pcd=new ProducerConsumerDemo();
        Thread produceThread=new Thread(()->{
            while(pcd.num<=pcd.capacity){
        try{
                pcd.produce();
                 Thread.sleep(500);// sleep after synchronized block as it released the lock
                                    // this ensures other thread can run during this sleep time,by acquiring released lock
                                    
                                    // if sleep used in synchronized block.. it will not release lock and starts executing after sleep time
                                    // without making the other thread to run
                                    
        } catch(InterruptedException e){
            Thread.currentThread().interrupted();
        }
            }
    });
    Thread consumeThread=new Thread(()->{
        while(pcd.queue.size()>0) {
        try{
                pcd.consume();
                 Thread.sleep(500);  // sleep after synchronized block as it released the lock
                                    // this ensures other thread can run during this sleep time,by acquiring released lock
        } catch(InterruptedException e){
            Thread.currentThread().interrupted();
        }
        }
    });
     
     produceThread.start();
     consumeThread.start();
    }
}

=======================================================================================

//Q8. Simulating Deadlock by two threads trying to lock two resources.

import java.util.*;
public class DeadlockTwoThreads{
    
    private static final Object resource1=new Object();
    private static final Object resource2=new Object();
    
    public static void main(String args[]){
        
        Thread t1=new Thread(()->{
            synchronized(resource1){
                try{
                System.out.println("Thread1 Locked by Resource1");
                Thread.sleep(100);
                synchronized(resource2){
                    System.out.println("Thread1 Locked by Resource1");
                } }catch(InterruptedException e){}
                
            }
        });
        
         Thread t2=new Thread(()->{
             try{
            synchronized(resource2){
                System.out.println("Thread2 Locked by Resource2");
                synchronized(resource1){
                    System.out.println("Thread2 Locked by Resource2");
                }
            } }catch(Exception e){}
        });
        
        t1.start();
        t2.start();
        
    }
}



//solution. Make sure the Thread1 and Thread2 acquires the both resources in the same order

// So, Thread2 waits untill all the locks released by Thread1 and proceed. This way deadlock avoided


import java.util.*;
public class DeadlockTwoThreads{
    
    private static final Object resource1=new Object();
    private static final Object resource2=new Object();
    
    public static void main(String args[]){
        
        Thread t1=new Thread(()->{
            synchronized(resource1){
                try{
                System.out.println("Thread1 Locked by Resource1");
                Thread.sleep(100);
                synchronized(resource2){
                    System.out.println("Thread1 Locked by Resource1");
                } }catch(InterruptedException e){}
                
            }
        });
        
         Thread t2=new Thread(()->{
             try{
            synchronized(resource1){
                System.out.println("Thread2 Locked by Resource2");
                synchronized(resource2){
                    System.out.println("Thread2 Locked by Resource2");
                }
            } }catch(Exception e){}
        });
        
        t1.start();
        t2.start();
        
    }
}

=======================================================================================

//Q9.Increment the shared counter with 10 threads. Final output should be 10,000.
// first go with traditional approach
import java.util.*;
public class DeadlockTwoThreads{
    
    private static int counter=0;
    private static final int threadCount=10;
    private static final int countPerThread=1000;
    
    
    public static void main(String args[]) throws InterruptedException{
        
        Runnable increaseCounterTask=()->{
            for(int i=0;i<countPerThread;i++)
            {
                counter++;
            }
        };
        Thread[] threads=new Thread[threadCount];
        
        for(int i=0;i<threadCount;i++)
        {
            threads[i]=new Thread(increaseCounterTask);
            threads[i].start();
        }
        
        for(Thread thread:threads)
        {
            thread.join();  // wait for all threads to complete
        }
        System.out.println(counter);
        
    }
}

// op is 9937,10000 like it is not reliable

// so go with AtomicInteger

import java.util.*;
import java.util.concurrent.atomic.*;

public class DeadlockTwoThreads{
    
    private static AtomicInteger atomicCount=new AtomicInteger(0);
    private static final int threadCount=10;
    private static final int countPerThread=1000;
    
    
    public static void main(String args[]) throws InterruptedException{
        
        Runnable increaseCounterTask=()->{
            for(int i=0;i<countPerThread;i++)
            {
                atomicCount.incrementAndGet();
            }
        };
        Thread[] threads=new Thread[threadCount];
        
        for(int i=0;i<threadCount;i++)
        {
            threads[i]=new Thread(increaseCounterTask);
            threads[i].start();
        }
        
        for(Thread thread:threads)
        {
            thread.join();  // wait for all threads to complete
        }
        System.out.println(atomicCount.get());
        
    }
}

// op:10000 always.

=======================================================================================

//Q10. Using Multiple threads, Withdraw from Bank Account avoid race conditions.
// just wrap or make withdraw and deposit synchronised methods. 
// Even multiple threads are assigned the above tasks, due to same lock exist on all methods
// only one thread can access any of the method at a time.. i.e, one method, one thread
// Resulting in non-race conditions

=======================================================================================
//Q11. Fetch User Details from Service A, and Fetch Users recent orders from service B
// we can utilze compleatable future.. and helps us to run tasks asynchronously and
// can join or combine results when ready.

import java.util.*;
import java.util.concurrent.*;

public class CompletableFutureExample{
    private static void simulateDealy(int duration) {
        try{
        Thread.sleep(duration);
        }catch(InterruptedException ignored){
            
        }
    }
    public static void main (String[] args) throws ExecutionException,InterruptedException{
        
        CompletableFuture<String> userFuture=CompletableFuture.supplyAsync(()->{
            simulateDealy(500);
            return "User:Naveen";
        });   // here itself the completablefuture starts running is seperate thread
            // making no-blocking for main thread
        
        CompletableFuture<String> orderFuture=CompletableFuture.supplyAsync(()->{
             simulateDealy(700);
            return "Orders:2";
        });
        
        CompletableFuture<String> combinedFuture=userFuture.thenCombine(orderFuture,(user,order)->{
            return user+" | "+order;
            }
            ); // here first get user details, then combine with orders future
        
        System.out.println(combinedFuture.get()); // get would block main thread untill results 
                                                    // are retrieved
    }
}

// this is just more of an idea wise how completablefuture works... Look for other things also

=======================================================================================
=======================================================================================
=======================================================================================
=======================================================================================

