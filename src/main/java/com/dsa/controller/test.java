package com.dsa.controller;

import static com.dsa.model.ArrayGenerator.*;
import static com.dsa.model.SortAlgorithm.bubbleSort;

class SortAlgorithm {

    public static void main(String[] args) {

        /**
         * Branch prediction is a mechanism used in modern processors to guess the outcome of a conditional branch
         * (such as an if statement) before it is actually evaluated. The processor tries to predict whether the branch
         * will be taken or not taken based on previous branch outcomes.
         *
         * In the case of comparing the speed of sorting a unique array and a reverse-sorted array, branch prediction
         * can have an impact. When the array is already sorted in reverse order, the if statement inside the
         * bubble sort's inner loop (if (arr[j - 1] > arr[j])) is almost always true. This means that the processor's branch
         * prediction may consistently guess the outcome correctly, leading to efficient execution.
         *
         * On the other hand, when sorting a unique array, the if statement may be true or false randomly, as the array
         * contains different values. This can make the branch prediction less accurate, potentially leading to more
         * mispredictions and slower execution.
         */
        int n = 10000;
        int numIterations = 10;


        /**
         * The issue you're facing is likely due to JVM warm-up time. When you execute the sorting code for the first time,
         * the JVM needs to load and compile the code, which may introduce additional overhead and impact the timing results.
         * Subsequent executions of the same code benefit from the JVM being warmed up and may appear faster.
         */
        // Warm-up phase
        int[] dummy = generateRandomArray(n, 1000);
        bubbleSort(dummy);

        // Reverse-sorted array
        int[] a = generateReverseSortedArray(n);
        long totalTimeOfReverse = 0;
        for (int i = 0; i < numIterations; i++) {
            int[] copy = a.clone();
            long start = System.currentTimeMillis();
            bubbleSort(copy);
            long end = System.currentTimeMillis();
            totalTimeOfReverse += end - start;
        }
        long averageTimeOfReverse = totalTimeOfReverse / numIterations;
        System.out.println("averageTimeOfReverse" + averageTimeOfReverse);

        // Unique element array
        int[] b = generateFewUniqueArray(n, 2);
        long totalTimeOfUnique = 0;
        for (int i = 0; i < numIterations; i++) {
            int[] copy = b.clone();
            long start = System.currentTimeMillis();
            bubbleSort(copy);
            long end = System.currentTimeMillis();
            totalTimeOfUnique += end - start;
        }
        long averageTimeOfUnique = totalTimeOfUnique / numIterations;
        System.out.println("averageTimeOfUnique" + averageTimeOfUnique);

    }
}
