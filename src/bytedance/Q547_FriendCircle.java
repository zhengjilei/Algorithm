package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q547_FriendCircle {
    public int findCircleNum(int[][] M) {
        return ufset(M);
    }

    // 思路一: 并查集
    public int ufset(int[][] m) {
        int[] parent = new int[m.length];  // parent[i]=k 表示 节点i的父节点为 k , parent[i] = i 时表明其单独为一个集合

        for (int i = 0; i < m.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i][j] == 1) {
                    // i/j 是朋友关系
                    union(i, j, parent);
                }
            }
        }

        int resultCount = 0;

        for (int i = 0; i < m.length; i++) {
            if (parent[i] == i) {       // 说明i为集合i的根节点，即一个新集合
                resultCount++;
            }
        }
        return resultCount;

    }

    // 索引位置为 a/b 的两个元素 合并到一个集合中
    public void union(int a, int b, int[] parent) {
        int aRoot = findRoot(a, parent);
        int bRoot = findRoot(b, parent);
        // 让 aRoot 挂到 bRoot 下
        parent[aRoot] = bRoot;
    }

    // 找出索引 a 所在集合（返回根节点索引）,折叠压缩
    public int findRoot(int a, int[] parent) {
        int t = a;
        while (parent[t] != t) {
            t = parent[t];
        }
        int root = t;
        while (parent[a] != root) {
            t = parent[a];
            parent[a] = root;
            a = t;
        }
        return root;
    }

    @Test
    public void test() {
        int[][] m = new int[][]{{1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};
        System.out.println(findCircleNum(m));


        int[][] n = new int[][]{{1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}};

        System.out.println(findCircleNum(n));

    }
}
