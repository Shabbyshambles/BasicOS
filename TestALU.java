public class TestALU {

    public TestALU(){

    }

    public void runTests(){
    Longword LW = new Longword(23);
    Longword notLW = LW.not();

    ALU myALU = new ALU();
         ///Accessor method
         System.out.println("The Accessor method: ");
         myALU.getBool();
         
         ///Demonstrating ALU And Method
         System.out.println("First Longword: "+ LW + " Second Longword: " +notLW + " And Op: ");
         myALU.operate(8, LW, notLW).Display();
         myALU.getBool();
         System.out.println("Longword And Method: ");
         LW.and(notLW).Display();
         
         ///Demonstrating ALU Or Method
         System.out.println("\n\nFirst Longword: "+ LW +" Second Longword: " + notLW + " Or Op: ");
         myALU.operate(9, LW, notLW).Display();
         myALU.getBool();
         System.out.println("Longword Or method: ");
         LW.or(notLW).Display();

        ///Demonstrating ALuU rippleadder
         System.out.println("\n\nThe original longword:");
         LW.Display();
         System.out.println("\n\nThe longword we are adding: ");
         notLW.Display();
         System.out.println("\n\nthe sum of the two:");
         myALU.operate(10, LW, notLW).Display();
         System.out.println("\n \nFirst value: " + LW.getSigned() + " Second value: " + notLW.getSigned() + " Summation: " + myALU.operate(1011, LW, notLW).getSigned());
         System.out.print("\nBoolean flags: ");
         myALU.getBool();
         ///Addition of two positiveeeeeeee numbers
         LW.set(45);
         notLW.set(10);
         
         
         Longword Adder = myALU.operate(11, LW, notLW);
         System.out.println("Addition of two postive numbers, expected value: 55 ");
         System.out.println("\nFirst value: " + LW.getSigned() + " Second value: " + notLW.getSigned() + " Summation: " + Adder.getSigned());
         System.out.print("\nBoolean flags:");
         myALU.getBool();

         ///Addition of a positive number of higher magnitude than a negtive number
         LW.set(45);
         notLW.set(-10);
         
         
         Adder = myALU.operate(11, LW, notLW);
         System.out.println("Addition of a positive number on the left and a negative number of lower magnitude on the right, expected value: 35 ");
         System.out.println("\nFirst value: " + LW.getSigned() + " Second value: " + notLW.getSigned() + " Summation: " + Adder.getSigned());
         System.out.println("\nBoolean flags:");
         myALU.getBool();
         
         ///Addition of a positive number of lower magnitude than a negative numberrrrr
         LW.set(45);
         notLW.set(-200);
         
         
         Adder = myALU.operate(11, LW, notLW);
         System.out.println("Addition of a positve number of lower magnitude on the left and a negative number of higher magnitude on the right, Expected result: -155 ");
         System.out.println("\nFirst value: " + LW.getSigned() + " Second value: " + notLW.getSigned() + " Summation: " + Adder.getSigned());
         System.out.println("\nBoolean flags: ");
         myALU.getBool();

         ///Adding two negative numbers
         LW.set(-45);
         notLW.set(-20);
         
         Adder = myALU.operate(11, LW, notLW);
         System.out.println("Addition of two negative numbers, Expected result: -65");
         System.out.println("\nFirst value: " + LW.getSigned() + " Second value: " + notLW.getSigned() + " Summation: " + Adder.getSigned());
         System.out.println("\nBoolean flags:");
         myALU.getBool();
         
         ///Demonstrating  CF flag
         LW.setBit(30);
         notLW.setBit(30);
         System.out.println("the original longword:\n");
         LW.Display();
         System.out.println("\nthe longword we are adding:\n");
         notLW.Display();
         System.out.println("\ndemonstrating the CF flag:\n");
         myALU.operate(11, LW, notLW).Display();
         System.out.println("\nSince we are adding two binary numbers with 1 in the MSB spot, we see the CF flag is set to true");
         myALU.getBool();
         
         ///Demonstrating the OF flag
         LW.set(1728);
         notLW.set(2);
         System.out.println("Since the OF state cannot be reached by normal operations, we use Longword 1 = 1728 as the OF condition just for demonstration");
         System.out.println("Demonstrating the OF flag: ");
         System.out.println("First Longword: " + LW.getSigned() + " Second Longword: " + notLW.getSigned() + " Result: ");
         myALU.operate(11, LW, notLW).Display();
         System.out.println("\nSince we keyed in the Longword to trigger the OF condition, we change the MSB to 1 while the MSB of each Longword is 0, which triggers the OF flag");
         myALU.getBool();
         
         ///Demonstrating the zero flag
         for(int i = 0; i<32;i++){
             LW.clearBit(i);
             notLW.clearBit(i);
         }
         System.out.println("The first longword:\n" + LW);
         System.out.println("The longword we are adding \n" + notLW);
         System.out.println("Demonstrating the zero flag:");
         myALU.operate(11, LW, notLW).Display();
         System.out.println("\nSince the resulting longword is the Zero longword, we see the Zero flag is set to true");
         myALU.getBool();
         
         ///Demonstrating the negative flag
         notLW.set(50);
         notLW.setBit(31);
         System.out.println("The first longword");
         LW.Display();
         System.out.println("\nThe longword we are adding ");
         notLW.Display();
         System.out.println("\nDemonstrating the negative flag:");
         myALU.operate(11, LW, notLW).Display();
         System.out.println("\nSince the resulting longword has a 1 in the sign bit, we see the negative flag is true");
         myALU.getBool();
         

        ///demonstrating subtraction of two positive number with the larger number to the left
        LW.set(50);
        notLW.set(5);
        System.out.println("\nSubtraction of two positive numbers with the larger positive number to the left of the minus, expected result: 45");
        System.out.println("First number: " + LW.getSigned() + " Second number: " + notLW.getSigned() + " Actual result: " + myALU.operate(1100, LW, notLW).getSigned());
    
        ///Demonstrating subtraction of two positive numbers with the larger number on the right
        LW.set(5);
        notLW.set(15);
        System.out.println("\nSubtraction of two positive numbers with the larger number to the right of the minus, Expected result: -10");
        System.out.println("First number: " + LW.getSigned() + " Second number: " +notLW.getSigned() +  " Actual result: " + myALU.operate(1100, LW, notLW).getSigned());
        ///Demonstrating subtraction of a positive number on the right and a negative number on the left
        LW.set(5);
        notLW.set(-15);
        System.out.println("\nSubtraction of a positive number on the right and a negative number on the left, Expected result: 20");
        System.out.println("First Longword: " +LW.getSigned() + " Second Longword: " + notLW.getSigned() + " Actual result: " + myALU.operate(1100, LW, notLW).getSigned());
        ///Demonstrating subtraction of two negative numbers
        LW.set(-5);
        notLW.set(-15);
        System.out.println("\nSubtraction of two negative numbers, Expected result: -20");
        System.out.println("First Longword: " + LW.getSigned() + " Second Longword: " + notLW.getSigned() + " Actual result: " + myALU.operate(1100, LW, notLW).getSigned());

        ///testing Rshift/Lshift
        LW.set(-98);
        notLW.set(5);
        System.out.println("LW:");
        LW.Display();
        System.out.println("\nLeftshifted by: " +notLW.getSigned()%32);
        myALU.operate(13, LW, notLW).Display();
        System.out.println("\nLW: ");
        LW.Display();
        System.out.println("\nRightshifted by: " +notLW.getSigned()%32); 
        myALU.operate(14, LW, notLW).Display(); 
        System.out.println("\nArithmetic shift by: " + notLW.getSigned()%32); 
        myALU.operate(15, LW, notLW).Display();
        System.out.println("\nI'm not sure if I fixed the arithmetic shift after getting it wrong on Assignment 1, but I believe this is correct"); 
        
        ///Checking the Boolean stays consistent with the Shift
        System.out.println("\nSince our Arithmetic Shift gives us a 1 in MSB we can check the negative is accounted for in this process by checking our booleans following arithmetic shift:");
        myALU.getBool();
        

        System.out.println("We will check that the zero accounted for in the Rightshift process, using the following Longword:");
        LW.set(2);
        LW.Display();
        System.out.println("Rightshifted by " + notLW.getSigned()%32);
        myALU.operate(14, LW, notLW).Display();
        System.out.println("\nSince we have the zero Longword, we check the zero is accounted for in the Rightshift:");
        myALU.getBool();

        System.out.println("This was all the cases I could come up with, hopefully any corner cases are also handled, fingers crossed!");
    }
}
