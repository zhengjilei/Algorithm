package designpattern.singleton.pattern1;

/**
 * Created by lenovo on 2018/5/11.
 */
public class TestSingle {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(MySingleObj.getInstance());
            }
        };
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
    }
}
