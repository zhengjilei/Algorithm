package 剑指offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 扑克牌中的顺子
 * created by Ethan-Walker on 2018/12/15
 */
public class Q061_SeqInCard {

    /**
     * 判断 str 是否是顺子
     *
     * @param str
     * @return
     */
    boolean isSeq(String str) {
        int length = str.length();
        if (str == null || str.length() != length) return false;

        char[] chs = str.toCharArray();
        int[] help = new int[length];
        for (int i = 0; i < length; i++) {
            int value = getRealValue(chs[i]);
            if (value == -1) {
                return false;
            } else if (value != 0) {
                help[i] = value;
            }
        }
        Arrays.sort(help);

        int countOfZero = 0, i;
        for (i = 0; i < length && help[i] == 0; i++) {
            countOfZero++;
        }
        int small = i, big = i + 1;

        while (big < length) {
            if (help[big] == help[small]) return false;

            countOfZero -= (help[big] - help[small] - 1); // 间隔数 抵消 大王小王(0值)

            small = big;
            big = big + 1;
        }
        if (countOfZero >= 0) {
            return true;
        }
        return false;
    }


    // A=1, J=11, I=10, Q=12,K=13 大王W 小王 w
    public int getRealValue(char c) {
        if (c >= '2' && c <= '9') {
            return c - '0';
        }
        switch (c) {
            case 'A':
                return 1;
            case 'I':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'W':
            case 'w':
                return 0;
            default:
                return -1;
        }
    }

    //[3,0,0,0,0]
    public boolean isSeq(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        Arrays.sort(numbers);

        int countOfZero = 0, i = 0;
        for (; i < numbers.length && numbers[i] == 0; i++) {
            countOfZero++;
        }

        int small = i, big = i + 1;
        while (big < numbers.length) {
            if (numbers[small] == numbers[big]) return false;

            countOfZero -= (numbers[big] - numbers[small] - 1);
            small = big;
            big = big + 1;
        }
        if (countOfZero >= 0) { // 0 较多
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(isSeq("391wW21"));
        System.out.println(isSeq("362W4"));
    }

}
