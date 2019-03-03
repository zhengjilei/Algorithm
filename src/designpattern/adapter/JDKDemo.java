package designpattern.adapter;

import java.io.*;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class JDKDemo {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("a.txt"))));

        AA a = new AA();
        System.out.println(a.a);

    }


}
class AA {
    int a = 10;

    public AA() {
        a = 20;
    }

}