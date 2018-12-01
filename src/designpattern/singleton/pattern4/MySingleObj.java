package designpattern.singleton.pattern4;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/5/11.
 * 静态内部类实现单例模式
 */
public class MySingleObj implements Serializable{

    private static final long serialVersionUID = 7130249135107591053L;

    private MySingleObj(){}
    private static class MySingleObjHandler{
        private static MySingleObj mySingleObj = new MySingleObj();
    }

    public static MySingleObj getInstance() {
        return MySingleObjHandler.mySingleObj;
    }

    protected Object readResolve(){
        System.out.println("调用了readResolve()方法");
        return MySingleObjHandler.mySingleObj;
    }
}
