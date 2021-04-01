package 剑指offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一个正整数数组，将数组中所有数字拼接成一个数，打印出拼接出来的最小数
 * created by Ethan-Walker on 2018/12/11
 */
public class Q045_MinNumByPositiveArray {

    public String min(int[] a) {

        Integer[] aa =new Integer[a.length];
        for(int i=0;i<a.length;i++){
            aa[i]=a[i];
        }
        Arrays.sort(aa, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String s1 = new StringBuilder(String.valueOf(a)).append(b).toString();
                String s2 = new StringBuilder(String.valueOf(b)).append(a).toString();
                return s1.compareTo(s2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(aa[i]);
        }
        return sb.toString();
    }

    @Test
    public void main() {
        int[] a = new int[]{53, 534, 5, 5346};
        System.out.println(min(a));
    }
}
