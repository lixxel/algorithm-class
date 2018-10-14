// File: BenchmarkSorts.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: Run sort and display sorting report
// Classes: BenchmarkSorts

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class BenchmarkSorts {
   private int[] sizes;
   private double[][] arrayStats;
   SelectionSort selectionSorter;

   BenchmarkSorts(int[] sizes) {
      this.sizes = sizes;
      //0 iterative average counts
      //1 iterative std counts
      //2 iterative average times
      //3 iterative std times
      //4 recursive average counts
      //5 recursive std counts
      //6 recursive average times
      //7 recursive std times
      arrayStats = new double[sizes.length][8];
      selectionSorter = new SelectionSort();
   } //end BenchmarkSorts(int sizes) constructor

   public void runSorts() {
      //for each of the 10 array sizes
      for (int i = 0; i < sizes.length; i++) {
      
         //0 recursive counts
         //1 recursive times
         //2 iterative counts
         //3 iterative times
         long[][] arrayData = new long[4][50];
         
         //fifty times do
         for (int j = 0; j < 50; j++) {
            //create a random array
            int[] recursiveArray = randomArray(sizes[i]);
            //clone array
            int[] iterativeArray = recursiveArray.clone();
            
            try{
               //test recursive
               selectionSorter.recursiveSort(recursiveArray);
               //test sorted
               selectionSorter.checkSorted(recursiveArray);
               //record count
               arrayData[0][j] = (long) selectionSorter.getCount();
               //record time
               arrayData[1][j] = selectionSorter.getTime();
               
               //test iterative
               selectionSorter.iterativeSort(iterativeArray);
               //test sorted
               selectionSorter.checkSorted(iterativeArray);
               //record count
               arrayData[2][j] = (long) selectionSorter.getCount();
               //record time
               arrayData[3][j] = selectionSorter.getTime();
            } catch (UnsortedException e) {
               System.out.println(e.getMessage());
            }
         }
         
         //find averages and standard deviations
         for (int k = 0; k < 4; k ++) {
            long sum = 0;
            for (long d: arrayData[k]) sum += d;
            double average = 1.0d * sum / 50;
            arrayStats[i][k*2] = average;
            long std = 0;
            for (long d: arrayData[k]) std += Math.pow(d - average, 2);
            arrayStats[i][k*2 +1] = Math.sqrt(std / 50)/average;
         }
         
      }
   } //end runSorts()
   
   public void displayReport(){
      System.out.printf("%-12s | %-57s | %-57s |%n", "", "Recursive", "Iterative");
      for (int i = 0; i < 133; i++) System.out.print ("-");
      System.out.println("|");
      System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |%n", "", "Average", "Coefficient", "Average", "Coefficient", "Average", "Coefficient", "Average", "Coefficient");
      System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |%n", "Size", "Count", "of Count", "Time", "of Time", "Count", "of Count", "Time", "of Time");
      for (int i = 0; i < 133; i++) System.out.print ("-");
      System.out.println("|");
      for (int i = 0; i < sizes.length; i++){
         System.out.print(String.format("%-12s |", sizes[i]));
         //for (int j = 0; j < 8; j++){
         //   System.out.print(String.format(" %-12.2f |", arrayStats[i][j]));
         //}
         System.out.print(String.format(" %-12.2f |", arrayStats[i][0]));
         System.out.print(String.format(" %-12.2e |", arrayStats[i][1]));
         System.out.print(String.format(" %-12.2f |", arrayStats[i][2]));
         System.out.print(String.format(" %-12.2f |", arrayStats[i][3]));
         System.out.print(String.format(" %-12.2f |", arrayStats[i][4]));
         System.out.print(String.format(" %-12.2e |", arrayStats[i][5]));
         System.out.print(String.format(" %-12.2f |", arrayStats[i][6]));
         System.out.print(String.format(" %-12.2f |", arrayStats[i][7]));
         System.out.println("");
      }
   } //end displayReport()
   
   public void saveReport(){
      PrintWriter writer = null;
      try {
        writer = new PrintWriter(new File("SelectionSortReport.csv"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      StringBuilder sb = new StringBuilder();
      sb.append("Size,Avg Count_R,Coef Count_R,Avg Time_R,CoefTime_R,AvgCount_I,CoefCount_I,AvgTime_I,CoefTime_I\n");
      for (int i = 0; i < sizes.length; i++){
         sb.append(sizes[i]);
         sb.append(',');
         for (int j = 0; j < 8; j++){
            sb.append(arrayStats[i][j]);
            if (j<7) sb.append(',');
         }
         sb.append('\n');
      }
      System.out.println("Saving...");
      writer.write(sb.toString());
      writer.close();
   }
   
   private int[] randomArray (int arraySize){
      int[] array = new int[arraySize];
      for (int i = 0; i < arraySize; i++) {
         array[i] = (int) (Math.random() * 10000);
      }
      return array;
   } // end randomArray()
   
} //end class BenchmarkSorts