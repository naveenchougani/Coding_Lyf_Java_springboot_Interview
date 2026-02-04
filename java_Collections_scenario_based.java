//Q1. You have a list of recently visited URLs. The order of visits matters, 
//   but a URL should only appear once. Which collection will you use and why? 

// Solution is LinkedHashSet 
// 1. It preserves the order/insertion order
// 2. Being Set implemention, duplicates are not allowed
// 3. It moslty have O(1) time complexity for add, remove, contains operation

// Sample 

        public class RecentURLs {
            public static void main(String[] args) {
                LinkedHashSet<String> recentUrls = new LinkedHashSet<>();

                recentUrls.add("https://example.com");
                recentUrls.add("https://google.com");
                recentUrls.add("https://example.com"); // duplicate, ignored
                recentUrls.add("https://openai.com");

                System.out.println(recentUrls);
                // Output: [https://example.com, 
                         //  https://google.com, 
                        // https://openai.com]
            }
        }

===============================================================================================================

//Q2.  You are building a leaderboard where player names are keys and scores are values. You need 
// constant-time lookup by name and to iterate over them in sorted order of names. Which collection type works here?

// Here 1. they need constat time look up by name(i.,e key)
    // for this we can use HashMap
    // 2. They want to iterate over them in sorted order of names(.i.e, keys)
    // TreeMap automaticallly keeps keys sorted according to their natural ordering or based on custom comparator
    // As Treemap look up is O(log n) due to their sorted ordering atleast half the tree need traversing
    //, but if sorting is your requirement then, go for Treemap
    
    public class Leaderboard {
    public static void main(String[] args) {
        TreeMap<String, Integer> leaderboard = new TreeMap<>());
                                            // new TreeMap<>(Comparator.reverseOrder());
                                            // this would sort keys reverse way

        leaderboard.put("Alice", 1200);
        leaderboard.put("Charlie", 1500);
        leaderboard.put("Bob", 1400);

        // Iterates in sorted order of names
        //In enhanced for loop, var can infer the entry as Map.Entry<String,Integer> . So no need to type all
        for (var entry : leaderboard.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Lookup by name
        System.out.println("Bob's score: " + leaderboard.get("Bob"));
    }
}


===============================================================================================================

// Important and Interesting ------>

//Q3. You have a List<Integer> with 1 million numbers. You need to remove all numbers less than 100 while iterating.

// The above question indirectly testing your ability to detect possible issue while we perform above operaiton

    List<Intege> numbers=Arrays.asList(1,2,.....);
    
    // Under the hood it uses Iterator and  read only method do not result concurrent modification
    for(Integer number:numbers){
      System.out.println(number);
    }
    
    //If you want to edit any values during traversing use Iterator
    
    Iterator<Integer> it=numbers.iterator(); // to creat iterator 
    
    int currentValue;
    
    while(it.hasNext()) {
        
      currentValue = it.next(); // always returns the current elemetn
      
      if(currentValue<100) {
        it.remove();  // always removes the element it returned last
                    // so without calling it.next() , if you directly calls remove() it results IllegalState exception
        }
    
    }
    
    // Iterator works with map on an entrySet or keySet
      Map<String,Integer> scores=new HashMap<>();
      scores.put("Naveen",90);
      scores.put("Santosh",99);
      
      Iterator<Map.Entry<String,Integer>> = scores.entrySet().iterator();
      while(it.hasNext()) {
      
      Map.Entry<String,Integer> scoreEntry=it.next();
          if(scoreEntry.getKey.toLowerCase().contains("naveen") && scoreEntry.getValue()<90)
          {
              it.remove();
          }
      }
      
      // another version using removeIf  -> mostly prefer this..... it uses iterator under the hood
      score.entrySet().removeIf(entry->entry.getKey().contains("Nav") && entry.getValue()<90);
      
    // Even keySet() and values() of Hashmap also remoev the entire Entry<Key,Value>
    // but in value(), the first occurance is removed.. 
    
    
    // All the above are FAIL-FAST iterators means.. they produce error if we modify during for loop
    
    // But FAIL-SAFE iteratiors are there, means we can modify during for loop itself
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("A", 1);
map.put("B", 2);

