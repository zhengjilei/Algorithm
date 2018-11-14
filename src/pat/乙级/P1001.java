package pat.乙级;

import java.util.Scanner;

/**
 * Created by lenovo on 2018/4/23.
 */
public class P1001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int count = 0;
        while (i != 1) {
            if (i % 2 == 1) {
                i = (3 * i + 1) >> 1;
            } else {
                i = i >> 1;
            }
            count++;
        }
        System.out.println(count);
    }
}
