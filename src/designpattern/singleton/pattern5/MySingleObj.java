package designpattern.singleton.pattern5;

/**
 * Created by Ethan-Walker on 2018/5/11.
 * 静态代码块实现 单例模式，类似于饿汉模式
 */
public class MySingleObj{
    private static MySingleObj obj =null;
    static{
        obj = new MySingleObj();
    }
    private MySingleObj(){}

    public static MySingleObj getInstance() {
        return obj;
    }
}
