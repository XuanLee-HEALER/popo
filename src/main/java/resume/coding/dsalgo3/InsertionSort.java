package resume.coding.dsalgo3;

public class InsertionSort implements IMutableSort {

    @Override
    public void sort(int[] a) {
        // 默认手里有一张牌
        for (int i = 1; i < a.length; i++) {
            // 每次取一张牌，放到“手上”最后一张的位置（i），依次和之前的牌进行比较，直到找到一张不大于它的牌
            int c = a[i];
            int j = i;

            for (; j > 0 && c < a[j-1]; j--) {
                a[j] = a[j-1];
                a[j-1] = c;
            }
        }
    }
}
