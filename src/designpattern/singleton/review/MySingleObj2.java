package designpattern.singleton.review;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class MySingleObj2 {
    private static volatile MySingleObj2 mySingleObj2;

    private MySingleObj2() {
    }

    public static MySingleObj2 getInstance() {

        if (mySingleObj2 == null) {
            synchronized (MySingleObj2.class) {
                if (mySingleObj2 == null) {
                    mySingleObj2 = new MySingleObj2();
                }
            }
        }
        return mySingleObj2;
    }
}
