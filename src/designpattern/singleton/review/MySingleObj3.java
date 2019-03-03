package designpattern.singleton.review;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class MySingleObj3 {

    private MySingleObj3() {
    }

    private static class MySingleHandler {
        private static MySingleObj3 mySingleObj3 = new MySingleObj3();
    }

    public static MySingleObj3 getInstance() {
        return MySingleHandler.mySingleObj3;
    }

    public Object readResolve() {
        return MySingleHandler.mySingleObj3;
    }
}
