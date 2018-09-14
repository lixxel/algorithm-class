// File: SortInterface.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: An interface for sorting classes
// Classes: SortInterface

interface SortInterface {
   public void recursiveSort(int[] list) throws UnsortedException;
   public void iterativeSort(int[] list) throws UnsortedException;
   public int getCount();
   public long getTime();
}