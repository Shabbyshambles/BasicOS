import java.util.*;
public class Longword {

    BitSet Longword = new BitSet(32);

    public Longword(){
   
    }

    public Longword(BitSet mySet){
        Longword = mySet;
    }

    public Longword(int myInt){
        if(myInt>=0){
            Longword.clear(31);
        }
        else{
            Longword.set(31);
            int n = 0;
            while(myInt<0){
                myInt++;
                n++;
            }
            myInt = n;
        }
        for(int i = 0;i<32;i++){
            if(myInt == Math.pow(2,i)){
                Longword.set(i);
                break;
            }
        else if(myInt<Math.pow(2,i)){
            int j = i-1;

                while(myInt/2>0){
                    if(myInt%2==1){
                        Longword.set(j);
                }
                    else{
                        Longword.clear(j);
                    }
                    
                    myInt=myInt/2;
                    j--;
                    if(myInt == 1){
                        Longword.set(0);
                    }
            }
    }
}
    }

    public void Display(){
        ///Method to reverse Longword so the shifts make sense
        for(int n = 31;n>=0;n--){
            if(this.getBit(n) == true){
            System.out.print(1);
            }
            if(this.getBit(n) == false){
                System.out.print(0);
            }
        }
    }
    void printWord(){
        ///printing the length
        System.out.println(this.Longword.length());
    }

    @Override public String toString() {
        ///Getting a String + the hex value;
        String myString;
        StringBuilder sBuilder = new StringBuilder(32);

        for(int i = 0; i<32;i++){
            int n = 0;
            boolean tf = this.Longword.get(i);
            if(tf == true){
                n = 1;
            }
            else if(tf == false){
                n = 0;
            }

            sBuilder.append(n);
            
        }
        sBuilder.append(" in hex: ");
        for(int n = 0;n<32;n=n+4){
            if(this.getBit(n+3) == true){
                if(this.getBit(n+2) == true){
                    if(this.getBit(n+1) == true){
                        if(this.getBit(n) == true){
                            sBuilder.append('F');
                        }
                        else{
                            sBuilder.append('E');
                        }
                
    
                }
                     else if(this.getBit(n)==true){
                         sBuilder.append('D');
                     }
                     else{
                         sBuilder.append('C');
                     }
            }
            else if(this.getBit(n+1) == true){
                if(this.getBit(n) == true){
                    sBuilder.append('B');
                }
                else{
                    sBuilder.append('A');
                }
            }
            else if(this.getBit(n) == true){
                sBuilder.append('9');
            }
            else{
                sBuilder.append('8');
            }

            }
            else if(this.getBit(n+2) == true){
                if(this.getBit(n+1) == true){
                    if(this.getBit(n) == true){
                        sBuilder.append('7');
                    }
                    else{
                        sBuilder.append('6');
                    }
            

            }
                 else if(this.getBit(n)==true){
                     sBuilder.append('5');
                 }
                 else{
                     sBuilder.append('4');
                 }
        }
            else if(this.getBit(n+1) == true){
                if(this.getBit(n) == true){
                    sBuilder.append('3');
                }
                else{
                    sBuilder.append('2');
                }

            }
            else if(this.getBit(n) == true){
                sBuilder.append("1");
            }
            else{
                sBuilder.append('0');
            }

        }

        
        myString = sBuilder.toString();
        return myString;
    }

    public Longword Lshift(int amount){
        ///Leftshift
        Longword Lshift = new Longword();
        for(int n = 32; n>0;n--){
            if((n-amount)>0){
            if(this.getBit(n-amount) == true){
                
                Lshift.setBit(n);
            }
            else{
            
                Lshift.clearBit(n);
            }
        }
        else{
        
            Lshift.clearBit(n);
        }
        }
        return Lshift;
    }

    public Longword Rshift(int amount){
        Longword Rshift = new Longword();
        for(int n = 0; n<32; n++){
            if(n+amount<32){
                if(this.getBit(n+amount) == true){
                    Rshift.setBit(n);
                }
                else{
                    Rshift.clearBit(n);
                }
            }
            else{
                Rshift.clearBit(n);
            }
        }
        return Rshift;
    }

