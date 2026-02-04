//Q1. You need to build a payment API that must support multiple payment methods (UPI, 
//Card, Wallet) and allow third parties to add new methods in the future. 

//1st Scenario-> Lets go with static methods

    class PaymentProcessor {
    public static void payWithCreditCard() { ... }
    public static void payWithPayPal() { ... }
                }
        // In the above Everytime, you add new payment methods, we edit this class
        // This breaks, Open/Closed principle.. open for extension, closed for modification
        
        
// 2nd Scenario -> Lets go with Abstract class
    
        abstract class Payment {
            public abstract void pay(double amount);
            public void logPayment() { ... } // default behavior
                    }
        
        // Now assume the below third party class
            class ThirdPartyBase {
                void someMethod() { }
                }

            class ThirdPartyPayment extends ThirdPartyBase {
                void process() { }
                }
                
        // Now, your implementation requires Thirdparty class and Payment class
           
           class MyPayment extends ThirdPartyPayment, Payment {
            }
            // Now this is not allowed in the java. Multiple inheritance in classes not allowed
            

// 3rd Scenario -> Now lets go with Interface
        
            interface Payment {
                void pay(double amount);
            }
            
        // third party class also required..
        
            class MyPayment extends ThirdPartyPayment implements Payment {
                @Override
                public void pay(double amount) {
                    System.out.println("Paying " + amount);
                        }    
                }
        // So eventually, interface helps you to solve this problem statement
        // And this reflect it executing Open for extension/ Closed for modification principle
        
    
// Below is the sample implementation with interface

import java.util.*;
import java.util.concurrent.*;

class PaymentChannels{
    public static void main(String args[]){
        PaymentProcessor pp=new PaymentProcessor();
        pp.pay(new CardPayment(),400);
        pp.pay(new UpiPayment(),500);
        
    }
}

interface PaymentType {
    void pay(double amount);
}

class UpiPayment implements PaymentType{
   
    public void pay(double amount){
        System.out.println("Paying "+amount+" via UPI");
    }
}
class CardPayment implements PaymentType{
    
    public void pay(double amount){
        System.out.println("Paying "+amount+" via Card");
    }
}

class PaymentProcessor{ // This class can act as dependency injection class
                        // if you provide an instance of interface
                        // tie the implemention in this class constructor to the field
                        // pay method would call the pay method of implementation
                        // So an extra class which recieves the implementation object
                        // and calls the required method with another method is 
                        // what we can call dependency injection achieved...
    public void pay(PaymentType type,double amount){
        type.pay(amount);
    }
}



===============================================================================================

//Q2. Different channels like Email, SMS, Push Notification must follow the same contract (sendMessage), 
//but each channel is completely independent.The app needs to send notifications through Email, SMS, and Push Notifications.

// Solution follow the above path.. i,e. OCP need an interface and implement them

===============================================================================================

// Q3. File export system which can export data to Pdf,Excel, CSV. 
    // solution : Same use interfaces
  // In free time, try to implement with real, mostly with springboot
===============================================================================================

//Q4 Authentication Strategies i.e, can selece, password login, Oauth login, Biometric login
    // solution : Same use interfaces
  // In free time, try to implement with real, mostly with springboot  

===============================================================================================

//Q5. Employee Payroll System:
// All employess have name, salary, and  method calculateBonus(),
//but bonus should differ amont FullTime and Contrat employees
// How do you desing this, Using interface or abstract class?

// 1st Interfaces -> interface can help to provide different implementation but cannot store information
//2nd Abstract-> It can store information and specific method implementation can be leaved to subclassses

//Solution: Abstract class best fits here....

//Abstract class with common fields and behavior
abstract class Employee {

    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
 
    // Abstract method — subclasses must implement their own bonus logic
    public abstract double calculateBonus();
 
    public void displayInfo() {
        System.out.println("Name: " + name + ", Salary: " + salary);
    }
}
 
//Full-time employee with its own bonus calculation
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.2; // 20% bonus
    }
}
 
//Contract employee with a different bonus calculation
class ContractEmployee extends Employee {
    public ContractEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.1; // 10% bonus
    }
}
 
public class EmployeePayroll {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee("NTR", 50000);
        Employee emp2 = new ContractEmployee("Hrithik ", 40000);

        emp1.displayInfo();
        System.out.println("Bonus: " + emp1.calculateBonus());

        emp2.displayInfo();
        System.out.println("Bonus: " + emp2.calculateBonus());
    }
}

===============================================================================================

// Q6. In a banking app, savings and current accounts share logic like deposit() and 
// calculateInterest() but have different interest rules.

// Same above implementation... calculateIntrest() should be given to subclass..i.e, Savings account and CurrentAccount 


===============================================================================================

//Q7. When creating a custom exception, how do you decide whether to extend Exception or 
//RuntimeException? 

// For Checked Exception extend Exception class : Exception which we can handle gracefully
    // 1. InsufficientBalanceException
    // 2. FileFormatException
    // 3. OrderNotFoundException

// For Unchecked Exception extend ERuntimexception class : Exception causing programming error, condition we cannot recover.etc
    // 1. PaymentProcessingFailedException
    // 2. DatabaseConnectionFailedException

===============================================================================================

//Q8. How do you make resources like Inputstream or files or DB connections close after you done 
//with your work? 

// use Try with Resources. Refer this-> https://medium.com/@AlexanderObregon/javas-autocloseable-interface-explained-b853aeb663b1

===============================================================================================

//Q9. You have an Interface EmailService with sendEmail(String to, String subject, String body) that 
//throws InvalidEmailException or EmailServerDownException. 

/Checked exception for invalid email addresses 
class InvalidEmailException extends Exception { 
    public InvalidEmailException(String message) { 
    super(message); 
    } 
} 

//Checked exception for server issues 
class EmailServerDownException extends Exception { 
    public EmailServerDownException(String message) { 
        super(message); 
    }
}

    inteface EmailService{
      void sendEmail(String to, String subject,String body)
          throws InvalidEmailException,EmailServerDownException
          }

// throw : throws exception object at run time
// throws(used at method level) : lets the compiler and caller know about possibel cause, so ready to handle it

===============================================================================================

//Q10. Global Math Operations
// Then create a class with Final and provide static methods
// Also very importantly ensure constructor to be private, hence they cannot instantiate
// This would result in single copy of class and methods can be reused..

===============================================================================================

//Q11. Your system needs a log(message) function that can be called from anywhere without creating a 
//logger object?

// Solution: create static method , it wont require any object.

===============================================================================================
//Q12. Create custom interface.

// solution: we need extaclty one abstract method in interface to become functional interface

  interface Calculate{
   int calculate(int a, int b);
   }
  
  public class CalculateDemo{
  
        public static void main(String args[]){
        
        Calculate add=(a,b)->a+b;
        Calculate sub=(a,b)->a-b;
        
        System.out.println("Additon "+add.calculate(2,4));
        System.out.println("Substraction "+add.calculate(6,4));
        
        }
    }
  
===============================================================================================
