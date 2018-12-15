package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 输入一个整数 n, 求所有连续的整数序列，使得序列之和为 n
 * <p>
 * created by Ethan-Walker on 2018/12/14
 */
public class Q057A_FindContinuousSeq {


    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (sum < 3) return list; // 序列至少包含1/2 两个数
        int small = 1, big = 2;

        int cSum = 3;
        while (small < big) {
            if (cSum == sum) {
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    tempList.add(i);
                }
                list.add(tempList);

                big++;
                cSum += big;

            } else if (cSum > sum) {
                cSum -= small;
                small++;
            } else {
                big++;
                cSum += big;
            }
        }
        return list;
    }


    @Test
    public void test() {
        System.out.println(findContinuousSequence(9));
    }
}
