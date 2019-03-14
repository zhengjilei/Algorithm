package a_review.other;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/11
 */
public class OOM {
    public static void main(String[] args) {
        List<Integer[]> objList = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            objList.add(new Integer[]{211, 222, 333, 444, 555, 8888, 9999, 7777, 1111, 553366});
        }
    }
}
