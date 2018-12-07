package designpattern.decorator;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class SugarCoffee extends Decorator{
    public SugarCoffee(Drink drink){
        super(drink);
    }

    @Override
    public double getPrice() {
        double price = super.getPrice();
        price+=0.5;
        return price;
    }

    @Override
    public String getConsist() {
        String consist = super.getConsist();
        consist+=" sugar";
        return consist;
    }
}
