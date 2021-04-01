package 剑指offer;

import org.junit.Test;

/**
 * 求一个整数的二进制中 1 的个数（负数用补码表示）
 * created by Ethan-Walker on 2018/12/4
 */
public class Q015_CountOneInBinary {

    /**
     * 错误：负数用补码来统计其中1的个数，不是用原码
     * <p>
     * 将该十进制整数 通过除2法得到二进制各位数字
     * 要考虑正负数的不同情况
     * <p>
     * 效率低（直接乘除效率低，移位运算效率高）
     *
     * @param n
     * @return
     */
    public int countOne(int n) {
        int count = 0;
        if (n < 0) {
            n = -n;
            count++; // 符号位为 1
        }

        int t;
        while (n != 0) {
            if (n % 2 == 1) count++;
            n /= 2;
        }
        return count;
    }

    /**
     * 依次获取二进制数各位
     *
     * @return
     */
    public int countOneByLeft(int n) {

        int flag = 1, count = 0;
        while (flag != 0) {
            if ((n & flag) != 0) {  // 获取第 xx 位
                // flag
                count++;
            }
            flag <<= 1; // 左移
        }
        return count;
    }

    /**
     * n-1 将n的最后一位 1 变为0，并将原先该1位置后的0变成1
     * n&(n-1) 去掉最后一位1 （将最后一位1 置为0）
     * 有多少个 1 就执行多少次
     *
     * @param n
     * @return
     */
    public int countOneByDelete1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n; // 去掉 n 的最后一位 1
        }
        return count;
    }

    @Test
    public void test() {
//      System.out.println(9 & 8);
//      System.out.println(countOneByLeft(9));
        System.out.println(countOne(9) + "," + countOneByLeft(9));
        System.out.println(Integer.toBinaryString(-9));
        System.out.println(countOne(-9) + "," + countOneByLeft(-9));
        System.out.println(countOne(121) + "," + countOneByLeft(121));
        System.out.println(Integer.toBinaryString(-321));
        System.out.println(countOne(-321) + "," + countOneByLeft(-321));
        System.out.println(6 & 2);

        int n = 0x80000000;
        int m = n;
        System.out.println(Integer.toBinaryString(n - 1));
    }
}
