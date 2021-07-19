package resume.coding.dsalgo3;

public class BubbleSort implements IMutableSort {

    @Override
    public void sort(int[] a) {
        for (int i = a.length-1; i >= 0; i--) {
            bubble(a, 0, i+1);
        }
    }

    // 冒泡排序比选择排序慢的原因是写操作更多
    // 读内存可能直接读的是cpu缓存的值，而写内存可能会写缓存+内存（开销）
    // 读cpu缓存和内存的速度差别在10~100倍（一级、二级、三级）
    private static void bubble(int[] a, int i, int j) {
        for (; i < j-1; i++) {
            if (a[i] > a[i+1]) {
                Helper.swap(a, i, i+1);
            }
        }
    }
}
