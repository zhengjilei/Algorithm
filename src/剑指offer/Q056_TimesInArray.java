package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * created by Ethan-Walker on 2018/12/14
 */
public class Q056_TimesInArray {

    /**
     * 整数数组中每个元素都出现了两次，有且仅有一个元素只出现了一次，找出这个元素
     * 异或: a^ b ^b ^c ^c ^d ^d= a ^0 ^0 = a
     *
     * @param array
     * @return
     */
    public int getOneInteger(int[] array) {
        int r = 0;
        for (int i = 0; i < array.length; i++) {
            r ^= array[i];
        }
        return r;
    }

    /**
     * 整型数组中每个元素都出现了两次，有且仅有两个元素各自只出现了一次，找出这两个元素
     * 要求：时间复杂度 O(n) 空间复杂度:O(1)
     *
     * @param array
     * @return
     */
    public ArrayList<Integer> getTwoInteger(int[] array) {
        int r = 0;
        for (int i = 0; i < array.length; i++) {
            r ^= array[i];
        }

        // 找到 r 的第xx位为1, 设a、b为数组中仅出现一次的两个数，由于 r=a^b 故a、b对应的第 xx 位不同
        int k = 1;
        while ((r & k) != k) {
            k <<= 1;
        }

        // 按照第 xx 位是否为 1 分为两组
        int r1 = 0;
        int r2 = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & k) == k) {
                r1 ^= array[i];
            } else {
                r2 ^= array[i];
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);
        return list;
    }

    /**
     * 数组中所有元素都出现了三次，只有一个元素只出现了一次
     * <p>
     * 计算各个元素的对应二进制位之和，若对应二进制位能被3整除，则说明目标元素在该位为 0，否则说明目标元素在该位为1
     *
     * @param array
     * @return
     */
    public int getOneTimeInArray(int[] array) {
        int sum = 0, bitMask = 0;
        int[] bitSum = new int[32];      // bitSum[i]=k 表示数组中所有元素  从左往右 第 i 位之和为 k
        for (int i = 0; i < array.length; i++) {
            bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                bitSum[j] += (array[i] & bitMask);
                bitMask <<= 1;
            }
        }
        int result = 0;
        bitMask = 1;
        for (int i = 31; i >= 0; i--) {
            if (bitSum[i] % 3 != 0) { // 说明result 在该位为 1
                result |= bitMask;
            }
            bitMask <<= 1;
        }
        return result;
    }

    @Test
    public void test() {
        int[] a = new int[]{10, 2, 2, 10, 4, 5, 10, 4, 5, 2, 5, 4, -1999};
        System.out.println(getOneTimeInArray(a));
    }
}
