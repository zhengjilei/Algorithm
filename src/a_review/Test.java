package a_review;

import java.util.concurrent.locks.LockSupport;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.parkUntil(System.currentTimeMillis() + 100000);
            System.out.println("哈哈哈哈");
        }, "t1");

        t1.start();

        t1.interrupt();

        Thread.sleep(1000);
    }
}
