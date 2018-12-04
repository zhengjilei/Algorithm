package 剑指offer;

import org.junit.Test;

/**
 * 将长度为 n 的绳子剪成若干段(>=2)，使得各段长度的乘积最大
 * created by Ethan-Walker on 2018/12/4
 */
public class Q014_Cutting {

    public int cutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        // >=4 时，当剩余长度为 1 2 3 时可以不用剪
        int[] maxLength = new int[length + 1];

        maxLength[0] = 0;
        maxLength[1] = 1;
        maxLength[2] = 2;
        maxLength[3] = 3;
        int max, temp;
        for (int i = 4; i <= length; i++) {
            max = i;
            for (int j = 1; j <= i / 2; j++) {
                temp = maxLength[i - j] * maxLength[j];
                if (temp > max) {
                    max = temp;
                }
            }
            maxLength[i] = max;
        }
        return maxLength[length];
    }

    /**
     * >= 5 剪尽可能多的 3
     *
     * @param length
     * @return
     */
    public int greedy(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        if (length == 4)
            return 4;

        int maxLength = 1;
        int k = length;
        while (k >= 5) {
            maxLength = maxLength * 3;
            k -= 3;
        }
        if (k > 0) maxLength *= k; // 1 2 3 4
        return maxLength;
    }

    @Test
    public void testCutting() {
        System.out.println(cutting(0) + "," + greedy(0));
        System.out.println(cutting(1) + "," + greedy(1));
        System.out.println(cutting(2) + "," + greedy(2));
        System.out.println(cutting(3) + "," + greedy(3));
        System.out.println(cutting(4) + "," + greedy(4));
        System.out.println(cutting(5) + "," + greedy(5));
        System.out.println(cutting(6) + "," + greedy(6));
        System.out.println(cutting(7) + "," + greedy(7));
        System.out.println(cutting(8) + "," + greedy(8));
        System.out.println(cutting(9) + "," + greedy(9));
        System.out.println(cutting(10) + "," + greedy(10));
        System.out.println(cutting(11) + "," + greedy(11));
        System.out.println(cutting(12) + "," + greedy(12));
    }
}
