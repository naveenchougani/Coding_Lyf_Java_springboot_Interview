try { 
// code 
} catch (Exception e) { 
// handle 
} catch (ArithmeticException ae) {  // Compiler Error as specific one should be on the TOP
// handle 
} 
=====================================================================================

try { 
return; 
} finally { 
System.out.println("In finally"); //The finally block is almost always executed, regardless of whether 
}                                 //an exception occurs or a return, break, or continue statement is present in the try or catch blocks

=====================================================================================
public static int test1() { 
try { 
return 1; 
} finally { 
return 2; 
}             // return value is always overridden by the finally block
} 
  
=====================================================================================
  
********************************************
 
  public static void test1() { 
 try {
        int x = 1 / 0;
    } catch (Exception e) {
        System.out.println("error");      // first Catch block this gets printed
        throw e;                          // third at last.. exception is propagated.. ie. after finally Exception is propagated
    } finally {
        System.out.println("in finally");  // second Finally Block this gets printed 
    }
}
  
=====================================================================================

  *******************************************

  public static void test1() { 
 try {
        int x = 1 / 0;
    } catch (Exception e) {
        System.out.println("error");  // first 
        throw e;
    } finally {
        System.out.println("in finally"); // second 
        throw new RuntimeException("finally exception"); // third as above catch return is overridden by finally exception
    }
}



// Finally sbould not return values or throw exception.. which is bad by choice mostly.. just use it close the connections
// as exception of catch block can be propagated after finally block

=====================================================================================

