package graph.prim;

import graph.kruskal.Edge;
import graph.kruskal.EdgeMinHeap;
import graph.matrix.GraphMatrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/14
 */
public class Prim {

    EdgeMinHeap minHeap;
    GraphMatrix graph;

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

    ArrayList<Edge> prim() {
        int n = 7;
        graphInit(n);
        minHeap = new EdgeMinHeap(n * (n - 1) / 2);
        ArrayList<Edge> edges = new ArrayList<>();

        boolean[] inSet = new boolean[n];
        int count = 1;
        inSet[0] = true; // 顶点0加入集合
        int p = 0;
        Edge e;
        while (count < n) {
            // 一共还需要加入 n-1 个节点
            for (int j = 0; j < n; j++) {
                if (inSet[j] == false && graph.getWeight(p, j) != 0 && graph.getWeight(p, j) < graph.getMaxWeight()) {
                    minHeap.insert(new Edge(p, j, graph.getWeight(p, j)));
                }
            }
            while (!minHeap.isEmpty()) {
                e = minHeap.removeMin();
                if (inSet[e.vertex2] == false) {
                    edges.add(e);
                    inSet[e.vertex2] = true;
                    p = e.vertex2; // 访问新加入节点
                    count++;
                    break;
                }
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        Prim p = new Prim();
        ArrayList<Edge> edges = p.prim();
        System.out.println(Arrays.toString(edges.toArray()));
    }
    
}
