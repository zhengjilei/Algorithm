package designpattern.singleton.pattern7;

/**
 * Created by Ethan-Walker on 2018/5/11.
 * 静态代码块实现 单例模式
 * 6 中将枚举类暴露，违反职责单一原则
 */
public class MySingleObj {
    public enum MyEnumSingleton {
        MY_SINGLE_OBJ;
        private Object object;
        private MyEnumSingleton() {
            object = new Object();
        }
        public Object getInstance() {
            return object;
        }
    }
    public static Object getInstance() {
        return MyEnumSingleton.MY_SINGLE_OBJ.getInstance();
    }

}
