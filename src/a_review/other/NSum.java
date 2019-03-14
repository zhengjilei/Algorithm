package a_review.other;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class NSum {

    public List<List<Integer>> nSum(int[] arr, int sum, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] save = new int[n];
        nSum(res, arr, 0, save, 0, sum, n);
        System.out.println(res);
        return res;
    }

    public void nSum(List<List<Integer>> res, int[] arr, int index, int[] save, int cnt, int target, int n) {
        if (cnt == n) {
            if (target == 0) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < cnt; i++) {
                    list.add(save[i]);
                }
                res.add(list);
            }
            return;
        }
        if (index == arr.length) { // || target <= 0  target <= 0 不代表不能得到最终结果，
            return;
        }

        save[cnt] = arr[index];
        nSum(res, arr, index + 1, save, cnt + 1, target - arr[index], n);
        nSum(res, arr, index + 1, save, cnt, target, n);
    }

    public static void main(String[] args) {
        NSum nSum = new NSum();

        int[] arr = new int[]{-7, 2, 4, 5, 3, 7, 8, 9, 1, 10, 11};
        nSum.nSum(arr, 13, 3); // 2 10 1  ; 4 6 3 ;7 4 2
    }
}
