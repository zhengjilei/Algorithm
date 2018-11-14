package 并查集;

/**
 * 当不需要统计每个集合中的数量时，设 parent[i] = i
 * created by Ethan-Walker on 2018/11/8
 */
public class UFSets2 {
    private int[] parent;
    int[] size;


    public void union(int a, int b) {
        int set1 = find(a);
        int set2 = find(b);
        if (set1 != set2) {
            parent[set1] = set2;
        }
    }

    public int collapsingFind(int a) {
        int root, x = a, p;
        while (parent[a] != a) a = parent[a];
        root = a;

        while (parent[x] != x) {
            p = parent[x];
            parent[x] = root;
            x = p;
        }
        return root;
    }

    public int find(int a) {
        while (parent[a] != a) a = parent[a];
        return a;
    }
}
