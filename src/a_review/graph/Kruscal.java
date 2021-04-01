package a_review.graph;

import a_review.other.UFSets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Kruscal {

    /**
     * 无向图
     * matrix[i][j] >= 0
     * matrix[i][j] = Integer.Max_VALUE  表示顶点 i-> j
     *
     * @param matrix
     * @return
     */
    public int min(int[][] matrix) {

        int vertexCount = matrix.length;
        UFSets ufSets = new UFSets(vertexCount);
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    minHeap.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        List<Edge> res = new ArrayList<>();
        int minLen = 0;
        // 选 n-1 条边
        while (vertexCount > 1) {
            Edge e = minHeap.poll();
            int a = ufSets.find(e.v1);
            int b = ufSets.find(e.v2);
            if (a != b) {
                res.add(e);
                minLen += e.cost;
                ufSets.union(a, b);
                vertexCount--;
            }
        }
        return minLen;
    }


}
