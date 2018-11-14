package designpattern.decorator;

/**
 * Created by lenovo on 2018/4/25.
 */
public abstract class Decorator implements Drink {
    protected Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double getPrice() {
        return drink.getPrice();
    }

    @Override
    public String getConsist() {
        return drink.getConsist();
    }
}
