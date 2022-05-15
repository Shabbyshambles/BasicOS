import java.util.*;

public class Driver {

     public static void main(String[] args){
        ///Creating the Longword
        Longword LW = new Longword(5);
    
        ///Testing basic Longword printword
         LW.printWord();
         System.out.println("original output: " + LW.toString());
         LW.printWord();

         ///checking set Method
         LW.set(7);
         System.out.println("after set: " + LW.toString());
         
         ///checking getSigned Method
         int Sign = LW.getSigned();
         System.out.println("Signed val: " + Sign);

         ///checking getUnsigned Method
         long Nonsign = LW.getUnsigned();
         System.out.println("Unsigned val: " + Nonsign);
         System.out.println(LW.toString());
         
         ///checking Not Method
         Longword notLW = LW.not();
         System.out.println("not output: " + notLW.toString());
         notLW.printWord();
         System.out.println(notLW.getSigned());
         
         ///checking And Method
         Longword LWandNot = LW.and(notLW);
         System.out.println("and output: " + LWandNot);
         System.out.println(LWandNot.getSigned());
         
         ///checking Or Method
         Longword LWorNot = LW.or(notLW);
         System.out.println("or output: " + LWorNot);
         System.out.println(LWorNot.getSigned());
         
         ///checking XOR Method
         Longword LWxorNot = LW.xor(notLW);
         System.out.println("xor: " + LWxorNot);
         System.out.println(LWxorNot.getSigned());
         
         ///Checking Rightshift/Leftshift
         Longword Rshift = LW.Rshift(5);
         System.out.println("right shift: " + Rshift);
         Longword Lshift = LW.Lshift(4);
         System.out.println("left shift: " + Lshift);

         ///checking Set Method
         LW.set(3);
         System.out.println("set to 3: " +LW);
         LW.set(11);
         System.out.println("set to 11: " +LW);
         
         ///checking isZero Method
         System.out.println("Not all zeros: "+LW.isZero());
         System.out.println("All zeros: "+LWxorNot.isZero());

         ///checking Arithmetic shift
         Longword Ashift = LW.Sshift(8);
         System.out.println("Arithmetic shift: " +Ashift);

         ///Testing ALU
         TestALU Tester = new TestALU();
         Tester.runTests();

         Memory myMem = new Memory();
         MemTest myMemTest = new MemTest(LW, Ashift);
         myMemTest.runTests();
         System.out.println("current Longword: " + LW.toString());
         Longword newWord = myMem.read(LW, 32);
         System.out.println("New Longword: " + newWord);

         myMem.write(LW, LW, 32);
         newWord = myMem.read(LW, 32);

         System.out.println("After writing Longword to Memory and Recovering Longword is: " + newWord.toString());
         System.out.println(newWord.getBit(0));

         Computer Computron = new Computer();
         Computron.fetch();
         TestCPU TestComputron = new TestCPU(Computron);
         TestComputron.runTests();
     }
    
}
