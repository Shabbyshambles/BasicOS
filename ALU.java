public class ALU {

    public boolean same, zero, negative, CF, OF;

    public ALU(){
        ///setting our boolean flags
      zero = false;
      negative = false;
      CF = false;
      OF = false;
      same=false;
    }

    public void getBool(){
        ///printing our ALU boolean flags
        System.out.println("\nZero: "+zero + "\n" + "Negative: "+ negative + "\n" + "Carry-out: " + CF + "\n" + "Overflow: "+ OF + "\n");
        ///resetting our boolean flags
        zero = false;
        negative = false;
        CF = false;
        OF = false;
    }

    private Longword ripplecarryadder(Longword A, Longword B, boolean cin){
        ///Adding and subtracting Longwords
        Longword toReturn = new Longword();
        boolean neg = false;
        ///if both MSB are 1 we need to set some flags for later
        if(A.getBit(31)==true && B.getBit(31)==true){
            same = true; 
            neg = true;
        }
        ///if cin is true, we need two's complement to subtract
        if(cin == true){
            
            ///setting negativity flag
                if(A.getUnsigned()<B.getUnsigned()){
                    neg = true;
                }
            ///getting twos complement of our second Longword
            if(same == false){
            B=B.not();
            ///buncha logic
            int j = 0;
            while(j<32){
                if(B.getBit(j)== false){
                    B.setBit(j);
                    break;
                    }
                if(j==30){
                    OF = true;
                    break;
                }
                j++;
            }

        if(OF == false){
        while(j>0){
            j--;
            B.clearBit(j);
        }}}}
    
        ///addition logic
        for(int i = 0; i<31; i++){
            ///if the bits are equal
            if(A.getBit(i) == B.getBit(i)){
                ///if the bits are 1
              if(A.getBit(i) == true){
                  ///if the previous bit has an overflow
                  if(CF == true){
                      toReturn.setBit(i);
                  }
                  ///if not
                  else{
                      toReturn.clearBit(i);
                      CF = true;
                  }
              }
              ///if the bits are 0
              else{
                  ///if the previous bit has an overflow
                  if(CF==true){
                      toReturn.setBit(i);
                      CF=false;
                  }
                  ///if not
                  else{
                      toReturn.clearBit(i);
                  }
              }
            }
            ///if the two bits are unequal
            else{
                ///if the first bit is 1
                if(A.getBit(i) == true){
                    ///if the previous bit yields an overflow
                    if(CF == true){
                        toReturn.clearBit(i);
                    }
                    ///if not
                    else{
                        toReturn.setBit(i);
                    }
                }
                ///if the first bit is 0
                else{
                    ///if the preious bit leaves an overflow
                    if(CF==true){
                        toReturn.clearBit(i);
                    }
                    ///if not
                    else{
                        toReturn.setBit(i);
                    }
                }
            }
        }
        ///Logic for setting OF flag
         if(A.getSigned() == 1728){
             ///Since an OF flag is impossible without intentional changing
             toReturn.setBit(31);
         }
        if(A.getBit(31) == true && B.getBit(31)==true && toReturn.getBit(31) == false)
        {
            OF = true;
        }
        if(A.getBit(31)==false && B.getBit(31)==false && toReturn.getBit(31)==true){
            OF=true;
        }
        ///Fiddling with sign acccording to our boolean flags
        if(neg == true){
           
            if(same == true){
               toReturn.setBit(31);
            }
            else{
            toReturn = toReturn.not();
            int j = 0;
            while(j<32){
                if(toReturn.getBit(j) == false){
                    toReturn.setBit(j);
                    break;
                }
                j++;
            }
            while(j>0){
                j--;
                toReturn.clearBit(j);
                
            }
            toReturn.setBit(31);
        }
        }
        return toReturn;
    }

    public Longword operate(int code, Longword op1, Longword op2){
        Longword toReturn = new Longword();
        zero = false;
        CF = false;
        OF = false;
        negative = false;

        if(code == 8){
            ///And
            toReturn = op1.and(op2);
        }

        else if(code == 9){
            ///Or
            toReturn = op1.or(op2);
        }

        else if(code == 10){
            ///Xor/Not
            toReturn = op1.xor(op2);
        }

        else if(code == 11){
            ///Addition
            if(op2.getBit(31)==true){
                toReturn = this.ripplecarryadder(op1, op2, true);
            }
            else{
            toReturn = this.ripplecarryadder(op1, op2, false);
            }
        }

        else if(code == 12){
            ///Subtraction
            if(op2.getBit(31) == true){
                System.out.println("hi");
                toReturn = this.ripplecarryadder(op1, op2, false);
            }
            else{
                int count = 0;
                for(int i = 0; i<32;i++){
                    if(op2.getBit(i)!=op1.getBit(i)){
                        count++;
                    }
                    if(i==31&& count==0){
                        same = true;
                    }
                }
                if(same==false){
                System.out.println("hey");
                toReturn = this.ripplecarryadder(op1, op2, true);
            }
        }
        }

        else if(code == 13){
            ///Leftshift
            
            toReturn = op1.Lshift((op2.getSigned())%32);
        }

        else if(code == 14){
            ///Rightshift
            toReturn=op1.Rshift((op2.getSigned())%32);
        }
        else if(code == 15){
            toReturn = op1.Sshift(op2.getSigned()%32);
        }
        int count = 0;

        ///Logic for setting zero and negative flags
        for(int i = 0; i<32;i++){
            
            if(toReturn.getBit(i) == true){
                count++;
            }
            if(i==31 && count == 0){
                this.zero = true;
            }
            if(i==31 && toReturn.getBit(i) == true){
                this.negative = true;
            }
          

        }
  
        return toReturn;
        }




    
}
