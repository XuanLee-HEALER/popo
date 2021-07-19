package resume.coding.dsalgo3;

import java.util.List;
import java.util.stream.Collectors;

public class QuickSort implements IImmutableSort<Integer> {

    @Override
    public List<Integer> sort(List<Integer> l) {
        return quickSort(l);
    }

    // 中间值很重要
    private static List<Integer> quickSort(List<Integer> l) {
        if (l.size() <= 1) {
            return l;
        }

        int r = l.get(0);
        List<Integer> left = l.stream().filter(x -> x < r).collect(Collectors.toList());
        List<Integer> mid = l.stream().filter(x -> x == r).collect(Collectors.toList());
        List<Integer> right = l.stream().filter(x -> x > r).collect(Collectors.toList());

        left = quickSort(left);
        right = quickSort(right);

        left.addAll(mid);
        left.addAll(right);
        return left;
    }
}
