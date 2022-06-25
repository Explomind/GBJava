
public class MergeSort {
    public static int[] sortArray(int[] arrayToSort) {
        if (arrayToSort == null) {
            return null;
        }
        if (arrayToSort.length < 2) {
            return arrayToSort;
        }
        int[] leftArray = new int[arrayToSort.length / 2];
        System.arraycopy(arrayToSort, 0, leftArray, 0, leftArray.length);
        int[] rightArray = new int[arrayToSort.length - arrayToSort.length / 2];
        System.arraycopy(arrayToSort, arrayToSort.length / 2, rightArray, 0, rightArray.length);
        leftArray = sortArray(leftArray);
        rightArray = sortArray(rightArray);
        return mergeArray(leftArray, rightArray);
    }

    public static int[] mergeArray(int[] leftArray, int[] rightArray) {
        int[] result = new int[leftArray.length + rightArray.length];
        int posLeft = 0;
        int posRight = 0;
        for (int i = 0; i < result.length; i++) {
            if (posLeft == leftArray.length) {
                result[i] = rightArray[posRight];
                posRight++;
            } else if (posRight == rightArray.length) {
                result[i] = leftArray[posLeft];
                posLeft++;
            } else if (leftArray[posLeft] < rightArray[posRight]) {
                result[i] = leftArray[posLeft];
                posLeft++;
            } else {
                result[i] = rightArray[posRight];
                posRight++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = sortArray(new int[] { 6, 1, 5, 2, 4, -5, 0, 12 });
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
