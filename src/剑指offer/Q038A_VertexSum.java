package 剑指offer;

/**
 * 将八个数值分别放在正方形的各个顶点上
 * 使得正方体上三组相对的面上的四个顶点的和都相等
 * <p>
 * a1+a2+a3+a4 = a5+a6+a7+a8
 * a1+a3+a5+a7 = a2+a4+a6+a8
 * a1+a2+a5+a6 = a3+a4+a7+a8
 * <p>
 * created by Ethan-Walker on 2018/12/8
 */
public class Q038A_VertexSum {


    /**
     * 求全排列，最好不用递归，递归会因为重复元素，导致多组重复计算多次
     *
     * @return
     */
    public boolean perm(int[] a, int start, int end) {
        if (start == end) {
            return judge(a);
        }
        boolean tag = false;
        for (int i = 0; i <= a.length - 1; i++) {
            swap(a, i, start);
            tag = perm(a, start + 1, end); // 递归对 start+1,end 进行全排列
            if (tag) return tag;
            swap(a, i, start);
        }
        return tag;
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public boolean judge(int[] a) {
        if (a[0] + a[1] + a[2] + a[3] != a[4] + a[5] + a[6] + a[7])
            return false;
        if (a[1] + a[3] + a[5] + a[7] != a[0] + a[2] + a[4] + a[6])
            return false;
        if (a[0] + a[1] + a[4] + a[5] != a[2] + a[3] + a[6] + a[7])
            return false;
        return true;
    }

}
