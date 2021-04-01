package designpattern.singleton.pattern1;

/**
 * 饿汉模式：适用于单线程、多线程
 * Created by Ethan-Walker on 2018/5/11.
 */
public class MySingleObj {
    // static 变量在类加载的过程中初始化，类加载时线程安全
    private static MySingleObj mySingleObj = new MySingleObj();

    public static MySingleObj getInstance() {
        return mySingleObj;
    }
    private MySingleObj(){}

}
