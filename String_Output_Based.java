String a = "hello";
String b = "hello";
String c = new String("hello");
System.out.println(a == b);      // TRUE  as both pointing to the same address/reference
System.out.println(a == c);      // False as c address is different than a
System.out.println(a.equals(c)); // TRUE as both content are equal

============================================================================================

String x = "codinglyf";      // SCP
String y = "coding";         // SCP
String z = y + "lyf";        // Concatenation with "+" operator happens by "StringBuilder".  Hence it creates an object in the Heap memory
System.out.println(x == z);  // False Hence address are both different 
System.out.println(x.equals(z)); // TRUE content being same


============================================================================================

String a = "hello";
String b = String.valueOf(a);   // internally if object is not null, valueOf(stringObj) return stringObj.toString() return itself
System.out.println(a == b);    // hence b refers to the same a addres. TRUE


============================================================================================

String a = "hello";
String b = a.substring(0);  //if index is 0 for substring method(literally whole String required), hence as part of optimization(from jdk 7) existing SCP/Heap Object reference is returned avoiding new object creation
System.out.println(a == b); // Hence True

============================================================================================

String s1 = new String("hello");  // Heap Object  
String s2 = "hello";              // SCP object
String s3 = s1.intern();          // if SCP object contains, it is returned, else one in SCP is created and its reference is assigned
System.out.println(s1 == s3)      // Obviously FALSE, as S3 get SCP address, s1 holds Heap address
System.out.println(s3 == s2);     // TRUE as both refers to SCP address

============================================================================================

StringBuilder sb1 = new StringBuilder("abc");     // New HEAP object
StringBuilder sb2 = new StringBuilder("abc");    // New HEAP object
System.out.println(sb1 == sb2);                  // Obviously FALSE
System.out.println(sb1.equals(sb2));             // FALSE, Surpise.. here StringBuilder equals method do not override, Object class equals method.. hence it just compares == references
System.out.println(sb1.toString().equals(sb2.toString()));   //here we are using equals method of String,, as objects are converted to the Strings by toString method

============================================================================================

StringBuilder sb1 = new StringBuilder("abc");   // new
StringBuilder sb2 = sb1;                        // refering the same address
sb1.append("d");                                // modification results in both
System.out.println(sb1 == sb2);                 // TRUE
System.out.println(sb1.equals(sb2));            // TRUE  -> StringBuilder or StringBuffer equals and hashcode methods are not overriden , so as we cannot use them as keys in maps which avoids data-loss
