package graph.dijkstra;

import graph.matrix.DirectedGraphMatrix;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/18
 */
public class Main {

    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertEdge(0, 1, 10);
        graph.insertEdge(0, 2, 18);
        graph.insertEdge(2, 1, 5);
        graph.insertEdge(1, 3, 5);
        graph.insertEdge(3, 2, 2);
        graph.insertEdge(3, 4, 2);
        graph.insertEdge(4, 2, 2);
    }

    void floyd() {
        int n = 5;
        graphInit(n);

        double[][] dist = new double[n][n];
        int[][] path = new int[n][n]; // path[i][j]=k 表示从 i~>j 的路径上到j顶点的上一个顶点位置为k

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph.getWeight(i, j);
                if (i != j && dist[i][j] < graph.getMaxWeight()) path[i][j] = i;
                else path[i][j] = -1; // i~j 没有路径
            }
        }

        for (int k = 0; k < n; k++) { // 将 n 个顶点依次加入中间顶点集合中
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(dist));

        int t;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (path[i][j] != -1) {
                    System.out.print(j + " <- ");
                    t = j;
                    while (path[i][t] != i) {
                        System.out.print(path[i][t] + " <- ");
                        t = path[i][t];
                    }
                    System.out.println(i);
                }
            }
        }
    }

    void dijkstra() {
        int n = 5, source = 0;
        graphInit(n);
        int[] path = new int[n];
        double[] dist = new double[n];
        for (int i = 0; i < n; i++) {
            dist[i] = graph.getWeight(source, i);
        }
        boolean[] inSet = new boolean[n];
        inSet[0] = true;
        path[0] = -1;
        double min = graph.getMaxWeight();
        int minIndex = -1;
        for (int i = 0; i < n - 1; i++) {  // 将剩余n-1个顶点加入集合中

            min = graph.getMaxWeight();
            for (int j = 0; j < n; j++) {
                if (inSet[j] == false && dist[j] < min) {
                    min = dist[j];
                    minIndex = j;
                }
            }
            inSet[minIndex] = true;

            // 更新dist
            for (int j = 0; j < n; j++) {
                if (inSet[j] == false && dist[minIndex] + graph.getWeight(minIndex, j) < dist[j]) {
                    dist[j] = dist[minIndex] + graph.getWeight(minIndex, j);
                    path[j] = minIndex;
                }
            }
        }

        System.out.println(Arrays.toString(dist));

        int t;
        for (int i = 1; i < n; i++) {
            t = i;
            System.out.print(t + "<- ");
            while (path[t] >= 0) {
                System.out.print(path[t] + "<- ");
                t = path[t];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
//        m.dijkstra();
        m.floyd();
    }

}