    public Longword Sshift(int amount){
        boolean shifted = this.getBit(31);
        Longword Sshift = new Longword();
        for(int n = 0;n<32;n++){
            if((n+amount)<32){
                if(this.getBit(n+amount) == true){
                    Sshift.setBit(n);
                }
                else{
                    Sshift.clearBit(n);
                }

            }
            else{
                if(shifted == true){
                    Sshift.setBit(n);
                }
                else{
                    Sshift.clearBit(n);
                }
            }
        }
        return Sshift;
    }

    public boolean getBit(int i){
        return this.Longword.get(i);
    }

    public void setBit(int i){
       this.Longword.set(i);
    }

    public void clearBit(int i){
        this.Longword.clear(i);
    }

    public void toggleBit(int i){
        this.Longword.flip(i);
    }

    public int getSigned(){

        int sum = 0;
        for(int n = 0; n<31;n++){
            if(this.getBit(n) == true){
              sum=sum+((int)Math.pow(2, n));
            }
        }

        if(this.getBit(31) == true){
            sum = -sum;
        }
        return sum;
    }

    public void set(int value){
        for(int n = 0; n<32;n++){
            this.Longword.clear(n);
        }
        if(value>=0){
            this.Longword.clear(31);
        }
        else{
            this.Longword.set(31);
            int n = 0;
            while(value<0){
                value++;
                n++;
            }
            value = n;
        }
        for(int i = 0;i<32;i++){
            
            if(value == Math.pow(2,i)){
                this.Longword.set(i);
                break;
            }
        else if(value<Math.pow(2,i)){
            int j = 0;

                while(value/2>0){
                    if(value%2==1){
                        this.Longword.set(j);
                }
                    else{
                        this.Longword.clear(j);
                    }
                    
                    value=value/2;
                    j++;
                    if(value == 1){
                        this.Longword.set(j);
                    }
            }
    }
}
    }

    public void copy(Longword other){
        this.Longword = other.Longword;
    }

    public long getUnsigned(){
        long sum = 0;
        for(int n = 0; n<31; n++){
            if(this.getBit(n) == true){
              sum = sum+((int)Math.pow(2, n));
            }
        }
        return sum;
    }

    public Longword not(){
        Longword notLW = new Longword();

        for(int n = 0; n<31;n++){
            if(this.getBit(n) == true){
                notLW.clearBit(n);
            }
            else{
                notLW.setBit(n);
            }
            
        }
        if(this.Longword.get(31) == true){
             notLW.setBit(31);
        }
        else{
            notLW.clearBit(31);
        }
        return notLW;
    }

    public Longword and(Longword other){
        Longword triple = new Longword();

        for(int n = 0; n<32;n++){
            if(this.Longword.get(n) == true && other.getBit(n) == true){
                triple.setBit(n);
            }
            else{
                triple.clearBit(n);
            }
        }
        return triple;
    }

    public Longword or(Longword other){
        Longword triple = new Longword();

        for(int n = 0; n<32;n++){
            if(this.getBit(n) == true || other.getBit(n) == true){
                triple.setBit(n);
            }
            else{
                triple.clearBit(n);
            }
        }
            return triple;
        
    }

    public Longword xor(Longword other){
        Longword triple = new Longword();

        for(int n = 0; n<32; n++){
            if((this.getBit(n) == true && other.getBit(n)==true) || 
            (this.getBit(n) == false && other.getBit(n)==false)){
                triple.setBit(n);
            }
            else{
                triple.clearBit(n);
            }
        }
        return triple;
        }
        
        public boolean isZero(){
            boolean isZero = false;
            for(int n = 0;n<32;n++){
                if(this.getBit(n) == true){
                    return isZero;
                }
                if(n == 31){
                    isZero = true;
                }
            }
            return isZero;
        }
}
