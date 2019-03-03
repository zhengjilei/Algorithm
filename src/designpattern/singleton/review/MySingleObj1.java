package designpattern.singleton.review;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class MySingleObj1 {
    private static MySingleObj1 mySingleObj1;

    private MySingleObj1() {
    }

    public static MySingleObj1 getInstance() {
        if (mySingleObj1 == null) {
            mySingleObj1 = new MySingleObj1();
        }
        return mySingleObj1;
    }
}
