package 大整数乘法;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/19.
 */
public class BigIntMulti {

    public static void main(String[] args) {
        // 输入相同位数的 整数
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            long a = scanner.nextLong();
            long b = scanner.nextLong();

            long multi = multi(a, b);
            System.out.println(multi);
        }
    }

    public static long multi(long a, long b){
        int count = String.valueOf(a).length();
        long div =(long)Math.pow(10,count/2);

        long aLeft = Long.valueOf(a)/div;
        long aRight = Long.valueOf(a)%div;

        long bLeft = Long.valueOf(b)/div;
        long bRight = Long.valueOf(b)%div;

        return aLeft*bLeft *div*div +div*((aLeft-aRight)*(bRight-bLeft) + aLeft*bLeft+aRight*bRight)+ aRight*bRight;
    }
}
