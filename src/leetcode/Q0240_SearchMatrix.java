package leetcode;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q0240_SearchMatrix {
    /**
     * 思路: 选左下角或者右上角开始判断，每次判断可以排除一行或者一列
     * 时间复杂度: O(row+col)
     * 空间复杂度:O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null ) return false;//|| matrix.length == 0 || matrix[0].length == 0

        int i = matrix.length - 1, j = 0;

        while (i >= 0 && j < matrix[0].length) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                i--;
            }
        }
        return false;

    }
}
