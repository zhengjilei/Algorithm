package 电路布线;

import java.util.Arrays;

/**
 * Created by EthanWalker on 2017/11/29.
 */
public class MNS {

    public static int[] c = {-1, 8, 7, 4, 2, 5, 1, 9, 3, 10, 6};  // -1 为填充索引 0 , 没有实际的效果
    public static int n = 10;
    public static int[][] s = new int[n + 1][n + 1];

    /**
     * 扫描实现
     * 从索引 1/2/3.. 依次向后 扫描  c 数组中递增的序列长度,
     *
     * @return
     */
    public static int mns() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int count = 1;
            int comp = c[i];
            s[i][count] = c[i];
            // s[1][1]  s[1][2]  s[1][3]  8 9 10
            for (int j = i + 1; j <= n; j++) {
                if (comp < c[j]) {
                    s[i][++count] = c[j];   // 这里的 s[]保存的是每条 递增子序列，例如 4 6 8 9
                    comp = c[j];
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * 动态规划迭代计算
     *
     * @param
     */
    public static int mns2() {
        // s[i][j] 保存的是 1=<k<=i , 且 c[k]<=j 时, 最大不相交子集的个数
        //i=1 的情况下 :  j<c[1] j>c[1]
        for (int j = 1; j < c[1]; j++) {
            s[1][j] = 0;
        }

        for (int j = c[1]; j <= n; j++) {
            s[1][j] = 1;
        }
        // i>1 的情况下 ：  j<c[i]  j>c[i]
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < c[i]; j++) {
                s[i][j] = s[i - 1][j];
            }
            for (int j = c[i]; j <= n; j++) {
                s[i][j] = Math.max(s[i - 1][c[i] - 1] + 1, s[i - 1][j]);
            }

        }
        return s[n][n];
    }

    // 路径追踪 traceback[i] 表示
    private static int[] trackback;

    /**
     *
     */
    public static void traceBack() {
        int count = 0;
        trackback = new int[n + 1];
        int j = n, m = 0;
        for (int i = n; i > 1; i--) {
            if (s[i][j] != s[i - 1][j]) {
                // 说明 i,c[i] 不在取得最多布线情况 的取值中
                trackback[m++] = i;
                j = c[i] - 1;  //
            }
        }
        if (j >= c[1]) trackback[m++] = 1;

    }

    public static void main(String[] args) {
        int mns = mns2();
        System.out.println(mns);
        traceBack();
        for(int i=0;i<mns;i++){
            System.out.println(trackback[i]+" -> "+ c[trackback[i]]);
        }
    }
}
