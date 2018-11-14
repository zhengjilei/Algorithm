package designpattern.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by lenovo on 2018/4/25.
 */
public class Demo {
    public static void main(String[] args) {
        Drink drink = new Coffee();
        System.out.println(drink.getConsist() + "==>" + drink.getPrice());

        drink = new MilkCoffee(drink);
        System.out.println(drink.getConsist() + "==>" + drink.getPrice());

        drink = new SugarCoffee(drink);
        System.out.println(drink.getConsist() + "==>" + drink.getPrice());

        drink = new SugarCoffee(drink);
        System.out.println(drink.getConsist() + "==>" + drink.getPrice());


    }
}
