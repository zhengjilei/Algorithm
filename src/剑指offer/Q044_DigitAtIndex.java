package 剑指offer;

/**
 * 数字序列以 0123456789101112131415... 变成一个字符序列，求字符序列的第 n 位(从0计数)对应的数字
 * 例如 n=9, 对应 9; n=11,对应 0
 * created by Ethan-Walker on 2018/12/11
 */
public class Q044_DigitAtIndex {

    /**
     * 最直接的思路，将从0~ index 的数全部拼成字符串（最后长度肯定大于 index）
     * 然后取索引 index 位置的数字
     * <p>
     * 缺点：
     * 时间复杂度: O(n)
     * 字符串反复拼接，非常消耗资源(Java中还支持拼接，C++不支持字符串拼接，只能分配足够大的数组)
     * index 很大时， 拼接的字符串过长（远大于index）
     * 例 index =100时，从0~100 拼接，字符串长度 1*10+2*90+3 = 193
     *
     * @return
     */
    public static int digitAtIndex(int index) {
        if (index < 0) return -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            sb.append(i);
        }
        return sb.toString().charAt(index) - '0';
    }


    public static int digitAtIndex2(int index) {
        if (index < 0) return -1;
        if (index <= 9) return index;

        int n = index;
        n -= 10; // 减去 0~9 位置的累计数目
        int length = 2; // length =2 ,表示数字长度为2 位数，即 10~99
        int startNum = 10;
        while (true) {
            int numCount = 9 * startNum; // 数字的总数, startNum 既是起始数值，也是该长度数字的总数
            int lengthCount = length * numCount;
            if (n >= lengthCount) {
                n -= lengthCount;
            } else {
                break;
            }
            length++;
            startNum *= 10;
        }
        int i = n / length; // 在长度为 length 的数字中排名第 i 个(从 0 开始计算)
        int pos = n % length; //
        int num = i + startNum; // 找到第 index 所在的数字了

        return String.valueOf(num).charAt(pos) - '0';
    }

    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 1000; i++) {
                if (digitAtIndex(i) != digitAtIndex2(i)) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println(i);
            System.out.println(e.getMessage());
        }

    }
}
