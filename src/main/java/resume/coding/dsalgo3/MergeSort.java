package resume.coding.dsalgo3;

import java.util.Arrays;

public class MergeSort implements IMutableSort {

    @Override
    public void sort(int[] a) {
        mergeSort(a, 0, a.length);
    }

    private static void mergeSort(int[] a, int i, int j) {
        if (j - i <= 1) {
            return;
        }

        int mid = (i + j + 1) / 2;
        mergeSort(a, i, mid);
        mergeSort(a, mid, j);
        merge(a, i, mid, j);
    }

    private static void merge(int[] a, int i, int mid, int j) {
        // copyOfRange会复制一个数组的元素到一个新数组，且如果下标越界会补零值
        int[] b = Arrays.copyOfRange(a, i, mid+1);
        int[] c = Arrays.copyOfRange(a, mid, j+1);

        // 哨兵，保证在合并的时候不会引用数组界外的值
        b[b.length-1] = c[c.length-1] = Integer.MAX_VALUE;

        int bi = 0, ci = 0;
        int k;
        for (k = i; k < j; k++) {
            if (b[bi] < c[ci]) {
                a[k] = b[bi++];
            } else {
                a[k] = c[ci++];
            }
        }
    }
}
