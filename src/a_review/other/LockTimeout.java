package a_review.other;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by Ethan-Walker on 2019/3/3
 */
public class LockTimeout {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.lock();
                System.out.println(Thread.currentThread().getName() + " 获得 lock1");

                for (int i = 0; i < Integer.MAX_VALUE; i++) {

                }
                try {
                    boolean has = lock2.tryLock(10, TimeUnit.SECONDS);
                    // ...
                    if (has) {
                        System.out.println(Thread.currentThread().getName() + " 获得 lock2");
                        lock2.unlock();
                    }else{
                        System.out.println(Thread.currentThread().getName() + " 希望获取lock2，但超时了");

                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + " 希望获取lock2，但被中断了");
                }
                lock1.unlock();
                latch.countDown();

            }
        }, "thread111");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " 获得 lock2");

                for (int i = 0; i < Integer.MAX_VALUE; i++) {

                }


                try {
                    boolean has = lock1.tryLock(20, TimeUnit.SECONDS);

                    if (has) {
                        System.out.println(Thread.currentThread().getName() + " 获得 lock1");
                        lock1.unlock();
                    }else{
                        System.out.println(Thread.currentThread().getName() + " 希望获取lock1，但超时了");

                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 希望获取lock1，但被中断了");
                }
                lock2.unlock();
                latch.countDown();

            }
        }, "thread222");


        t1.start();
        t2.start();

        latch.await();


    }
}
