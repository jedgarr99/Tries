/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;



/**
 *
 * @author Jorge Edgar Rodriguez Ortiz Loyola 181334
 * @param <T>
 */
public class Sort<T extends Comparable<T>> {

    public int quickCont = 0;

    //SELECTION-SORT:
    public int selectionSort(T[] arr) {
        int min, cont;
        cont = 0;
        T aux;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                cont++;
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            aux = arr[i];
            arr[i] = arr[min];
            arr[min] = aux;
        }
        return cont;
    }

    public void selectionSort1(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int pos = i;
            T min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(min) < 0) {
                    min = arr[j];
                    pos = j;
                }
            }
            T aux = arr[i];
            arr[i] = min;
            arr[pos] = aux;
        }
    }

    //INSERTION-SORT:
    public int insertionSort(T[] arr) {
        int rec, pos, cont;
        T aux;
        cont = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            rec = i;
            pos = i + 1;
            cont++;
            while (rec >= 0 && arr[rec].compareTo(arr[pos]) > 0) {

                aux = arr[rec];
                arr[rec] = arr[pos];
                arr[pos] = aux;
                rec--;
                pos--;
            }
        }
        return cont;
    }

    public T[] insertionSort1(T[] array) {
        for (int j = 1; j < array.length; j++) {

            // Picking up the key(Card)
            T key = array[j];
            int i = j - 1;

            while (i >= 0 && key.compareTo(array[i]) < 0) {
                array[i + 1] = array[i];
                i--;
            }
            // Placing the key (Card) at its correct position in the sorted subarray
            array[i + 1] = key;
        }
        return array;
    }

    //CLASSICAL-BUBBLE-SORT:
    public void bubbleSortClasico(T[] arr) {
        T aux;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    aux = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = aux;
                }
            }
        }

    }

    public int bubbleSort(T[] arr) {
        int cont = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                cont++;
                if (arr[j + 1].compareTo(arr[j]) < 0) {
                    T aux = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = aux;
                }
            }
        }
        return cont;
    }

    //DIEGO'S-BUBBLE-SORT:
    //Al utilizar una bandera que me diga cuando ya todo está ordenado, no
    //tengo la necesidad de recorrer n veces el arreglo preguntando n veces lo mismo. 
    public void bubbleSortDiego(T[] arr) {
        T aux;
        boolean flag = false;

        while (!flag) {
            flag = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    flag = false;
                    aux = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = aux;
                }
            }
        }
    }

    //DIEGO'S-OPTIMIZED-BUBBLE-SORTED:
    //Además de lo mencionado en el método anterior, aquí no sólo no recorro todo n veces, sino que 
    //cada vez que recorro para preguntar nuevamente si hay aglo en desorden no hago el recorrido n veces, pues
    //asumo que las veces que ya he pasado, he colocado ordenadamente el mayor en la útlima posición, por lo cual 
    //ya no tengo que llegar al final del arreglo.
    public void bubbleSortDiegoOptimizado(T[] arr) {
        T aux;
        int j = 1;
        boolean flag = false;

        while (!flag && j >= 0) {
            flag = true;
            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i + 1].compareTo(arr[i]) > 0) {
                    flag = false;
                    aux = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = aux;
                }
            }
            j++;
        }
    }

    //QUICK-SORT:
    /* Complejidad: O(n^2)
    ** Nota: Para que un hacker no abuse de mi método hay que agarrar un número aleatorio. */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);

        return array;
    }

    /**
     * The sorting process
     *
     * @param left The first index of an array
     * @param right The last index of an array
     * @param array The array to be sorted
     *
     */
    private <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = randomPartition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    /**
     * Ramdomize the array to avoid the basically ordered sequences
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array
     * @return the partition index of the array
     */
    private <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array Finds the partition index of an
     * array
     *
     */
    private <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) / 2;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                ++left;
            }
            while (less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    public int getQuickCont() {
        return this.quickCont;
    }

    public void setQuickCont(int n) {
        this.quickCont = n;
    }

    public <T extends Comparable<T>> boolean less(T v, T w) {
        quickCont++;
        return v.compareTo(w) < 0;
    }

    static <T> boolean swap(T[] array, int idx, int idy) {
        T swap = array[idx];
        array[idx] = array[idy];
        array[idy] = swap;
        return true;
    }

    public void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int posPivot = partition(low, high, arr);
            quickSort(arr, low, posPivot);
            quickSort(arr, posPivot + 1, high);
        }
    }

    public int partition(int low, int high, T[] arr) {
        T pivot = arr[low];
        int i = low, j = high;
        while (i < j) {
            do {
                i++;
            } while (arr[i].compareTo(pivot) <= 0);

            do {
                j--;
            } while (arr[j].compareTo(pivot) > 0);

            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, low, j);
        return j;
    }

    private void swap(T[] arr, int a, int b) {
        T aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }

    //RECURSIVE MERGE_SORT:
    public <T extends Comparable<T>> T[] mergeSort(T[] unsorted) {
        T[] tmp = (T[]) new Comparable[unsorted.length];
        doSort(unsorted, tmp, 0, unsorted.length - 1);
        return unsorted;
    }

    /**
     * @param arr The array to be sorted
     * @param temp The copy of the actual array
     * @param left The first index of the array
     * @param right The last index of the array Recursively sorts the array in
     * increasing order
     *
     */
    private <T extends Comparable<T>> void doSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(arr, temp, left, mid);
            doSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }

    }

    /**
     * This method implements the merge step of the merge sort
     *
     * @param arr The array to be sorted
     * @param temp The copy of the actual array
     * @param left The first index of the array
     * @param mid The middle index of the array
     * @param right The last index of the array merges two parts of an array in
     * increasing order
     *
     */
    private <T extends Comparable<T>> void merge(T[] arr, T[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            quickCont++;
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }

    public void mergeSort2(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int mid = array.length / 2;
        // Split left part 
        T[] left = (T[]) new Comparable[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        // Split right part 
        T[] right = (T[]) new Comparable[array.length - mid];
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        mergeSort(left);
        mergeSort(right);
        int i = 0, j = 0, k = 0;
        // Merge left and right arrays 
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        // Collect remaining elements 
        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }

    }

    //ITERATIVE MERGE_SORT:
    /*Merge subarrays in bottom up manner. First merge subarrays of size 1 to create sorted  
      subarrays of size 2, then merge subarrays of size 2 to create sorted subarrays of size 4, 
      and so on. */
    public int iterativeMergeSort(T arr[]) {
        int n = arr.length, cont;
        cont = 0;
        // For current size of subarrays to be merged curr_size varies from  1 to n/2 
        for (int curr_size = 1; curr_size <= n - 1; curr_size = 2 * curr_size) {

            // Pick starting point of different subarrays of current size 
            // For picking starting index of left subarray to be merged 
            for (int left_start = 0; left_start < n - 1; left_start += 2 * curr_size) {
                // Find ending point of left subarray. mid+1 is starting point of right: 
                int mid = Math.min(left_start + curr_size - 1, n - 1);

                int right_end = Math.min(left_start + 2 * curr_size - 1, n - 1);

                // Merge Subarrays arr[left_start...mid] & arr[mid+1...right_end]:
                cont += merge(arr, left_start, mid, right_end);
                //mergeDiegoIndices(arr, left_start, right_end);

            }
        }
        return cont;
    }

    //MERGE-FUNCTION:
    /* Function to merge the two haves arr[l..m] and arr[m+1..r] of array arr[] */
    public int merge(T arr[], int l, int m, int r) {
        int i, j, k, cont;
        int n1 = m - l + 1;
        int n2 = r - m;
        cont = 0;

        /* create temp arrays */
        T L[] = (T[]) new Comparable[n1];
        T R[] = (T[]) new Comparable[n2];

        /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            cont++;
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        /* Copy the remaining elements of L[], if there are any */
        while (i < n1) {
            arr[k++] = L[i++];
        }
        /* Copy the remaining elements of R[], if there are any */
        while (j < n2) {
            arr[k++] = R[j++];
        }
        return cont;
    }

    public void mergeIndices(T[] arr, int low, int high) {

        T[] aux = (T[]) new Comparable[high + 1 - low];

        for (int c = low; c < high + 1; c++) {
            aux[c - low] = arr[c];
        }

        int i = 0;
        int mid = (int) Math.floor(aux.length / 2);
        int j = mid;

        while (i < mid && j < aux.length) {
            if (aux[i].compareTo(aux[j]) < 0) {
                arr[low++] = aux[i++];
            } else {
                arr[low++] = aux[j++];
            }
        }

        while (i < mid) {
            arr[low++] = aux[i++];
        }

        while (j < aux.length) {
            arr[low++] = aux[j++];
        }
    }

    //DIEGO'S-MERGE-FUNCTION:
    /**
     * Only copy half of the array, instead of copy the complete array
     */
    public void mergeDiegoIndices(T[] arr, int low, int high) {
        if (arr == null | high <= low | arr.length <= 1) {
            return;
        }

        T[] aux = (T[]) new Comparable[(high + 1 - low) / 2]; //Create an aux array with the middle of the length to sort.

        int mitad = Math.floorDiv((high + low), 2);

        //Copiar la mitad del arreglo al auxiliar:
        for (int a = low; a < mitad + 1; a++) {
            aux[a - low] = arr[a];
        }

        int i = low;      //Index ordered array
        int j = mitad + 1;    //Index top array
        int a = 0;      //Index low/aux array 

        //Compare and merge:
        while (a < aux.length && j < high + 1) {
            if (aux[a].compareTo(arr[j]) < 0) {
                arr[i++] = aux[a++];
            } else {
                arr[i++] = arr[j++];
            }
        }

        while (a < aux.length) {
            arr[i++] = aux[a++];
        }

        while (j < high + 1) {
            arr[i++] = arr[j++];
        }
    }


    public double average(int[] arr) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        res = res / arr.length;
        return res;
    }

    public double average(long[] arr) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        res = res / arr.length;
        return res;
    }

}
