package resume.coding.dsalgo3;

public class QuickSortMutable implements IMutableSort {

    @Override
    public void sort(int[] a) {
        quickSort(a, 0, a.length);
    }

    private static void quickSort(int[] a, int i, int j) {
        // 递归一定要写终止条件！
        if (j - i <= 1) {
            return;
        }

        int x = a[i];

        // l下标必须能遍历完无序序列，所以r=j而不是r=j-1
        int l = i + 1, r = j;
        while (l != r) {
            if (a[l] < x) {
                l++;
            } else {
                Helper.swap(a, l, --r);
            }
        }

        Helper.swap(a, i, l-1);

        quickSort(a, i, l-1);
        quickSort(a, l, j);
    }
}
