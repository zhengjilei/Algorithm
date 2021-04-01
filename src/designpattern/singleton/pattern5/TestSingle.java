package designpattern.singleton.pattern5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ethan-Walker on 2018/5/11.
 */
public class TestSingle {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(MySingleObj.getInstance());
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.execute(runnable);
        }
    }
}
