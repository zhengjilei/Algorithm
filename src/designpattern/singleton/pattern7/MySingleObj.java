package designpattern.singleton.pattern7;

/**
 * Created by Ethan-Walker on 2018/5/11.
 * 静态代码块实现 单例模式
 * 6 中将枚举类暴露，违反职责单一原则
 */
public class MySingleObj {
    private static enum MySingleEnum {
        MY_SINGLE_OBJ;
        private MySingleObj singleton;

        MySingleEnum() {
            singleton = new MySingleObj();
        }

        private MySingleObj getSingleInstance() {
            return singleton;
        }
    }

    public static MySingleObj getSingleInstance() {
        return MySingleEnum.MY_SINGLE_OBJ.getSingleInstance();
    }

}
