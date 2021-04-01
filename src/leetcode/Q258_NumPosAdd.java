package leetcode;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q258_NumPosAdd {
    public int addDigits(int num) {
        int res = num;
        while ((res = add(res)) >= 10) ;
        return res;
    }

    public int add(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
