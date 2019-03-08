package 程序员代码面试指南2;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class Q057 {
    public int minCount(int[] value, int aim) {

        int[][] dp = new int[value.length][aim + 1];
        for (int j = value[0]; j <= aim; j += value[0]) {
            dp[0][j] = j / value[0];
        }
        for (int i = 1; i < value.length; i++) {
        }
        return 0;
    }


    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        for(int i=0;i<1000;i++){
            objects.add(new Object());

        }

        objects= null;

        System.gc();


    }

}
