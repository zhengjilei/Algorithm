package a_review.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Prim {

    public int prim(int[][] matrix) {
        int vertexCount = matrix.length;
        boolean[] inSet = new boolean[vertexCount];
        inSet[0] = true;
        List<Edge> edges = new ArrayList<>();
        int minLen = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        int p = 0;
        int cnt = 1;
        while (cnt < vertexCount) {        // 还需要加入 n - 1 个顶点

            // 顶点 p 所有的邻接边
            for (int j = 0; j < vertexCount; j++) {
                if (!inSet[j] && j != p && matrix[p][j] < Integer.MAX_VALUE) {
                    minHeap.add(new Edge(p, j, matrix[p][j])); // 注意: p/j 位置不能颠倒
                }
            }
            while (!minHeap.isEmpty()) {
                Edge e = minHeap.poll();
                if (!inSet[e.v2]) {
                    minLen += e.cost;
                    edges.add(e);
                    inSet[e.v2] = true;
                    p = e.v2; // 访问新加入节点
                    cnt++;
                    break;
                }
            }

        }
        return minLen;
    }
}
