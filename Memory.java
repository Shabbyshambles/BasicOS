import java.util.*;
public class Memory {

    ///setting fields
    BitSet memArray = new BitSet(255);

    ///creating the object
    public Memory(){
       this.memArray.clear(0, 255);
    }

    ///method for displaying the contents, used later by Computer function
    public void displayAll(){
        for(int i = 0; i<memArray.length(); i++){
            if(memArray.get(i) == true){
            System.out.print('1');
            }
            else{
                System.out.print('0');
            }
        }
        System.out.println(" ");
    }

    ///method for clearing all bits in memory
    public void clearAll(){
        memArray.clear(0, memArray.length());
    }

    ///read method
    public Longword read(Longword start, int numbytes){
        ///we get an unsigned integer representation of our longword to know what address to start at
        long address = start.getUnsigned();
        
        ///creating Longword that we will return and an int that we will use to track the indices of our Longword
        Longword readWord = new Longword();
        int j = 0;
        System.out.println("reading from address: " + address);
        ///logic to read our longwords values from memory
        for(int i = 0; i<numbytes;i++){
             
             if(this.memArray.get(i+(int)address) == true){  

                 readWord.setBit(j);
             }
             else{ 
                 readWord.clearBit(j);
             }
            
    
            j++;
        }
        System.out.println(" ");

    System.out.println(readWord.toString());
    return readWord;

    }

    ///write method
    public void write(Longword address, Longword toWrite, int numbytes){
          ///address to start at, int to track our Longwords indices
          long addr = address.getUnsigned();
          int j = 0;

          System.out.println("Longword we are writing: " +toWrite);
          System.out.println("Address " + addr);

          ///logic for populating memory from our longword
          for(int i = (int)addr;i<((int)addr+numbytes);i++){
                  if(toWrite.getBit(j) == true){
                      this.memArray.set(i);
                  }
                  else{
                      this.memArray.clear(i);
                  }
              
              j++;
          } 
          System.out.println("current memory: " + this.memArray.size());
    }



    
}
