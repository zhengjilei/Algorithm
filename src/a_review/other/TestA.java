package a_review.other;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class TestA {
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            LockSupport.parkUntil(System.currentTimeMillis() + 100000);
//            System.out.println("哈哈哈哈");
//        }, "t1");
//
//        t1.start();
//
//        t1.interrupt();
//
//        Thread.sleep(1000);

        method();
    }

    static void method() {
        long a = 32;
        method();
    }


    @Test
    public void test2(){
        Class clss = Object.class;

    }

}
