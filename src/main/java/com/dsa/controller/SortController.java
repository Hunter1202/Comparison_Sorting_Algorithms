package com.dsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.dsa.model.ArrayGenerator.*;
import static com.dsa.model.SortAlgorithm.*;


@Controller
public class SortController {

    @GetMapping ("/")
    public String index(){
        return "index";
    }

    @GetMapping ("/Visualize")
    public String Visualize(){
        return "Visualize";
    }

    @PostMapping("/compare")
    public String compareSort(@RequestParam double numb,
                              @RequestParam double bound,
                              @RequestParam("algorithm1") String algorithm1,
                              @RequestParam("algorithm2") String algorithm2,
                              @RequestParam("arrayType") String arrayType,
                              Model model) {

        // Generate the array based on the selected array type
        int[] array;
        switch (arrayType) {
            case "ReverseSortedArray":
                array = generateReverseSortedArray((int) numb);
                System.out.println("ReverseSortedArray");
                //Show(array);
                break;
            case "NearlySortedArray":
                array = generateNearlySortedArray((int) numb, 3);
                System.out.println("NearlySortedArray");
                //Show(array);
                break;
            case "FewUniqueArray":
                array = generateFewUniqueArray((int) numb, 3);
                System.out.println("FewUniqueArray");
                //Show(array);
                break;
            case "RandomArray":
            default:
                array = generateRandomArray((int) numb, (int) bound);
                System.out.println("RandomArray");
                //Show(array);
                break;
        }

        long startTime, endTime;

        // Sort using Algorithm 1
        startTime = System.currentTimeMillis();
        performSorting(array, algorithm1);
        endTime = System.currentTimeMillis();
        long algorithm1Time = endTime - startTime;

        // Sort using Algorithm 2
        startTime = System.currentTimeMillis();
        performSorting(array, algorithm2);
        endTime = System.currentTimeMillis();
        long algorithm2Time = endTime - startTime;

        //Pass the input to the view
        model.addAttribute("numb", numb);
        model.addAttribute("bound", bound);
        model.addAttribute("arrayType", arrayType);

        //Pass the selected algorithms and their respective comparison times to the view
        model.addAttribute("algorithm1", algorithm1);
        model.addAttribute("algorithm1Time", algorithm1Time);
        model.addAttribute("algorithm2", algorithm2);
        model.addAttribute("algorithm2Time", algorithm2Time);
//        model.addAttribute("sortedArray", a);


        //show who's the winner
        if(algorithm1Time > algorithm2Time){
            model.addAttribute("winner", algorithm2);
        } if(algorithm1Time < algorithm2Time){
            model.addAttribute("winner", algorithm1);
        } if (algorithm1Time == algorithm2Time){
            model.addAttribute("winner", "Hòa");
        }

        return "index";
    }


    @GetMapping("/sortAll")
    public String sortAllIndex() {
        return "sortAll";
    }

    @PostMapping("/sortAllArr")
    public String sortAll(@RequestParam double numb,
                          @RequestParam double bound,
                          @RequestParam(value = "arrayType", required = false) List<String> arrayTypes,
                          @RequestParam(value = "algorithms", required = false) List<String> algorithms,
                          Model model) {

        // Generate the arrays base on the selected array types
        List<int[]> arrays = new ArrayList<>();
        for (String arrayType : arrayTypes) {
            switch (arrayType) {
                case "ReverseSortedArray":
                    arrays.add(generateReverseSortedArray((int) numb));
                    System.out.println("ReverseSortedArray");
                    break;
                case "NearlySortedArray":
                    arrays.add(generateNearlySortedArray((int) numb, 3));
                    System.out.println("NearlySortedArray");
                    break;
                case "FewUniqueArray":
                    arrays.add(generateFewUniqueArray((int) numb, 3));
                    System.out.println("FewUniqueArray");
                    break;
                case "RandomArray":
                default:
                    arrays.add(generateRandomArray((int) numb, (int) bound));
                    System.out.println("RandomArray");
                    break;
            }
        }

        // algorithmTimes HashMap to save the sorting time of chosen algorithms for each array type
        Map<String, Map<String, Long>> algorithmTimes = new LinkedHashMap<>();

        // Iterate over the selected algorithms for each array type
        for (int i = 0; i < arrayTypes.size(); i++) {
            String arrayType = arrayTypes.get(i);
            int[] a = arrays.get(i);

            Map<String, Long> algorithmTimesForArrayType = new LinkedHashMap<>();
            if (algorithms != null && !algorithms.isEmpty()) {
                for (String algorithm : algorithms) {
                    long startTime, endTime;
                    startTime = System.currentTimeMillis();
                    performSorting(a, algorithm);
                    endTime = System.currentTimeMillis();
                    long algorithmTime = endTime - startTime;
                    algorithmTimesForArrayType.put(algorithm, algorithmTime);
                }
            }
            heapSortConfig(algorithmTimesForArrayType);
            algorithmTimes.put(arrayType, algorithmTimesForArrayType);
        }

        model.addAttribute("numb", numb);
        model.addAttribute("bound", bound);
        model.addAttribute("arrayType", arrayTypes);
        model.addAttribute("algorithmTimes", algorithmTimes);
        return "sortAll";
    }


    private void performSorting(int[] arr, String algorithm) {

        switch (algorithm) {
            case "bubbleSort" -> {
                int[] bubbleSortArray = Arrays.copyOf(arr, arr.length);
                bubbleSort(bubbleSortArray);
                //System.out.println("Sap xep boi bubbleSort: ");
                //Show(bubbleSortArray);
            }
            case "bubbleSort2" -> {
                int[] bubbleSortArray2 = Arrays.copyOf(arr, arr.length);
                bubbleSort2(bubbleSortArray2);
                //System.out.println("Sap xep boi bubbleSort2: ");
                //Show(bubbleSortArray2);

            }
            case "SelectionSort" -> {
                int[] SelectionSortArray = Arrays.copyOf(arr, arr.length);
                SelectionSort(SelectionSortArray);
            }
            case "SelectionSort2" -> {
                int[] SelectionSortArray2 = Arrays.copyOf(arr, arr.length);
                SelectionSort2(SelectionSortArray2);
            }
            case "InsertionSort" -> {
                int[] InsertionSortArr = Arrays.copyOf(arr, arr.length);
                InsertionSort(InsertionSortArr);
            }
            case "InsertionSort2" -> {
                int[] InsertionSortArr2 = Arrays.copyOf(arr, arr.length);
                InsertionSort2(InsertionSortArr2);
            }
            case "quickSort" -> {
                int[] quickSortArr = Arrays.copyOf(arr, arr.length);
                quickSort(quickSortArr, 0, quickSortArr.length - 1);
            }
            case "quickSort2" -> {
                int[] quickSortArr2 = Arrays.copyOf(arr, arr.length);
                QuickSort2(quickSortArr2, 0, quickSortArr2.length - 1);
            }
            case "mergeSort" -> {
                int[] mergeSortArr = Arrays.copyOf(arr, arr.length);
                mergeSort(mergeSortArr, 0, mergeSortArr.length - 1);
            }
            case "RadixSort" -> {
                int[] RadixSortArr = Arrays.copyOf(arr, arr.length);
                RadixSort(RadixSortArr);
            }
            case "heapSort" -> {
                int[] heapSortArr = Arrays.copyOf(arr, arr.length);
                heapSort(heapSortArr);
            }
            case "countingSort" -> {
                int[] countingSortArr = Arrays.copyOf(arr, arr.length);
                countingSort(countingSortArr);
            }
            // Add cases for other sorting algorithms

            }
        }
    }



