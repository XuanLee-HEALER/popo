package resume.coding.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

    private final Sync sync = new Sync();
    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return compareAndSetState(1, 0);
        }
    }

    public void acquire() {
        sync.acquire(0);
    }

    public void release() {
        sync.release(0);
    }

    public static void main(String[] args) throws InterruptedException {
        int[] test = new int[]{0};
        Mutex mutex = new Mutex();

        runThread(test, mutex);

        System.out.printf("result: %d\n", test[0]);
    }

    static void runThread(int[] test, Mutex mutex) {
        for (int i = 0; i < 1; i++) {
            Thread t1 = new Thread(new Op(test, mutex));
            Thread t2 = new Thread(new Op(test, mutex));
            Thread t3 = new Thread(new Op(test, mutex));
            t1.start();
            t2.start();
            t3.start();

            test[0] = 0;
        }
    }

    static class Op implements Runnable {
        private int[] data;
        private Mutex mutex;

        public Op(int[] data, Mutex mutex) {
            this.data = data;
            this.mutex = mutex;
        }

        @Override
        public void run() {
//            mutex.acquire();
            data[0]++;
//            mutex.release();
        }
    }
}
