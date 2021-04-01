package graph.kruskal;

import graph.matrix.GraphMatrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/14
 */
public class Kruskal {
    GraphMatrix graph;
    EdgeMinHeap minHeap;

    void graphInit(int n) {
        graph = new GraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");

        graph.insertEdge(0, 1, 28);
        graph.insertEdge(0, 5, 10);
        graph.insertEdge(1, 2, 16);
        graph.insertEdge(1, 6, 14);
        graph.insertEdge(2, 3, 12);
        graph.insertEdge(3, 4, 22);
        graph.insertEdge(3, 6, 18);
        graph.insertEdge(4, 5, 25);
        graph.insertEdge(4, 6, 24);
    }


    ArrayList<Edge> kruskal() {
        int n = 7;
        graphInit(n);
        // 构造堆
        minHeap = new EdgeMinHeap(n * (n - 1) / 2);
        Edge e;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph.getWeight(i, j) < graph.getMaxWeight()) {
                    e = new Edge(i, j, graph.getWeight(i, j));
                    minHeap.insert(e);
                }
            }
        }
        UFSets ufSets = new UFSets(n);
        ArrayList<Edge> result = new ArrayList<>();
        int count = 1, a, b;
        while (count < n) {
            // 最小生成树一共 n-1 条边
            e = minHeap.removeMin();
            if (e != null) {
                a = ufSets.find(e.vertex1);
                b = ufSets.find(e.vertex2);
                if (a != b) {
                    // 说明 e.vertex1 e.vertex2 不在一个集合中，不会形成环
                    ufSets.union(a, b);
                    result.add(e);
                    count++;
                }
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Kruskal k = new Kruskal();
        ArrayList<Edge> result = k.kruskal();
        System.out.println(Arrays.toString(result.toArray()));
    }

}
