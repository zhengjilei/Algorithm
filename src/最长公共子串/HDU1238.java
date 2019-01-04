package 最长公共子串;

import java.util.Scanner;

/**
 * 最长的公共子串，反串的子串也算
 * <p>
 * created by Ethan-Walker on 2019/1/4
 */
public class HDU1238 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(), n;
        String[] strs;
        while (t > 0) {
            n = sc.nextInt();
            strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = sc.next();
            }
//            System.out.println(lcst(strs));
            t--;
        }
    }

}
