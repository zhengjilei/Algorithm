package graph.kruskal;

/**
 * created by Ethan-Walker on 2018/11/14
 */
public class UFSets {

    int[] parent;
    int count;

    public UFSets(int sz) {
        this.count = sz;
        parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = -1;
        }
    }

    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        else {
            if (parent[aRoot] < parent[bRoot]) {
                parent[aRoot] += parent[bRoot];
                parent[bRoot] = aRoot;
            } else {
                parent[bRoot] += parent[aRoot];
                parent[aRoot] = bRoot;
            }
        }
    }

    /**
     * 折叠查找
     *
     * @param a
     * @return
     */
    public int find(int a) {
        int b = a, t;
        while (parent[a] > 0) a = parent[a];
        while (parent[b] > 0) {
            t = parent[b];
            parent[b] = a;
            b = t;
        }
        return a;
    }
}
