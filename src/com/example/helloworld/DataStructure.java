package com.example.helloworld;


/**
 * Created by Julian on 15/9/7.
 */
public class DataStructure {
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        // print out values of even indices of the array
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext())
            System.out.printf(iterator.next() + " ");
        System.out.println();
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> {}

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface

    private class EvenIterator implements DataStructureIterator {
        // Start stepping through the array from beginning
        private int nextIndex = 0;

        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {
            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main(String[] args) {
        // Fill the array with integer values and print out only
        // values of even indices
        DataStructure dataStructure = new DataStructure();
        dataStructure.printEven();
    }
}
