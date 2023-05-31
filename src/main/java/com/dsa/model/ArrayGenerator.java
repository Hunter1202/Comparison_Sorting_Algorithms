package com.dsa.model;

import java.lang.reflect.Array;
import java.util.Random;

import static com.dsa.model.SortAlgorithm.Show;

public class ArrayGenerator {
    public static int[] generateReverseSortedArray(int size) {

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        Show(array);
        return array;
    }

    public static int[] generateNearlySortedArray(int size, int swaps) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }

        // Perform random swaps
        for (int i = 0; i < swaps; i++) {
            int index1 = (int) (Math.random() * size);
            int index2 = (int) (Math.random() * size);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        Show(array);
        return array;
    }

    public static int[] generateFewUniqueArray(int size, int uniqueElements) {
        int[] array = new int[size];
        int segmentSize = size / uniqueElements;

        for (int i = 0; i < uniqueElements; i++) {
            int value = i + 1;
            int startIndex = i * segmentSize;
            int endIndex = (i + 1) * segmentSize;

            for (int j = startIndex; j < endIndex; j++) {
                array[j] = value;
            }
        }

        // Randomly shuffle the array
        for (int i = 0; i < size; i++) {
            int randomIndex = (int) (Math.random() * size);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
        Show(array);
        return array;
    }

    public static int[] generateRandomArray(int size, int bound) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);
        }
        Show(array);
        return array;
    }

//    public static void main(String[] args) {
//        generateNearlySortedArray(100, 2);
//        generateReverseSortedArray(100);
//    }
}
