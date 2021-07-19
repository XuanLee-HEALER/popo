package resume.coding.dsalgo3;

public class SelectionSort implements IMutableSort {

    @Override
    public void sort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            int m = maxIndex(a, 0, i+1);
            Helper.swap(a, m, i);
        }
    }

    private static int maxIndex(int[] a, int i, int j) {
        int max = Integer.MIN_VALUE;
        int maxIndex = j - 1;

        for (; j > i; j--) {
            if (a[j-1] > max) {
                max = a[j-1];
                maxIndex = j-1;
            }
        }
        return maxIndex;
    }
}
