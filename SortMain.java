// File: SortMain.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: Test the efficiency of a selection sort
// Classes: SortMain, BenchmarkSorts, YourSort, SortInterface, UnsortedException

public class SortMain{

   //warming up for benchmarking
   static {
      //long start = System.nanoTime();
      ManualClassLoader.load();
      //long end = System.nanoTime();
      //System.out.println("Warm-up time: " + (end-start));
   }
   
   //main()
   public static void main (String [] args) {
      int[] sizes = {10,100,250,500,1000,2500,5000,7500,10000,25000};
      
      BenchmarkSorts sorter = new BenchmarkSorts(sizes);
      sorter.runSorts();
      sorter.displayReport();
      
   }


}


// found warmup example at https://www.baeldung.com/java-jvm-warmup
class Dummy {
   public void m(){
   }
}

class ManualClassLoader {
   protected static void load() {
      for (int i = 0; i < 100000; i++) {
         Dummy dummy = new Dummy();
         dummy.m();
      }
   }
}