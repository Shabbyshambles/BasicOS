public class MemTest {

    Longword toTest = new Longword();
    Longword Address = new Longword();

    public MemTest(Longword toTest, Longword Address){
        this.toTest = toTest;
        this.Address = Address;
    }

    public void runTests(){

        
        Memory myMemory = new Memory();
          System.out.println("Reading the first 5 bytes in toTest Longword. Expected value:000000");


        myMemory.read(toTest, 5);

        System.out.println("Reading from beginning. Expected value 0000000...");
        myMemory.read(toTest, 0);

        Longword Twofive = new Longword(255);
        Longword zero = new Longword(0);
        System.out.println("Writing number 255 to memory in address 0");

        myMemory.write(zero, Twofive, 32);
        System.out.println("Reading the number 255 that was placed in memory back out");
        myMemory.read(zero, 32);

        System.out.println("Clearing the memory and then printing it");
        myMemory.clearAll();
        myMemory.read(zero, 32);
        
    }
    
}
