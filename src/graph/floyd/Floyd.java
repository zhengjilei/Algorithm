package graph.floyd;

import graph.matrix.DirectedGraphMatrix;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/17
 */
public class Floyd {
    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 3, 4);
        graph.insertEdge(1, 2, 9);
        graph.insertEdge(1, 3, 2);
        graph.insertEdge(2, 0, 3);
        graph.insertEdge(2, 1, 5);
        graph.insertEdge(2, 3, 8);
        graph.insertEdge(3, 2, 6);

    }

    void floyd() {
        int n = 4;
        graphInit(n);
        double[][] cost = new double[n][n];
        int[][] path = new int[n][n]; // path[i][j]=k 表示从i->j 的最短路径中 到顶点j的上一个顶点位置

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = graph.getWeight(i, j);
                if (i != j && cost[i][j] < graph.getMaxWeight()) path[i][j] = i;
                else path[i][j] = -1;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cost[i][k] + cost[k][j] < cost[i][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(cost));

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int t;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && cost[i][j] < graph.getMaxWeight()) {
                    System.out.printf(i + "->" + j + ": ");
                    stack.push(j);
                    t = j;
                    while (path[i][t] != i) {
                        stack.push(path[i][t]);
                        t = path[i][t];
                    }
                    stack.push(i);
                    while (!stack.isEmpty()) {
                        System.out.print(stack.pop() + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        Floyd f = new Floyd();
        f.floyd();
    }

}
