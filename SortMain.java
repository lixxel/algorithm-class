// File: SortMain.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: Test the efficiency of a selection sort
// Classes: SortMain, BenchmarkSorts, YourSort, SortInterface, UnsortedException

public class SortMain{

   
   public static void main (String [] args) {
      int[] sizes = {};
      
      BenchmarkSorts sorter = new BenchmarkSorts(sizes);
      sorter.runSorts();
      sorter.displayReport();
   }


}

