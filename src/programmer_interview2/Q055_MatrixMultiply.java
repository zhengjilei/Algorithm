package programmer_interview2;

/**
 * created by Ethan-Walker on 2019/3/5
 */
public class Q055_MatrixMultiply {

    // 求整数base 的 n 次方
    public int power(int base, int expo) {
        if (base == 0 && expo == 0) return 0;
        if (expo == 0) return 1;

        int temp = base;
        int result = 1;
        for (; expo != 0; expo >>= 1) {
            if ((expo & 1) != 0) { // 二进制对应位是 1
                result *= temp;
            }
            temp *= temp;
        }
        return result;
    }

    // 求方阵 matrix 的 n 次方
    public int[][] power(int[][] matrix, int n) {
        int[][] result = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 1;
            }
        }
        int[][] tmp = matrix;
        for (; n != 0; n >>= 1) {
            if ((n & 1) == 0) {
                result = multiply(result, tmp);
            }
            tmp = multiply(tmp, tmp);
        }

        return result;
    }


    public int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                // 求 result[i][j]
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
