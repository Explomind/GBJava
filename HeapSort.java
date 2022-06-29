public class HeapSort {
    static void sort(int[] arrayToSort) {
        int arrayLength = arrayToSort.length;
        for (int i = arrayLength / 2 - 1; i >= 0; i--) {
            maxHeap(arrayToSort, arrayLength, i);
        }
        for (int i = arrayLength - 1; i >= 0; i--) {
            int tmp = arrayToSort[i];
            arrayToSort[i] = arrayToSort[0];
            arrayToSort[0] = tmp;
            maxHeap(arrayToSort, i, 0);
        }
    }

    static void maxHeap(int[] arrayToSort, int arrayLength, int i) {
        int maxPos = i;
        if (2 * i + 1 < arrayLength && arrayToSort[maxPos] < arrayToSort[2 * i + 1]) {
            maxPos = 2 * i + 1;
        }
        if (2 * i + 2 < arrayLength && arrayToSort[maxPos] < arrayToSort[2 * i + 2]) {
            maxPos = 2 * i + 2;
        }
        if (maxPos != i) {
            int tmp = arrayToSort[maxPos];
            arrayToSort[maxPos] = arrayToSort[i];
            arrayToSort[i] = tmp;
            maxHeap(arrayToSort, arrayLength, maxPos);
        }
    }

    public static void main(String[] args) {
        int[] intArray = new int[] { 4, 10, 3, 5, 1, 2, 7, 6, 9, 8 };
        System.out.println("Initial array:");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println();
        sort(intArray);
        System.out.println("Sorted array:");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println();
    }
}
