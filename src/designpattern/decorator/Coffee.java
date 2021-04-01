package designpattern.decorator;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class Coffee implements Drink {
    @Override
    public double getPrice() {
        return 10;
    }

    @Override
    public String getConsist() {
        return "coffee ";
    }
}
