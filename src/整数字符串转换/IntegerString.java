package 整数字符串转换;

public class IntegerString {

    /**
     * 整数转换成字符串:
     *
     * @param n
     * @return
     */
    public String itoa1(int n) {
        return "";
    }

    /**
     * 假设整数是合法、符合日常书写的整数
     * 例如不会出现：0100,+321,1e10,-0021
     * 整数字符串转换成整数
     * <p>
     * 如果数字溢出，返回 0
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        if (str == null || str.trim().length() == 0) return 0;
        boolean positive = true;
        if (str.charAt(0) == '-') positive = false;
        int minq = Integer.MIN_VALUE / 10; //-214748364
        int minr = Integer.MIN_VALUE % 10; //-8
        int sum = 0, cur = 0;
        // 将原数中的不含符号部分（即正数部分），求其相反数（负数）
        for (int j = positive ? 0 : 1; j < str.length(); j++) {
            cur = '0' - str.charAt(j); // 负数
            if (sum < minq || (sum == minq && cur < minr)) {
                // sum<minq , 当前数再乘以10就溢出了
                // sum==minq, 如果cur<minr(即-8)， minq*10+cur 肯定溢出了（不管原数是正数还是负数）
                return 0;
            }
            sum = sum * 10 + cur;
        }
        // 原数是正数，但是正数部分求反之后恰好等于最小值，在上面处理过程中只会保留该非法数，剔除
        // 2147483648
        if (positive && sum == Integer.MIN_VALUE) {
            return 0;
        }
        return positive ? -sum : sum;
    }

}
