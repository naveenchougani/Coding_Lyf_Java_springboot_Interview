/******---Static Blocks--- always gets executed as per their order ""Before main method runs"" ******/
/****---Instance Blocks--- always gets executed as per their order ""Before Constructor runs"" ******/



public class Main { 
      static { 
      System.out.println("Static Block before static variable");   // first this gets printed
      } 
static int x = Init();           // second this variable is initialized by invoking the init method
      static { 
      System.out.println("Static Block after static variable ");   // third this gets printed
      } 
      private static int Init() { 
      System.out.println("Static variable initialization");  // second
      return 1; 
      } 
    public static void main(String[] args) {
      System.out.println("main method");  // finally this gets printed
        } 
} 

// Simply static blocks/initializations "gets executed as per the code" "before main method runs"

==========================================================================================================

  public class Main { 
            { 
            System.out.println("Instance Block before variable initialization");   // First
            }
    int x = instanceInit();   // Second
              { 
              System.out.println("Instance Block after variable initialization");  // Third
              }
        Main() { 
        System.out.println("Constructor");   // Last Fifth 
        } 
              { 
              System.out.println("Instance Block after  constructor");   //Fourth
              }
          private int instanceInit() { 
          System.out.println("Instance Variable Initialization");    //second
          return 10; 
          } 
public static void main(String[] args) { 
            new Main(); 
      } 
} 

// Finally constructor will be called lastly, and instance block/initializations are executed as per the code
==========================================================================================================

  public class Main { 
    
    int y=30;
    { 
  System.out.println("Instance Block before variable initialization"+y);
}
int x = instanceInit();  
  { 
  System.out.println("Instance Block after variable initialization");
  }
        Main() { 
        System.out.println("Constructor"); 
        } 
{ 
System.out.println("Instance Block after  constructor");
}
    private int instanceInit() { 
    System.out.println("Instance Variable Initialization"); 
    return 10; 
    } 
        static { 
        System.out.println("Static Block will be called first before instance block"); 
        }
public static void main(String[] args) { 
  
  new Main();     // First static block then rest of instance blocks executed as per their order in the code
  
  //new Main();  // Only Static block gets executed before main runs
  } 
} 
==========================================================================================================
