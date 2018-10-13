// File: SelectionSort.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: Run a selection sort
// Classes: SelectionSort

class SelectionSort implements SortInterface {
   
   private int count;
   private long time;
   
   public SelectionSort() {
      count = 0;
      time = 0;
   } //end default constructor
   
   @Override
   public void recursiveSort(int[] list) throws UnsortedException {
      count = 0;
      long start = System.nanoTime();
      recursiveSortInner(list, 0);
      long time = System.nanoTime() - start;
   } //end recursiveSort()
   
   private void recursiveSortInner(int[] list, int j){
      //critical operation, comparing j to list length (default)
      count++;
      if (j >= list.length - 1) return;
      
      int min = j;
      for (int i = j + 1; i < list.length; i++) {
         count ++; //critical operation, comparing each item to min
         if (list[i] < list [min]) min = i;
      }
      count+=3; //critical operations, assignments
      int temp = list[j];
      list[j] = list[min];
      list[min] = temp;
      
      recursiveSortInner(list, j + 1);

   }
   
   @Override
   public void iterativeSort(int[] list) throws UnsortedException {
      count = 0;
      long start = System.nanoTime();
      int n = list.length;
      
      for (int j=0; j < n-1; j++) {
         int min = j;
         
         for (int i = j+1; i < n; i++){
            count++; //critical operation, comparing each item to the min
            if (list[i] < list[min]) {
               min = i;
            }
         }
         count+=3; //critical operation, assignments
         int temp = list[min];
         list[min] = list[j];
         list[j] = temp;
      }
      time = System.nanoTime() - start;
   } //end iterativeSort()
   
   @Override
   public int getCount() {
      return count;
   } //end getCount()
   
   @Override
   public long getTime() {
      return time;
   } //end getTime()
   
   public void checkSorted(int[] list) throws UnsortedException {
      for (int i = 0; i < list.length - 1; i++){
         if (list[i] > list[i+1]) throw new UnsortedException("Array not sorted correctly.");
      }
   }
   
   
}//end class SelectionSort