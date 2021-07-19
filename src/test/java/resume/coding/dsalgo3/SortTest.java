package resume.coding.dsalgo3;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortTest {

    @Test
    public void insertionSortTest() {
        sortTest(InsertionSort.class, 100_000);
    }

    @Test
    public void selectionSortTest() {
        sortTest(SelectionSort.class, 100_000);
    }

    // 测试排序稳定性
//    @Test
//    public void selectionSortStabilityTest() {
//
//    }

    @Test
    public void bubbleSortTest() {
        sortTest(BubbleSort.class, 100_000);
    }

    @Test
    public void mergeSortTest() {
        sortTest(MergeSort.class, 1_000_000);
    }

    @Test
    public void quickSortImmutableTest() {
        sortTest(QuickSort.class, 1_000_000);
    }

    @Test
    public void quickSortMutableTest() {
        sortTest(QuickSortMutable.class, 100_000);
    }

    @Test
    public void bucketSortTest() {
        int count = 1_000_000;
        List<Integer> testList = new ArrayList<>(count);
        while (count-- > 0) {
            testList.add((int)Math.floor(Math.random()*100));
        }

        var start = System.currentTimeMillis();
        assertSorted(new BucketSort().sort(testList).stream().mapToInt(x -> x).toArray());
        System.out.println("time usage: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public void sortTest(Class cls, int N) {
        try {
            var constructer = cls.getConstructor();
            var rawInst = constructer.newInstance();
            var start = System.currentTimeMillis();
            if (rawInst instanceof IMutableSort) {
                var A = gen(N).stream().mapToInt(x -> x).toArray();
                var sorter = (IMutableSort)rawInst;
                sorter.sort(A);
                System.out.println("time usage: " + (System.currentTimeMillis() - start) + " ms.");
                assertSorted(A);
            } else if (rawInst instanceof IImmutableSort) {
                var A = gen(N);
                var sorter = (IImmutableSort)rawInst;
                A = sorter.sort(A);
                System.out.println("time usage: " + (System.currentTimeMillis() - start) + " ms.");
                assertSorted(A.stream().mapToInt(x -> x).toArray());
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void assertSorted(int[] a) {
        int smallest = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (smallest <= a[i]) {
                smallest = a[i];
            } else {
                Assert.fail("Array not in sorted order");
            }
        }
    }

    public static List<Integer> gen(int N) {
        List<Integer> l = new ArrayList<>(N);
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < N; i++) {
            l.add(rand.nextInt());
        }
        return l;
    }
}
