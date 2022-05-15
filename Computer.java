public class Computer {
   ///setting fields
    boolean halt = false;
    boolean zf = false;
    boolean cf = false;
    ALU compALU = new ALU();
    Memory mem = new Memory();
    Longword[] Register = new Longword[16];
    Longword PC = new Longword();
    Longword IR = new Longword();
    Longword OP1 = new Longword();
    Longword OP2 = new Longword();
    Longword Result = new Longword();
    
    ///we use a series of integer arrays to store the digits in each 4 bit string
    ///we do this to ease the later process by which we have to extract the information encoded into these sequences
    int[] destReg = new int[4];
    int[] bitBlock2 = new int[4];
    int[] bitBlock1 = new int[4]; 
    int[] opcode = new int[4];

    ///we use integers to store numbers we are going to find later
    ///we need our arrays to serve as register indicators, numerical representations, as well as the binary themselves, depending on opcode
    ///thus, we need many integers 
    int branch, Registrum1, Registrum2, code1, code2, Operation, Destination, codex, codedOP, codedDest, totalRegistrum, jump, addend, temp = 0;

    ///creating the object
    public Computer(){
    ///clearing our Longwords
        for(int i = 0;i<32;i++){
            PC.clearBit(i);
            IR.clearBit(i);
            OP1.clearBit(i);
            OP2.clearBit(i);
        }
            for(int i = 0; i<Register.length;i++){
                Longword tempLong = new Longword(0);
                Register[i] = tempLong;
            }
    }

    ///run method
    public void run(String[] Instruct){
    
        halt = false;
        temp=0;
        branch = 0;
        jump = 0;
        mem = new Memory();
        PC = new Longword(0);
        System.out.println(PC.getSigned());
        IR = new Longword(0);

        preload(Instruct);
        while(halt == false){
        for(int i = 0; i<4;i++){
            bitBlock1[i]= 0;
            bitBlock2[i] = 0;
            opcode[i] = 0;
            destReg[i] = 0;
        }
        this.fetch();
        this.decode();
        this.execute();
        this.store();
        }
    }

    ///fetch method
    void fetch(){

        System.out.println("***********Fetch Method Hit********");
        System.out.println(PC.getUnsigned());
        IR = mem.read(PC, 16);
        long before = PC.getUnsigned();
        System.out.println(before);
        System.out.println(IR.toString());
        PC = new Longword((int)before+16);
    }

    void decode(){
        System.out.println("*********Decode Method Hit*******");



        ///we use an integer array to store the information passed from memory to IR
        int[] bitEncoded = new int[4];
        ///populating the temporary array
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4;j++){ 
                if(IR.getBit(j) == true){
                    bitEncoded[j] = 1;
                }
            }

            IR = IR.Rshift(4);
            
            ///Since we are  using a temp array of integers, we need to change each resulting integer array individually based on the value
            ///instead of creating an equality to the integer in the temporary array. If we do it with an equality, changing any value in any array
            ///changes the value at that index in every array

            ///changing the first block of digits
            if(i== 0){
                for(int n = 0; n<4;n++){
                    if(bitEncoded[n] == 1){
              bitBlock1[n] = 1;
                }
                else{
                    bitBlock1[n] = 0;
                }
            }
        }
        ///changing the second block of digits
            else if(i==1){
                for(int n = 0; n<4;n++){
                    if(bitEncoded[n] == 1){
              bitBlock2[n] = 1;
                }
                else{
                    bitBlock2[n] = 0;
                }
            }
        }
        ///changing the destination register
            else if(i==2){
                for(int n = 0; n<4;n++){
                    if(bitEncoded[n] == 1){
              destReg[n] = 1;
                }
                else{
                    destReg[n] = 0;
                }   
            }
        }
        ///changing the opcode
            else if(i==3){
                for(int n = 0; n<4;n++){
                    if(bitEncoded[n] == 1){
                       opcode[n] = 1;
            }
            else{
                       opcode[n] = 0;
            }
            }
        }
        ///erasing our temp int array
            for(int n = 0;n<4;n++){
  
            bitEncoded[n] = 0;
            }

        }
        ///we are sending it out to a helper function to handle population of our global integers based on our integer arrays
        ///we use a helper function for debugging purposes. ie, since this is accessible from any method we can change values within the arrays
        ///from within any method and test the logic held in those methods in isolation
        this.populateVars();
        
        ///handling the jump operation
        if(Operation == 3){
           jump = totalRegistrum*2;
           System.out.println("Jumping " + jump + " spaces in memory");
           Result = new Longword(jump);
        }
            
            }
        
    

    void execute(){
        System.out.println("************Execute Method Hit**************");

        ///If the opcode tells us to access the ALU;
        if(Operation>=8){
            OP1 = Register[Registrum1];
            OP2 = Register[Registrum2];
            System.out.println("Executing Operation " + Operation + " on Register " + Registrum1 + " and Register " + Registrum2);
            Result = compALU.operate(Operation, OP1, OP2);
        }

        ///the Halt Operation
        else if(Operation == 0){
            System.out.println("Halt encountered");
            halt = true;
        }

        ///the Interrupt/Trap Operation
        else if(Operation == 1){
          if(Registrum2 == 0){
              
            System.out.println("Register dump encountered. Contents of Register are as follows:");
              for(int i = 0; i<16;i++){
                  System.out.println(Register[i].toString());
              }
          }
          else if(Registrum2 == 1){
              System.out.println("Memory dump encountered. Contents of Memory are as follows:");
              mem.displayAll();
            }
          }
        
        ///the Move Operation 
        else if(Operation == 2){
            System.out.println("Moving to Registers...");
            ///if MSB of second 4 bit block is 0, the process is simple and handled here
            if(Registrum1 < 8){
            int toStore = addend+Registrum2;
            Result = new Longword(toStore);
            System.out.println("sum to be placed in Register " +Destination +" : " + toStore);      
            }

            ///if MSB of second 4 bit block is 1, the result must be negative and requires additional logic which is handled here
            else{
            addend = -128;
            int n = 0;
            int m = 0;
            for(int i = 3; i>-1;i--){
                if(i<3){
               if(bitBlock2[i] == 1){
                   addend = addend+(int)Math.pow((double)2, (double)m);
                   m++;
               }
            }
               if(bitBlock1[i] == 1){
                   addend = addend+(int)Math.pow((double)2, (double)n+3);
               }
               n++;
            }
            System.out.println("sum to be placed in Register " +Destination +" : " + addend);
            Result = new Longword(addend);
            }
        }
    
        else if(Operation == 3){
            ///handled in decode phase
        }


        else if(Operation == 4){
             OP1 = Register[Registrum1];
             OP2 = Register[Registrum1];
             System.out.println(OP1);
             System.out.println(OP2);
             compALU.operate(12, OP1, OP2);
             zf =  compALU.same;
             cf = compALU.CF;
             System.out.println("zf: " + zf + " cf: " + cf);
        }

        else if(Operation == 5){
            int toAdd = 0;
            if(bitBlock2[0]==1){
              toAdd = toAdd+16;
            }
            if(bitBlock2[1]==1){
                toAdd = toAdd+32;
            }
            if(destReg[3]==1){
                if(destReg[2]==1){
                   if(zf==true ||  cf==true){
                      branch = branch+Registrum2+toAdd;

                   }
                }
                else{
                    if(zf==true){
                        branch = branch+Registrum2+toAdd;
                    }
                }
            }
            else{
                if(destReg[2]==1){
                    if(cf==true){
                        branch=branch+Registrum2+toAdd;
                    }
                }
                    else{
                        if(zf==false){
                            branch=branch+Registrum2+toAdd;
                        }

                }
            }
            System.out.println("Branch is equal to " + branch);
        }

    }

    ///Store method
    void store(){
        System.out.println("**********Store Method Hit**********");

        ///logic implemented on jump condition
        if(jump > 0){
            if(jump<mem.memArray.size()){
            PC = new Longword(jump); 
            }
            else{
                System.out.println("Error encountered in Store method. Jump overflows memory size");
            }
        }
        if(branch>0){
            System.out.println("branching from value " + PC.getUnsigned() + " to value " + (PC.getUnsigned()+branch));
            PC = new Longword(branch);
            System.out.println("We trigger a halt flag here for simplicity.");
            System.out.println(" The halt operation has been demonstrated, and rather than fiddle around with bits super deep in memory, we just flip a halt flag");
            halt=true;
        }
        ///if we are not jumping but need to store a resulting Longword in the Registers
        else if(Destination<16 && Destination>=0){
            System.out.println("Result: " + Result.toString());
            if(Operation>=8 || Operation  == 2){
            Register[Destination] = Result;
            }
        }
        ///Error handling
        else{
            System.out.println("Error encountered in Store method: Register Overflow");        
        }
    }

    void preload(String[] Instruct){
        ///creating Longword to send to write and an integer to index the Longword
        Longword instructLong = new Longword();
        int n = 0;

        ///Logic for populating Longword with info from memory
            for(int j = Instruct[0].length()-1; j>=0;j--){
              if(Instruct[0].charAt(j) == '1'){
                    instructLong.setBit(n);
                }
                else if(Instruct[0].charAt(j) == '0'){
                    instructLong.clearBit(n);
                }
                else{
                    System.out.println("Error encountered in preload method: Invalid Character Encountered in String found in String Array index 0");
                }
                n++;
            }
            
            for(int j=Instruct[1].length()-1;j>=0;j--){
                if(Instruct[1].charAt(j) == '1'){
                    instructLong.setBit(n);
                }
                else if(Instruct[1].charAt(j) == '0'){
                    instructLong.clearBit(n);
                }
                else{
                    System.out.println("Error encountered in preload method: Invalid Character Encountered in String found in String Array index 1");
                }
                n++;
            }
        while(n<31){
            System.out.println("We shouldnt be here");
           instructLong.clearBit(n);
           n++;
        }
    
        Longword address = new Longword(0);
  
        ///If we are jumping, we need to set it up so we can tell the jump worked. So, we need to create a new Longword, and put it where we are jumping to
        if(instructLong.getBit(12)==true && instructLong.getBit(13)==true && instructLong.getBit(14)==false && instructLong.getBit(15) == false){
            Longword secondInstruct = new Longword();
            int addy = 0;
            
            ///logic for populating our new address and new instruction from memory
            for(int i = 0;i<8;i++){
                if(instructLong.getBit(i) == true){
                   addy = addy+(int)Math.pow((double)2, (double)i);

                }
            }
            
            Longword secondAddy = new Longword(addy*2);
            int k = 0;
            for(int i = 16; i<32;i++){
                if(instructLong.getBit(i)== true){
                    secondInstruct.setBit(k);
                }
                else{
                    secondInstruct.clearBit(k);
                }
                instructLong.clearBit(i);
                k++;
            }
            System.out.println("First memory dump: " + instructLong.toString());
            System.out.println("First address: " + address.getUnsigned());
            System.out.println("Second memory dumo: " + secondInstruct.toString());
            System.out.println("Second address: " + secondAddy.getUnsigned());

            mem.write(secondAddy, secondInstruct, secondInstruct.Longword.length());

        }
        mem.write(address, instructLong, instructLong.Longword.length());
    }

    ///we use this helped method to handle populating our integer variables for debugging reasons
    ///namely, we want the option to change our integer variables from within any method to check the logic within that method in isolation
    public void populateVars(){
         
        Registrum1=0;
        Registrum2=0;
        code1=0;
        code2=0;
        Operation=0;
        Destination=0;
        codex=0;
        codedOP=0;
        codedDest=0;
        totalRegistrum=0;
        jump=0;
        addend=0;
        temp = 0;

        for(int i = 0; i<4;i++){
            ///System.out.println(i + " " + bitBlock2[i] + " " + bitBlock1[i] + " " + opcode[i] + destReg[i]);
            
            ///changing value that we use to update the integer representation of the actual binary codes
            if(i == 0){
                codex = 1;
            }
            else if(i==1){
                codex = 10;
            }
            else if(i==2){
                codex = 100;
            }
            else if(i==3){
                codex=1000;
            }
            
            ///handling the integer representations relating to the second 4 bit block
            if(bitBlock2[i] == 1){
                Registrum1 = Registrum1+(int)Math.pow((double)2, (double)i);
                code1 = code1+codex;
                totalRegistrum = totalRegistrum+(int)Math.pow((double)2, (double)(i+4));
                addend = addend+(int)Math.pow((double)2, (double)(i+4));
            }
            
            ///handling the integer representations relating to the first 4 bit block
            if(bitBlock1[i] == 1){
                Registrum2 = Registrum2+(int)Math.pow((double)2, (double)i);
                code2 = code2+codex;
                totalRegistrum = totalRegistrum+(int)Math.pow((double)2, (double)i);
            }

            ///handling the integer representations relating to the third 4 bit block
            if(destReg[i] == 1){
                Destination = Destination+(int)Math.pow((double)2, (double)i);
                codedDest = codedDest+codex;
            }

            ///handling the integer representations relating to the opcode
            if(opcode[i] == 1){
                Operation = Operation+(int)Math.pow((double)2, (double)i);
                codedOP = codedOP+codex;
            }
    }
    
}

    
}
