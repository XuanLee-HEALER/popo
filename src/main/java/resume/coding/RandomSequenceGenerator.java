package resume.coding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

// 返回一个无限个数的随机序列
public class RandomSequenceGenerator<T> implements Iterable<T> {

    private List<T> list;

    public RandomSequenceGenerator(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return list.get((int) (Math.random() * list.size()));
            }
        };
    }

    public static void main(String[] args) {
        List<String> animals = Stream.of("猫", "狗", "蛇").toList();
        RandomSequenceGenerator<String> generator = new RandomSequenceGenerator<>(animals);

//        int count = 0;
//        for (String animal: generator) {
//            System.out.println("animal: " + animal);
//            if (count++ > 100) break;
//        }

        for (int i = 0; i < 100; i++) {
            System.out.println("animal: " + generator.iterator().next());
        }
    }
}

