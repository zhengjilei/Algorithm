package designpattern.singleton.review;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class MySingleObj0 {
    private static MySingleObj0 mySingleObj0 = new MySingleObj0();

    public static MySingleObj0 getInstance() {
        return mySingleObj0;
    }
}
