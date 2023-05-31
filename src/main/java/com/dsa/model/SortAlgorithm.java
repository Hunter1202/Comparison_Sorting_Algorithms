package com.dsa.model;

import java.util.*;

public class SortAlgorithm {

    public static String Show(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.print("]\n");
        return null;
    }

    /**bubbleSort*/
    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**bubbleSort Improvement*/
    public static void bubbleSort2(int arr[]) {
        int size = arr.length;
        for(int i=0; i < size; ++i) {
            boolean is_sorted = true;
            for (int j = 1; j < size-i; ++j) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    is_sorted = false;
                }
            }
            if(is_sorted) return;
        }
    }

    /**SelectionSort*/
    public static void SelectionSort(int a[]){
        int n = a.length;
        int indexMin, i, j;

        for (i = 0; i < n - 1; i++) {
            // set Minimum element
            indexMin = i;

            // searching for Minimum element
            for (j = i + 1; j < n; j++) {
                if (a[j] < a[indexMin]) {
                    indexMin = j;
                }
            }
            if (indexMin != i) {
                // Swap Minimum element with the last item
                int temp = a[indexMin];
                a[indexMin] = a[i];
                a[i] = temp;
            }
        }
    }

    /**SelectionSort improvement*/
    public static void SelectionSort2(int[] arr) {
        int n = arr.length;
        int left = 0; // Vị trí bắt đầu của đoạn chưa được sắp xếp
        int right = n - 1; // Vị trí kết thúc của đoạn chưa được sắp xếp

        while (left < right) {
            int minIdx = left;
            int maxIdx = right;

            // Tìm phần tử nhỏ nhất và phần tử lớn nhất trong đoạn chưa được sắp xếp
            for (int i = left; i <= right; i++) {
                if (arr[i] < arr[minIdx]) {
                    minIdx = i;
                }
                if (arr[i] > arr[maxIdx]) {
                    maxIdx = i;
                }
            }

            // Hoán đổi phần tử nhỏ nhất với phần tử đầu tiên của đoạn chưa được sắp xếp
            swapSelectionSort2(arr, left, minIdx);

            // Nếu phần tử lớn nhất đang ở vị trí đầu tiên, hoán đổi nó với phần tử cuối cùng của đoạn chưa được sắp xếp
            if (maxIdx == left) {
                maxIdx = minIdx;
            }

            // Hoán đổi phần tử lớn nhất với phần tử cuối cùng của đoạn chưa được sắp xếp
            swapSelectionSort2(arr, right, maxIdx);

            // Giảm đoạn chưa được sắp xếp bằng cách tăng vị trí bắt đầu và giảm vị trí kết thúc
            left++;
            right--;
        }
    }

    // Phương thức hoán đổi hai phần tử trong mảng
    private static void swapSelectionSort2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**InsertionSort*/
    public static void InsertionSort(int a[]){
        for (int i = 0; i < a.length; i++) {
            int next = a[i];
            int j;
            for (j = i - 1; j >= 0 && a[j] > next; --j) {
                a[j + 1] = a[j];
            }
            a[j + 1] = next;
        }
    }

    /**InsertionSort improvement*/
    public static void InsertionSort2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = binarySearch(arr, key, 0, i - 1);

            // Di chuyển các phần tử lớn hơn key về sau
            System.arraycopy(arr, j, arr, j + 1, i - j);

            // Chèn key vào vị trí đúng
            arr[j] = key;
        }
    }

    // Tìm vị trí chèn phần tử key trong đoạn đã được sắp xếp
    public static int binarySearch(int[] arr, int key, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**quickSort*/ //using stack auxiliary to avoid stack-overflow
    public static void quickSort(int[] arr, int low, int high) {
        // Create an auxiliary stack
        int[] stack = new int[high - low + 1];

        // Initialize top of stack
        int top = -1;

        // Push initial values of low and high to the stack
        stack[++top] = low;
        stack[++top] = high;

        // Keep popping from stack until it is empty
        while (top >= 0) {
            // Pop high and low
            high = stack[top--];
            low = stack[top--];

            // Set pivot element at its correct position in the sorted array
            int pivotIndex = partition(arr, low, high);

            // If there are elements on the left side of pivot,
            // then push left side to the stack
            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }

            // If there are elements on the right side of pivot,
            // then push right side to the stack
            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swapquickSort(arr, i, j);
            }
        }

        swapquickSort(arr, i + 1, high);
        return i + 1;
    }

    private static void swapquickSort(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**QuickSort improvement*/
    public static void QuickSort2(int[] arr, int low, int high) {
        // Create an auxiliary stack
        int[] stack = new int[high - low + 1];
        int top = -1;

        // Push initial values of low and high to the stack
        stack[++top] = low;
        stack[++top] = high;

        // Process elements in the stack
        while (top >= 0) {
            // Pop high and low
            high = stack[top--];
            low = stack[top--];

            // Set pivotIdx
            int pivotIdx = randomPartition(arr, low, high);

            // If there are elements on the left side of the pivot, push them to the stack
            if (pivotIdx - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIdx - 1;
            }

            // If there are elements on the right side of the pivot, push them to the stack
            if (pivotIdx + 1 < high) {
                stack[++top] = pivotIdx + 1;
                stack[++top] = high;
            }
        }
    }

    private static int randomPartition(int[] arr, int low, int high) {
        Random random = new Random();
        int randomIdx = random.nextInt(high - low + 1) + low; // Chọn ngẫu nhiên một chỉ số từ low đến high
        swappartitionQuickSort2(arr, randomIdx, high); // Hoán đổi giá trị của phần tử ngẫu nhiên với phần tử cuối cùng (pivot)
        return partitionQuickSort2(arr, low, high); // Áp dụng phân hoạch cho mảng đã hoán đổi
    }

    private static int partitionQuickSort2(int[] arr, int low, int high) {
        int pivot = arr[high]; // Chọn pivot là phần tử cuối cùng
        int i = low - 1; // Chỉ số i để duyệt qua các phần tử nhỏ hơn pivot

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) { // Nếu phần tử đang xét nhỏ hơn pivot
                i++;
                swappartitionQuickSort2(arr, i, j); // Hoán đổi phần tử nhỏ hơn pivot với phần tử tại chỉ số i
            }
        }

        swappartitionQuickSort2(arr, i + 1, high); // Đưa pivot về vị trí đúng trong mảng đã phân hoạch
        return i + 1; // Trả về chỉ số của pivot
    }

    private static void swappartitionQuickSort2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**mergeSort*/
    public static void mergeSort(int arr[], int l, int r){
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    private static void merge(int arr[], int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    /**HeapSort*/
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**countingSort*/
    public static int[] countingSort(int[] arr){
        int max = findMax(arr);
        int min = findMin(arr);
        int range = max-min+1;
        int count[] = new int[range];
        int result[] = new int[arr.length];
        // Đếm số lần xuất hiện
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        // Tổng tích lũy
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }
        for (int i = arr.length-1; i >= 0; i--) {
            result[count[arr[i] - min]-1] = arr[i];
            count[arr[i]-min]--;
        }
        return result;
    }

    private static int findMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    private static int findMin(int[] arr){
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]){
                min = arr[i];
            }
        }
        return min;
    }

    /**RadixSort*/
    public static void RadixSort(int[] arr){
        int max = findMax(arr);

        for (int exp = 1; max/exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp){
        int[] result = new int[arr.length];
        int[] count = new int[10];

        for (int i = 0; i < arr.length; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        for (int i = arr.length-1; i >= 0; i--) {
            int digit = (arr[i]/exp) % 10;
            result[count[digit]-1] = arr[i];
            count[digit]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }
    private static int digitAt(int x, int i){
        while (i-- > 0){
            x /= 10;
        }
        return x%10;
    }

    private int numDigit(int[] arr){
        int element = findMax(arr);
        int count=0;
        while(element>0){
            element /= 10;
            count++;
        }
        return count;
    }

    private static int findMaxRadixSort(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    /**heapSortConfig for ranking*/
    public static void heapSortConfig(Map<String, Long> map) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());

        int n = list.size();

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(list, n, i);

        // Extract elements from the heap in decreasing order
        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(list, 0, i);
            heapify(list, i, 0);
        }

        // Update the map with the sorted list
        map.clear();
        for (Map.Entry<String, Long> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    private static void heapify(List<Map.Entry<String, Long>> list, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && list.get(left).getValue() > list.get(largest).getValue())
            largest = left;

        if (right < size && list.get(right).getValue() > list.get(largest).getValue())
            largest = right;

        if (largest != i) {
            Collections.swap(list, i, largest);
            heapify(list, size, largest);
        }
    }
}

