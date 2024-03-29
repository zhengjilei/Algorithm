//import edu.princeton.cs.algs4.StdDraw;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/6/23.
 */
public class MyTest {

    @Test
    public void a() {

    }

    @Test
    public void b() {
        Random random = new Random();
        double v = random.nextDouble() * 10;
        System.out.println(v);
        System.out.printf("%5.2f\n", v);
        System.out.printf("%-5.2f\n", v);
        System.out.printf("%10.2e\n", v);
        System.out.println("-----------------");


        int n = random.nextInt(1000);
        System.out.println(n);
        System.out.printf("%5d\n", n);
        System.out.printf("%-5d\n", n);


        System.out.println("-----------------");
        String s = "hello, world";
        System.out.println(s);
        System.out.printf("%14s\n", s);
        System.out.printf("%-14s\n", s);
        System.out.printf("%14.5s\n", s);// 保留前5位，左边填充空白 直到14位
        System.out.printf("%-14.5s\n", s);// 保留前5位，右边填充空白 直到14位

    }

    @Test
    public void c() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(-2147483648));
        System.out.println(1 / 0.0);
        System.out.println(1 / 0);
    }

    @Test
    public void d() {
        System.out.println(-14 / 3);
        System.out.println(14 / -3);
        System.out.println(-14 % 3);
        System.out.println(14 % -3);
    }


    @Test
    public void e() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }
//        System.out.println(i);

    }

    public static void main(String[] args) {
//        StdDraw.circle(0.5, 0.5, 0.2);
        a1(b1());
    }

    public static int a1(int num){
        return num+1;
    }
    public static int b1(){
        return 3;
    }




}
