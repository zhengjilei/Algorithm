package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/28
 */
public class Q229_MajorityNumII {
    public List<Integer> majorityElement(int[] array) {
        List<Integer> res = new ArrayList<>();
        int count1 = 0, count2 =0;
        int num1 = array[0], num2 = array[0];
        for (int i = 0; i < array.length; i++) {
            if (num1 == array[i]) {
                count1++;
            } else if (num2 == array[i]) {
                count2++;
            } else if (count1 == 0) {
                num1 = array[i];
                count1 = 1;
            } else if (count2 == 0) {
                num2 = array[i];
                count2 = 0;
            } else {
                count1--;
                count2--;
            }
        }

        if (checkMoreThanTreePer(array, num1)) {
            res.add(num1);
        }
        if (num2 != num1 && checkMoreThanTreePer(array, num2)) {
            res.add(num2);
        }
        return res;

    }

    public boolean checkMoreThanTreePer(int[] array, int key) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                count++;
            }
        }
        return count > array.length / 3;
    }
}
