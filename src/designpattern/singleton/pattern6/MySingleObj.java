package designpattern.singleton.pattern6;

/**
 * Created by Ethan-Walker on 2018/5/11.
 * 枚举实现单例模式
 */
public enum MySingleObj {
    MY_SINGLE_OBJ;

    private Object object;
    private MySingleObj(){
        object = new Object();
    }

    public  Object getInstance() {
        return object;
    }

}
