package designpattern.adapter.adapter2;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Adaptee implements AdapteeInterface {
    @Override
    public void method() {
        System.out.println("执行了 method ");
    }
}
