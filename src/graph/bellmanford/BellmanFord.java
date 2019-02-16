package graph.bellmanford;

import graph.matrix.DirectedGraphMatrix;
import org.junit.Test;

import java.util.Arrays;

/**
 * Bellman-Ford 算法
 * 单源任意权值最短路径（可以处理权值是负的问题）
 * created by Ethan-Walker on 2019/2/13
 */
public class BellmanFord {

    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");

        graph.insertEdge(0, 1, 6);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(0, 3, 5);
        graph.insertEdge(1, 4, -1);
        graph.insertEdge(2, 1, -2);
        graph.insertEdge(2, 4, 1);
        graph.insertEdge(3, 2, -2);
        graph.insertEdge(3, 5, -1);
        graph.insertEdge(4, 6, 3);
        graph.insertEdge(5, 6, 3);
    }

    int[] path;
    double[] dist;

    // 时间复杂度: O(n^3)
    void bellmanFord() {
        int n = 7;
        graphInit(n);
        path = new int[n];
        dist = new double[n];
        int v = 0;
        for (int i = 0; i < n; i++) {
            dist[i] = graph.getWeight(v, i);
            if (i != v && graph.getWeight(v, i) < graph.getMaxWeight())
                path[i] = v;
            else
                path[i] = -1;
        }
        double min;
        double weight;
        // dist(k) [u] (k>=1 && k<=n-1 )表示从源点v 到达终点 u 最多经过 k 条边的最短路径长度
        // dist(1) [u] 已经初始化了
        // dist(k)[u] = min{ dist(k-1)[u] , min{dist(k-1)[j]+edge[j][u]} (j>=0,j<n) }
        for (int k = 2; k < n; k++) {
            for (int u = 0; u < n; u++) {
                if (u != v) { // dist[v] 始终为 0 (v->v)
                    // 更新 dist(k)[u]
                    for (int j = 0; j < n; j++) {
                        weight = graph.getWeight(j, u);
                        if (weight < graph.getMaxWeight() && dist[j] + weight < dist[u]) {
                            dist[u] = dist[j] + weight;
                            path[u] = j;
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }


    @Test
    public void test() {
        BellmanFord bellmanFord = new BellmanFord();
        bellmanFord.bellmanFord();
    }
}