for (String key : map.keySet()) {
    if (key.equals("A")) {
        map.put("C", 3);   // new entry
        map,put(key,map.get(key)+10); // update value for that key A
        
        // to modify a key, we have to delete that entry and put new entry
        // as new key is an object and its hashcode may be different and its bucket may result different
        // hence remove old key and put new key to maintain the data structure
        
        // Even if you directly modify the key value inline with a new object
        // the new object hashcode would refer to new bucket where value is nothing
        // hence it results in no entry . So always avoid mutable object in keys to avoid this issue
        
        //Thas why mostly choose string in map keys to avoid the above issue
        // As you can update the value of same key... if you try to use put method
        // if you use another string automatically result in new entry
        // Same key → safe value update.
        // New key → safe new entry.
        // in the same way any objet shoudl be final and its fields shoudl be final to works as expeted
        // Class also should be final to make sure class is not extended 
        //  So that we can avoid subclass overriding hashcode or equals method
        // And now we can place this subclass instance in the Parent class as key
        // This is accepted as per java inheritance. Now subclass has mutable fields used the hashcode
        // Now we can update them and results in the entry list not avialable in the bucket
        
    }
}
    
===============================================================================================================

// Q4. top 3 performers in descending order of map collections, using stream it will be helpful

    // use can use Map.Entry.comparingByKey()ByValue(Comparator.reverseOrder()) works on stream of entries
    
    scores.entrySet().stream()
                      .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                      .limit(3)
                      .forEach(e->System.out.println(e.getKey()+"-"+e.getValue()));

===============================================================================================================

//Q5. You have two lists of customer emails. Merge them into one, preserving order of first 
//appearance, without duplicates. Which collection will you use? 

// Use LinkedHashSet (i.,e set) will not keep duplicates and also preserve insertion order


===============================================================================================================

//Q6. You have a string and need to find the first character that appears only once. Which collection 
//will help track occurrence and maintain original order efficiently?
  
// LinkedHashMap<String,Integer> would keep the original order helps to store char and occurance

  Map<Character,Integer> map=new LinkedHashSet<>();
  
  for(char ch:str.toCharArray()) {
      map.put(ch,map.getOrDefault(ch,0)+1);
     }
    
  for(var entry:map.entrySet()) {
      
      if(entry.getValue()==1 {
          System.out.println(entry.getKey());
          }
      }

===============================================================================================================
//Q7. You are building a real-time logging system that stores only the last 100 log entries in memory. 
//Old entries should automatically be removed as new ones are added. Which collection will you choose?

// The problem statement asks that we need a collection
// That would store 100 entries, (assume fixed size)
// Now if we enter 101, an old entry should be removed

// Assume LinkedList works.. but slow due to extra memory for node objects
// Array Dequeu is fast with with insertion and deletion with both ends

// Because Queue basically lets you add from last.. remove from head.. Like a queue line
// Stack means adding and removing from the same end -> example browser back button,pile of plates


// Dequeu mean you can add or remove elements both the ends
// Insert  (offerFirst()/offerLast or addFirst()/addLast() ) -> offer -> true or false, add-> IllegalStateException
//Remove (pollFirst()/pollLast() or removeFirst()/removeLast()) ->  poll -> null, remove-> NosuchelementException


public class Test { 
    private static final int MAX_SIZE = 5; 
    private Queue<String> logs = new ArrayDeque<>(); 
 
    public void addLog(String log) { 
        if (logs.size() == MAX_SIZE) { 
            logs.pollFirst(); // remove oldest 
        } 
        logs.offerLast(log); // add newest 
    } 
 
    public void printLogs() { 
        logs.forEach(System.out::println); 
    } 
 
    public static void main(String[] args) { 
     Test logger = new Test(); 
 
        for (int i = 1; i <= 10; i++) { 
            logger.addLog("Log " + i); 
        } 
 
        logger.printLogs(); // prints last 100 logs 
    } 
} 
===============================================================================================================
//Q8. Given a list of product IDs sold in a day, you want to find the product sold the most. Which 
//collection will you use, and what will be your approach? 

// Simple.. take a hashmap and store id and its no of occurances for the id.

 Map<Integer,Integer> map=new HashMap<>();
 for(int id:saleIds){
 
 map.put(id, getOrDefault(id,0)+1);
 
 }
 
 int maxProduct=-1,maxSales=-1;
 for(var entry:map){
  if(entry.getValue()>maxSales){
      maxSales=entry.getValue();
      maxProduce=entry.getKey();
      }
 }
 System.out.println(maxProduce+"->"+maxSales);
 
 
===============================================================================================================

//Q9. LRU Cache Implementation
//Immplement a cache where most recently used item should stay
// and least recently used should be evicyed once capacity is reached.
//Which java collection can do this with minimal custom code

class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int
    
    public LRUCache<k,V>(int capacity) {
    super(capacity,0.75,true); // size,load factor, accessOrder
    // access order ensures to move every input to the last node..
    this.capacity=capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    
    //Evict the oldest entry when size exceeds capacity
    
    return size()>capacity;
    
    }

}
    
        public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B"); 
        cache.put(3, "C");
        cache.get(1); // Moves 1 to most recent
        cache.put(4, "D"); // Evicts 2 key (least recently used)

        System.out.println(cache); // Output: {3=C, 1=A, 4=D}
        
    }
}

   // A , B, C -> and access order is true which means, everytime you make put or get
   // that item is moved to the end of the list(marking most recent used)
   //removeEldestEntry is called after every put, and if true, head is removed
   
   // A,B,C is size==capacity
   //get(1) means B,C,A ( as A is accessed it is moved to last node considering latest access)
   // put(4,'D') -> checks eldest entry size 4 > 3 true so removes B
   // Now C A D.. this is how LRU cache works
