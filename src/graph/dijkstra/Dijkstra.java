package graph.dijkstra;

import graph.matrix.DirectedGraphMatrix;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 有向图、非负权值的单源最短路径
 * created by Ethan-Walker on 2018/11/15
 */
public class Dijkstra {
    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdge(0, 1, 10);
        graph.insertEdge(0, 4, 100);
        graph.insertEdge(0, 3, 30);
        graph.insertEdge(1, 2, 50);
        graph.insertEdge(2, 4, 10);
        graph.insertEdge(3, 2, 20);
        graph.insertEdge(3, 4, 60);
    }

    int[] path;
    double[] dist;

    // 时间复杂度: O(n^2)
    void dijkstra() {
        int n = 5;
        graphInit(n);

        path = new int[n];                // path[i] = k 表示到顶点i的最短路径的 上一个顶点 path[i] = -1 表示直接从顶点
        dist = new double[n];             // dist[i] 表示从源点0 到顶点i的最短路径长度
        boolean[] inSet = new boolean[n];  // inSet[i]=true 表示顶点i加入集合中

        int source = 0;
        for (int i = 0; i < n; i++) {
            dist[i] = graph.getWeight(0, i);
            if (i != source && dist[i] < graph.getMaxWeight()) path[i] = source;
            else path[i] = -1;
        }
        double maxWeight = graph.getMaxWeight();
        double min;
        int minIndex = 0;
        inSet[0] = true;
        for (int i = 0; i < n - 1; i++) {
            // 选不在集合中 并且 dist[v] 最小的顶点 v 加入集合
            min = maxWeight;

            for (int j = 0; j < n; j++) {
                if (inSet[j] == false && dist[j] < min) {
                    min = dist[j];
                    minIndex = j;
                }
            }
            inSet[minIndex] = true;//顶点 minIndex 加入集合
            //更新 dist 表
            for (int j = 0; j < n; j++) {
                if (inSet[j] == false
                        && graph.getWeight(minIndex, j) < graph.getMaxWeight()   // minIndex-> j 不是无穷大
                        && dist[minIndex] + graph.getWeight(minIndex, j) < dist[j]) {
                    dist[j] = dist[minIndex] + graph.getWeight(minIndex, j);
                    path[j] = minIndex;
                }
            }
        }

        System.out.println(Arrays.toString(dist));

        //输出各最短路径
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int j;
        for (int i = 1; i < n; i++) {
            System.out.printf("path" + i + ": ");

            stack.push(i);
            j = i;
            while (path[j] > 0) {
                stack.push(path[j]);
                j = path[j];
            }
            stack.push(0);

            while (!stack.isEmpty()) {
                System.out.printf("%3d", stack.pop());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        d.dijkstra();
    }
}
