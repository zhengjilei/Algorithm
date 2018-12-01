package designpattern.singleton.pattern3;

/**
 * 静态内置类创建单例模式
 * Created by lenovo on 2018/5/12.
 */
public class MySingleObj {
    private static class MySingletonHandler{
        private static MySingleObj mySingleObj = new MySingleObj();
    }
    public static MySingleObj getInstance(){
        return MySingletonHandler.mySingleObj;
    }

    private MySingleObj(){}
}