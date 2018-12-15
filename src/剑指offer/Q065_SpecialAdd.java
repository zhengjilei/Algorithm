package 剑指offer;

/**
 * 不用加减乘除求和
 * created by Ethan-Walker on 2018/12/16
 */
public class Q065_SpecialAdd {

    int add(int a, int b) {

        int sum=0, carry; // carry 表示进位
        do {
            sum = a ^ b; // 不考虑进位的加
            carry = (a & b) << 1; // 只考虑进位的结果

            a = sum;
            b = carry;
            // 循环将 非进位结果和进位结果相加
        } while (carry != 0); // 直到没有进位
        return sum;
    }
}
