package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/7
 */
public class Q067_Type {


    public int num(String express, boolean desired) {
        if (!isValid(express)) return 0;
        return process(express.toCharArray(), desired, 0, express.length() - 1);
    }

    /**
     * 求 chs[l..r] 凑出值等于desired 的种数
     * 暴力递归
     * 时间复杂度: O(n!)
     * 空间复杂度: O(n)
     *
     * @param chs
     * @param desired
     * @param l
     * @param r
     * @return
     */
    public int process(char[] chs, boolean desired, int l, int r) {
        int t = 0;
        if (l == r) {
            if (chs[l] == '1') {
                return desired ? 1 : 0;
            } else {
                return !desired ? 1 : 0;
            }
        }
        int result = 0;
        if (desired) {
            for (int i = l + 1; i <= r - 1; i += 2) { // i 指向奇数位置（字符位置）
                switch (chs[i]) {
                    case '^':
                        // true^false;
                        result += process(chs, true, l, i - 1) * process(chs, false, i + 1, r);
                        // false^true
                        result += process(chs, false, l, i - 1) * process(chs, true, i + 1, r);
                        break;
                    case '|':
                        // true|true
                        result += process(chs, true, l, i - 1) * process(chs, true, i + 1, r);
                        // true|false
                        result += process(chs, true, l, i - 1) * process(chs, false, i + 1, r);
                        // false|true
                        result += process(chs, false, l, i - 1) * process(chs, true, i + 1, r);
                        break;
                    case '&':
                        // true&true
                        result += process(chs, true, l, i - 1) * process(chs, true, i + 1, r);
                        break;
                }
            }
        } else {
            for (int i = l + 1; i <= r - 1; i += 2) { // i 指向奇数位置（字符位置）
                switch (chs[i]) {
                    case '^':
                        // true^true;
                        result += process(chs, true, l, i - 1) * process(chs, true, i + 1, r);
                        // false^false
                        result += process(chs, false, l, i - 1) * process(chs, false, i + 1, r);
                        break;
                    case '|':
                        // false|false
                        result += process(chs, false, l, i - 1) * process(chs, false, i + 1, r);
                        break;
                    case '&':
                        result += process(chs, true, l, i - 1) * process(chs, false, i + 1, r);
                        result += process(chs, false, l, i - 1) * process(chs, true, i + 1, r);
                        result += process(chs, false, l, i - 1) * process(chs, false, i + 1, r);
                        break;
                }
            }
        }
        return result;


    }

