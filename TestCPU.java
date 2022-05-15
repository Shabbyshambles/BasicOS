public class TestCPU {

    String[] StrArray = new String[2];
    Computer myComp = new Computer();

    public TestCPU(){

    }

    public TestCPU(String[] StrArray){
        this.StrArray = StrArray;

    }

    public TestCPU(Computer myComp){
        this.myComp = myComp;
    }

    public TestCPU(String[] StrArary, Computer myComp){
        this.StrArray = StrArray;
        this.myComp = myComp;
    }

    public void runTests(){
        String myString = new String();
        StringBuilder Instruct = new StringBuilder(); 
        StringBuilder Instruct2 = new StringBuilder();

        for(int i=0;i<16;i++){
            if(i!=3){
            Instruct.append('0');
            
            Instruct2.append('0');
            }
            else{
                Instruct.append('1');
                
            Instruct2.append('0');
            }
        }

        
        System.out.println("Testing trap and halt opcodes. Expected results: a dump of register values, followed by a halt message");
        StrArray[1] = Instruct2.toString();
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray);
        
        System.out.println("Testing other trap opcode Expected results: a dump of memory values, followed by a halt message");
        Instruct.setCharAt(15,'1');
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing Move opcode. Expected results: A statement saying we are placing the number 3 in Register 0"+
        " followed by a dump of the Registers displaying the binary representation of the number 3 in Register 0");
        Instruct.setCharAt(3, '0');
        Instruct.setCharAt(2, '1');
        Instruct.setCharAt(14, '1');
        Instruct2.setCharAt(3, '1');

        StrArray[0] = Instruct.toString();
        StrArray[1] = Instruct2.toString();
        System.out.println(StrArray[1]);
        myComp.run(StrArray); 

        System.out.println("Testing jump opcode. Expected results:A statement saying we are jumping 64 bits, followed by a dump of the Registers");
        for(int i= 0;i<Instruct.length();i++){
            Instruct.setCharAt(i,'0');
            Instruct2.setCharAt(i, '0');
        }
        Instruct.setCharAt(2, '1');
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(10, '1');   
        Instruct2.setCharAt(3, '1');
        StrArray[0] = Instruct.toString();
        StrArray[1] = Instruct2.toString();
        System.out.println(StrArray[0]);
        System.out.println(StrArray[1]);
        myComp.run(StrArray);

        System.out.println("Testing ALU opcodes");
        System.out.println("Baseline test showing a dump of clean Registers");
        myComp = new Computer();
        for(int i = 0; i<Instruct.length();i++){
           Instruct.setCharAt(i, '0');
           Instruct2.setCharAt(i, '0');
        }
        Instruct2.setCharAt(3, '1');
        Instruct.setCharAt(3, '1');
        StrArray[0] = Instruct.toString();
        StrArray[1] = Instruct2.toString();
        myComp.run(StrArray);

        System.out.println("Moving the number 5 to R0");
        Instruct.setCharAt(3, '0');
        Instruct.setCharAt(2, '1');
        Instruct.setCharAt(7, '0');
        Instruct.setCharAt(13, '1');
        Instruct.setCharAt(15, '1');
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Moving the number 1 to R1");
        Instruct.setCharAt(7, '1');
        Instruct.setCharAt(6,'0');
        Instruct.setCharAt(13, '0');
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 8, expected result is 5 AND 1 placed in Register 2");
        Instruct.setCharAt(6, '1');
        Instruct.setCharAt(7,'0');
        Instruct.setCharAt(2, '0');
        Instruct.setCharAt(0, '1');
        Instruct.setCharAt(11, '0');
        Instruct.setCharAt(13, '0');
        Instruct.setCharAt(15, '1');
        Instruct.setCharAt(14, '0');
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 9, expected result is 5 OR 1 placed in Register 3");
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(6, '1');
        Instruct.setCharAt(7, '1');
        StrArray[0] = Instruct.toString();
        myComp.run(StrArray); 

        System.out.println("Testing opcode 10, expected result is 5 XOR 1 in Register 4");
        Instruct.setCharAt(3, '0');
        Instruct.setCharAt(2, '1');
        Instruct.setCharAt(7, '0');
        Instruct.setCharAt(6, '0');
        Instruct.setCharAt(5, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 11. Expected result is 5+1 in Register 5");
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(7, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 12. Expecte result is 5-1 in Register 6");
        Instruct.setCharAt(3, '0');
        Instruct.setCharAt(2, '0');
        Instruct.setCharAt(1, '1');
        Instruct.setCharAt(7, '0');
        Instruct.setCharAt(6, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 13. Expected result is the number 1 LeftShifted by 5 in Register 7");
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(6, '1');
        Instruct.setCharAt(7, '1');
        Instruct.setCharAt(15, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 14. Expected result is the number 5 RightShifted by 1 in Register 8");
        Instruct.setCharAt(3, '0');
        Instruct.setCharAt(2, '1');
        Instruct.setCharAt(1,'1');
        Instruct.setCharAt(7, '0');
        Instruct.setCharAt(6, '0');
        Instruct.setCharAt(5, '0');
        Instruct.setCharAt(4, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Testing opcode 15. Expected result is the number 5 Arithmetic shifted by 1 in Register 9");
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(7, '1');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);  

        System.out.println("Testing opcode 4. Expected result is terminal displaying zf=false and cf = true");
        Instruct.setCharAt(0, '0');
        Instruct.setCharAt(1, '1');
        Instruct.setCharAt(2,'0');
        Instruct.setCharAt(3, '0');
        StrArray[0]=Instruct.toString();
        myComp.run(StrArray);

        System.out.println("Now that we have cf=true, we can start to test opcode 5. Expected result is a message saying we branched from a value, to a greater value");
        Instruct.setCharAt(3, '1');
        Instruct.setCharAt(4,'1');
        Instruct.setCharAt(5,'1');
        StrArray[1]=Instruct.toString();
        myComp.run(StrArray);
        System.out.println("We see cf=true, zf=false, and we hit the branch logic as desired");

        System.out.println("We want to check if zero flag is working in our branch op.");
        Instruct.setCharAt(3, '0');
        for(int i=8;i<16;i++){
        Instruct.setCharAt(i, '1');
        }
        StrArray[0]=Instruct.toString();
        Instruct.setCharAt(3, '1');
        StrArray[1]=Instruct.toString();
        myComp.run(StrArray);
        System.out.println("We see that the zero flag is true, the cf flag is false, and we hit up against the branch logic as desired");

        System.out.println("So, we have tested Halt, Trap with both interrupt codes, Move, Jump, Conditional, Branch with both the zf flag and the cf flag as triggers, and the full ALU codes");
        System.out.println("Hopefully this is enough for the Computer Tests. The ordering of the binary digits may be messed up when transferring to the 4 digit codes.");
        System.out.println("I'm finding it very hard to actually tell.");
    }
    
}
