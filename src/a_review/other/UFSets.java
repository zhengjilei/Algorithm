package a_review.other;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class UFSets {

    int[] parent;

    public UFSets(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * 找到所属的父节点
     * 折叠压缩
     *
     * @return
     */
    public int find(int index) {
        int j = index;
        while (parent[j] != j) {
            j = parent[j];
        }
        int t;
        while (index != parent[index]) {
            t = parent[index];
            parent[index] = j;
            index = t;
        }
        return j;
    }

    public int union(int i, int j) {

        int pi = find(i);
        int pj = find(j);

        if (pi == pj) return pi;
        parent[pi] = pj;

        return pj;
    }
}
