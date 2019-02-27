package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q400_NthDigit {

    public int findNthDigit(int n) {
        if (n <= 9) return n;
        n -= 10;            // 注意：这里是 - 10, 第 k 位从 1 开始计算
        long numLen = 2;
        long numCount = 90;
        long count;
        while (true) {
            count = numLen * numCount;
            if (n >= count) {
                n -= count;
                numLen++;
                numCount *= 10;
            } else {
                break;
            }
        }

        // 剩余 n 位
        long startNum = numCount / 9;
        long num = startNum + n / numLen; // 所在的数字值
        int pos = (int) (n % numLen); // 在数字的第 pos 位
        return String.valueOf(num).charAt(pos) - '0';
    }


    @Test
    public void test() {
        System.out.println(findNthDigit(2147483647));
    }
}
