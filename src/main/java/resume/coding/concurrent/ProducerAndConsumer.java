package resume.coding.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {

    private static int MAX = 10;
    private LinkedList<Integer> products = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    private void produce() throws InterruptedException {
        if (products.size() == MAX) {
            producer.await();
            return;
        }
        Thread.sleep((long) (100*Math.random()));
        products.addFirst((int) Math.floor(Math.random()*MAX));
        consumer.signalAll();
    }

    private void consume() throws InterruptedException {
        if (!products.isEmpty()) {
            System.out.printf("consume %d, current size: %d\n", products.removeLast(), products.size());
            producer.signalAll();
            return;
        }
        consumer.await();
    }

    public static void main(String[] args) {
        ProducerAndConsumer model = new ProducerAndConsumer();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        model.lock.lock();
                        model.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        model.lock.unlock();
                    }
                }
            });
            t.start();
        }

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    model.lock.lock();
                    model.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    model.lock.unlock();
                }
            }
        });
        t.start();
    }
}
