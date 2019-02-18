package leetcode;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q074_SearchMatrix {
    /**
     * 每一行执行二分查找
     * 时间复杂度: O(row*log(col))
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null ) return false;//|| matrix.length == 0 || matrix[0].length == 0
        for (int i = 0;i<matrix.length;i++){
            if(binarySearch(matrix,i,target)) return true;
        }
        return false;
    }

    public boolean binarySearch(int[][] matrix, int row, int target) {
        int start = 0, end = matrix[row].length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) >> 1;
            if (matrix[row][mid] == target) return true;
            else if (matrix[row][mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

}