===============================================================================================================

//Q10. You need to schedule jobs where each job has a priority number. Highest priority should be executed first, lowest last. 

// We need a collection that would work based on priority i mean sorting based on a property

// We can take priority Queue as its operations are faster than Treemap. Follow below code

PriorityQueue<Job> queue=new PriorityQueue<>(q1,q2->Integer.compare(q2.priority,q1.priority));

queue.add(new Job("naveen",1);
queue.add(new Job("karthik",2);
queue.add(new Job("Puli",3);

while(!queue.isEmpty()) {

Job job = queue.poll();

System.out.println("Executing job "+job);

}

// This is more sort of running the threads in the parallel.. coresize would pickup that many jobs from queue
// ThreadPoolExecutor ex=new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, queue);
//executor.prestartAllCoreThreads(); // start workers
//executor.shutdown();

===============================================================================================================

//Q11. Detecting Duplicate usernames in a stream of usernames.

// Hashset is very fast and returns in O(1) without scanning the list.

    public boolean register(String username) {
    
    if(userNamesSet.contains(username) ) {
    System.out.println("Username already taken: "+username);
     return false;
        }
        
        System.out.println("REgistered : "+username);
        usernamesSet.add(username);
        return true;
    }

===============================================================================================================

//Q12. Auto-Suggest in Search

// Use a TreeSet which stores unique values in the dictionary order or natural order of that object

// This is helpful to retrieve a range of queries 

import java.util.*;

public class Test{
    public static void main(String args[]){
        TreeSet<String> products=new TreeSet<>();
        products.add("apple"); 
        products.add("apricot"); 
        products.add("banana"); 
        products.add("blueberry"); 
        products.add("blackberry"); 
        products.add("cherry"); 
        
        String prefix="bl";
        String end = prefix+Character.MAX_VALUE;// all strin with prefix
        
        NavigableSet<String> suggestion=products.subSet(prefix,true,end,true);
        System.out.println("Suggestion for prefix "+prefix+" : "+suggestion);
        
    }
}

// subSet(fromElement, inlcudeFromElement,toElement,includeToElement) returns a NavigableSet<Object>


===============================================================================================================

//Q13. Thread Safe List
// Multiple threads are adding elements to a list at the same time. Which collection will you choose?
// CopyOnWriteArrayList would enable thread safe read adn writes
// But avoid writes as they copy entire array. So search for alternate for write
// Choose for read and one thing is it will not throw concurrent modification exception
// But Collections.synchronizedList(arrayList) will throw concurrent modification exception
// It will not copy entire array everytime you write.. just makes the block synchronized when you put
// when you go for compound operations in a enhanced for loop , just make it synchronized for synchronized list
// else it will throw concurrent modification exception.

import java.util.*;

import java.util.concurrent.*;

public class Test{
    public static void main(String args[]) throws InterruptedException{
        List<String> list=new CopyOnWriteArrayList<>();
        
       Runnable task=()->{
           String thread=Thread.currentThread().getName();
           for(int i=1;i<5;i++){
               list.add(thread+" -> "+i);
           }
       };
        
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        Thread t3=new Thread(task);
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        t2.join();
        t3.join();
        
        System.out.println(list);
    
    }
}
 

===============================================================================================================

//Q14. Multiple threads are recording the API hits for different users in real time. Which collection you 
// would use to keep counts without explicit synchronization?  

import java.util.concurrent.*;

import java.util.concurrent.atomic.*;

public class Test{
    
private static final ConcurrentHashMap<String,AtomicInteger> userHits=new ConcurrentHashMap<>();
    
    private static void recordHits(String user){
        userHits.computeIfAbsent(user,k -> new AtomicInteger(0)).incrementAndGet();
    }
    
    public static void main(String args[]) throws InterruptedException{
     
        
       Runnable task=()->{
          
           for(int i=1;i<100;i++){
               recordHits("user1");
               recordHits("user2");
               recordHits("user3");
               
           }
       };
        
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        Thread t3=new Thread(task);
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        t2.join();
        t3.join();
        
        System.out.println(userHits);
    
    }
}

===============================================================================================================