    public boolean isValid(String express) {
        if (express == null || (express.length() & 1) == 0)
            return false;   // 表达式长度为偶数
        for (int i = 0; i < express.length(); i++) {
            if ((i & 1) == 0) { // 偶数位必须是 1/0
                if (!(express.charAt(i) == '0' || express.charAt(i) == '1'))
                    return false;
            } else {
                // 奇数位
                if (!(express.charAt(i) == '&' || express.charAt(i) == '|' || express.charAt(i) == '^')) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 为了不重复计算，将已经计算的结果存到数组中
     * t[a][b] 表示 express[a..b] 凑成 true 的种数
     * f[a][b] ......              false
     * <p>
     * t[0][2] = t[0][0] * t[2][2]
     * 先计算单个字符的 t ，然后3个字符的，5个字符的...
     * <p>
     * t[0][0] [2][2] [4][4] ...[len-1][len-1]
     * 计算t[0][2]
     * (0,0) (2,2)
     * <p>
     * 计算t[0][4]
     * (0,2){上一步骤算出} / (4,4)
     * (0,0) / (2,4){依赖(2,2),(4,4)}
     * <p>
     * 计算t[0][6]
     * (0,4){上一步骤算出} / (6,6)
     * (0,2) / (4,6) {依赖(4,4)/(6,6)}
     * (0,0) / (2,6){依赖(2,2)/(4,6) (2,4)(4,6)}
     * <p>
     * 计算t[0][8]
     * (0,6)/(8,8)
     * (0,4)/(6,8){依赖(6,6)/(8,8)}
     * (0,2)/(4,8){依赖(4,4)/(6,8) (4,6){依赖(4,4)/(6,6)}  /(8,8)}
     * (0,0)/(2,8){依赖(2,2)/(4,8) (2,4)/(6,8) (2,6)/(8,8)}
     * <p>
     * 以计算t[0][8] 示例: 先计算 t[6][8] 再计算 t[4][8] 、t[2][8] 、t[0][8]
     * 总结，先计算小间隔，再计算大间隔
     *
     * @param express
     * @param desired
     * @return
     */
    public int num2(String express, boolean desired) {
        if (!isValid(express)) return 0;
        int len = express.length();
        char[] chs = express.toCharArray();
        int[][] t = new int[len][len];
        int[][] f = new int[len][len];
        t[0][0] = chs[0] == '1' ? 1 : 0;
        f[0][0] = chs[0] == '1' ? 0 : 1;


        for (int i = 2; i < len; i += 2) { // 计算 t[0][i] 的值
            //计算长度为 true[2][2] [4][4]..
            t[i][i] = chs[i] == '1' ? 1 : 0;
            f[i][i] = chs[i] == '0' ? 1 : 0;
            // 首先计算 t[i-2][i] ，t[i-4][i].. 最后计算 t[0][i]（先计算间隔小的，再计算间隔大的）
            for (int j = i - 2; j >= 0; j--) {
                // 计算 t[j][i] j=i-2 i-4...
                for (int k = j + 1; k < i; k += 2) {
                    // [j][i] 分成 [j][k-1] , [k+1][i] 两部分
                    switch (chs[k]) {
                        case '|':
                            t[j][i] += (t[j][k - 1] * t[k + 1][i] + t[j][k - 1] * f[k + 1][i] + f[j][k - 1] * t[k + 1][i]);
                            f[j][i] += (f[j][k - 1] * f[k + 1][i]);
                            break;
                        case '^':
                            t[j][i] += (t[j][k - 1] * f[k + 1][i] + f[j][k - 1] * t[k + 1][i]);
                            f[j][i] += (f[j][k - 1] * f[k + 1][i] + t[j][k - 1] * t[k + 1][i]);
                            break;
                        case '&':
                            t[j][i] += (t[j][k - 1] * t[k + 1][i]);
                            f[j][i] += (f[j][k - 1] * f[k + 1][i] + t[j][k - 1] * f[k + 1][i] + f[j][k - 1] * t[k + 1][i]);
                            break;
                    }
                }
            }
        }
        return desired ? t[0][len - 1] : f[0][len - 1];
    }

    /**
     * 先计算所有的小间隔，再计算所有的大间隔
     * <p>
     * 先计算所有1个数字字符的 t[i][i]
     * 再计算所有2个数字字符的 t[i][i+2]
     * ...
     * <p>
     * 例如计算最终结果 t[0][8]
     * 1. (0,0) (2,2) (4,4) (6,6) (8,8)
     * 2. (0,2) (2,4) (4,6) (6,8)
     * 3. (0,4) (2,6) (4,8)
     * 4. (0,6) (6,8)
     * 5. (0,8)
     * <p>
     * <p>
     * 时间复杂度: O(n^3)
     * 空间复杂度: O(n^2)
     *
     * @param express
     * @param desired
     * @return
     */
    public int num3(String express, boolean desired) {
        if (!isValid(express)) return 1;

        int len = express.length();
        int[][] t = new int[len][len];
        int[][] f = new int[len][len];
        char[] chs = express.toCharArray();

        // 计算所有的 gap=0
        for (int j = 0; j < len; j += 2) {
            t[j][j] = chs[j] == '1' ? 1 : 0;
            f[j][j] = chs[j] == '0' ? 1 : 0;
        }
        for (int gap = 2; gap < len; gap += 2) {

            for (int i = 0; i + gap < len; i += 2) { // i 为起点
                // 计算 t[i][i+gap]

                // k 指向符号位
                for (int k = i + 1; k < i + gap; k += 2) {
                    // 分成左 [i][k-1] [k+1][i+gap] 两部分
                    switch (chs[k]) {
                        case '&':
                            t[i][i + gap] += (t[i][k - 1] * t[k + 1][i + gap]);
                            f[i][i + gap] += (t[i][k - 1] * f[k + 1][i + gap] + f[i][k - 1] * t[k + 1][i + gap] + f[i][k - 1] * f[k + 1][i + gap]);
                            break;
                        case '|':
                            t[i][i + gap] += (t[i][k - 1] * f[k + 1][i + gap] + f[i][k - 1] * t[k + 1][i + gap] + t[i][k - 1] * t[k + 1][i + gap]);
                            f[i][i + gap] += (f[i][k - 1] * f[k + 1][i + gap]);
                            break;
                        case '^':
                            t[i][i + gap] += (t[i][k - 1] * f[k + 1][i + gap] + f[i][k - 1] * t[k + 1][i + gap]);
                            f[i][i + gap] += (t[i][k - 1] * t[k + 1][i + gap] + f[i][k - 1] * f[k + 1][i + gap]);
                            break;
                    }
                }
            }
        }
        return desired ? t[0][len - 1] : f[0][len - 1];

    }

    @Test
    public void test() {
        String express = "1^0|0|1&0|1^1&0|1^1&0&0|1";
        System.out.println(num(express, true));
        System.out.println(num(express, false));
        System.out.println(num2(express, true));
        System.out.println(num2(express, false));
        System.out.println(num3(express, true));
        System.out.println(num3(express, false));
    }

}
