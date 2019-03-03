package designpattern.adapter.adapter2;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Adapter implements Target {

    AdapteeInterface adaptee;

    public Adapter(AdapteeInterface adpatee) {
        this.adaptee = adpatee;
    }

    @Override
    public void method1() {

    }

    @Override
    public void adapteeMethod() {
        adaptee.method();
    }
}
