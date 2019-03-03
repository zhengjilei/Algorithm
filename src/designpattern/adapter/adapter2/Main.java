package designpattern.adapter.adapter2;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Main {

    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.adapteeMethod();
    }
}
