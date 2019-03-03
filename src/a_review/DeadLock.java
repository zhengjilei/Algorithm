package a_review;

import java.util.concurrent.CountDownLatch;

/**
 * created by Ethan-Walker on 2019/3/3
 */
public class DeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {

                    }
                    synchronized (lock2) {
                        latch.countDown();
                    }
                }
            }
        }, "thread111");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    for (int i = 0; i <  Integer.MAX_VALUE; i++) {

                    }
                    synchronized (lock1) {
                        latch.countDown();
                    }
                }
            }
        }, "thread222");

        t1.start();
        t2.start();
        latch.await();

    }
}
