import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Ethan-Walker on 2018/7/28.
 */
public class MyTest2 {
    public static void main(String[] args) {

        int[] a = new int[]{1,2,3,4};
        a = Arrays.copyOf(a,10);
        System.out.println(Arrays.toString(a));
        System.out.println(a.length);
    }
}




