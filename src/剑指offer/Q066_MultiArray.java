package 剑指offer;

/**
 * 构建乘积数组 b
 * 使得 b[i] = a[0]*a[1]*..*a[i-1]*a[i+1]...*a[n-1]
 * 要求：不能使用除法
 * created by Ethan-Walker on 2018/12/16
 */
public class Q066_MultiArray {

    /**
     * 最简单的做法
     * 时间复杂度: O(n^2)
     *
     * @param a
     * @return
     */
    public int[] buildMultiArray(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            int m = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    m *= a[j];
                }
            }
            b[i] = m;
        }
        return b;
    }


    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * @param a
     * @return
     */
    public int[] buildMultiArray2(int[] a) {
        int n = a.length;
        int[] c = new int[n]; // c[i] = a[0]*a[1]*...*a[i-1]
        int[] d = new int[n]; // d[i] = a[i+1]*a[i+2]*...*a[n-1]

        int[] b = new int[n];  //b[i] = c[i]*d[i]

        //c[i] = c[i-1]*a[i-1]
        c[0] = 1;
        for (int i = 1; i < n; i++) {
            c[i] = c[i - 1] * a[i - 1];
        }

        //d[i] = d[i+1]*a[i+1]
        d[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            d[i] = d[i + 1] * a[i + 1];
        }

        for (int i = 0; i < n; i++) {
            b[i] = c[i] * d[i];
        }
        return b;
    }
}
