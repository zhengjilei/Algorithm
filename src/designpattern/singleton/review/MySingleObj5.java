package designpattern.singleton.review;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class MySingleObj5 {

    private enum MySingleton {
        MY_SINGLETON;
        private MySingleObj5 mySingleObj;

        MySingleton() {
            mySingleObj = new MySingleObj5();
        }

    }

    public static MySingleObj5 getInstance() {
        return MySingleton.MY_SINGLETON.mySingleObj;
    }

    private MySingleObj5() {
    }
}
