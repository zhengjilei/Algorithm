package a_review.corejava;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/21
 */
public class TTpye<T> {
    T[] a;

    public TTpye() {
        a = (T[]) new Object[10];
    }

    @Test
    public void test() {
        int a = 0b0101_0101;
        System.out.println(a);
    }
}
