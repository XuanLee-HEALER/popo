package resume.coding.dsalgo3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    // 排1_000_000个0~99的整数
    public List<Integer> sort(List<Integer> l) {
        int k = 100;
        List<List<Integer>> buckets = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            buckets.add(new LinkedList<>());
        }

        for (var e : l) {
            buckets.get(e%100).add(e);
        }

        List<Integer> result = new ArrayList<>(l.size());
        for (var b : buckets) {
            result.addAll(b);
        }

        return result;
    }
}
