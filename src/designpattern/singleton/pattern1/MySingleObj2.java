package designpattern.singleton.pattern1;

/**
 * 懒汉模式
 * Created by Ethan-Walker on 2018/5/11.
 */
public class MySingleObj2 {
    private static MySingleObj2 mySingleObj2;

    private MySingleObj2() {
    }

    // 单线程使用，多线程不安全，方法上加 synchronized ，效率太低，代码块中加 synchronized ，如何加?
    public static MySingleObj2 getInstance() {
        try {
            if (mySingleObj2 == null) {
                Thread.sleep(100); // 模拟初始化类的时间
                mySingleObj2 = new MySingleObj2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySingleObj2;
    }


    // 在创建对象前加上同步synchronized
    public static MySingleObj2 getInstance2() {
        try {
            if (mySingleObj2 == null) {
                Thread.sleep(100);
                // 仍然会出现线程不安全的问题
                synchronized (MySingleObj2.class) {
                    mySingleObj2 = new MySingleObj2();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySingleObj2;
    }

    // 最终解决方案3: DCL双重检查锁机制
    public static MySingleObj2 getInstance3() {
        try {
            if (mySingleObj2 == null) {
                Thread.sleep(1000);
                synchronized (MySingleObj2.class) {
                    if (mySingleObj2 == null) {
                        // 检查两次，获取最新值，防止之前锁中的线程已经对 obj 做出了改变
                        mySingleObj2 = new MySingleObj2();
                    }
                }
            }
        } catch (InterruptedException e) {

        }
        return mySingleObj2;
    }
}
