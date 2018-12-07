package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/3/30.
 */
public class TestA {

    static int[] a;

    public static void swap(int l, int r) {
        a[l] = a[l] + a[r];
        a[r] = a[l] - a[r];
        a[l] = a[l] - a[r];
    }

    public static void sort() {
        if (a[1] < a[0]) swap(1, 0);
        if (a[2] < a[1]) swap(1, 2);
        if (a[1] < a[0]) swap(1, 0);
    }

    public static void main(String[] args) {
        Random random = new Random();
        a = new int[3];
        a[0] = random.nextInt(100);
        a[1] = random.nextInt(100);
        a[2] = random.nextInt(100);
        System.out.println(Arrays.toString(a));
        sort();
        System.out.println(Arrays.toString(a));
    }
}
