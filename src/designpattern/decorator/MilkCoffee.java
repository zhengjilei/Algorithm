package designpattern.decorator;

/**
 * Created by lenovo on 2018/4/25.
 */
public class MilkCoffee extends Decorator {

    public MilkCoffee(Drink drink) {
        super(drink);
    }

    @Override
    public String getConsist() {
        String consist = super.getConsist();
        consist += " milk";
        return consist;

    }

    @Override
    public double getPrice() {
        double price = super.getPrice();
        price += 2.1;
        return price;
    }
}
