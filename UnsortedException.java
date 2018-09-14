// File: UnsortedException.java
// Date: 9/16/18
// Author: Steph Anderson
// Purpose: Throws an UnsortedException used by sorting class
// Classes: UnsortedException

class UnsortedException extends Exception {
   public UnsortedException() {
      super();
   }
   
   public UnsortedException(String s) {
      super(s);
   }
}