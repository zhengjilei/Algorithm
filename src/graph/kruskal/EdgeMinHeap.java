package graph.kruskal;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/14
 */
public class EdgeMinHeap {
    Edge[] edges;
    int numEdges;
    int maxEdges;

    public EdgeMinHeap(int sz) {
        edges = new Edge[sz];
        this.numEdges = 0;
        this.maxEdges = sz;
    }

    public boolean insert(Edge e) {
        if (numEdges == maxEdges) {
            maxEdges = 2 * maxEdges;
            edges = Arrays.copyOf(edges, maxEdges);
        }
        edges[numEdges] = e;
        siftUp(numEdges);
        numEdges++;
        return true;
    }

    public Edge removeMin() {
        if (numEdges == 0) return null;
        Edge e = edges[0];
        swap(0, numEdges - 1);
        numEdges--;
        siftDown(0);
        return e;
    }

    private void siftDown(int i) {
        int j = 2 * i + 1;
        while (j < numEdges) {
            if (j < numEdges - 1 && edges[j + 1].cost < edges[j].cost) j++;
            if (edges[i].cost <= edges[j].cost) break;
            else {
                swap(i, j);
                i = j;
                j = 2 * i + 1;
            }
        }
    }

    private void siftUp(int index) {
        int j = index, i = (j - 1) / 2;
        while (i >= 0) {
            if (edges[i].cost <= edges[j].cost) break;
            else {
                swap(i, j);
                j = i;
                i = (j - 1) / 2;
            }
        }
    }

    private void swap(int i, int j) {
        Edge e = edges[i];
        edges[i] = edges[j];
        edges[j] = e;
    }


    public boolean isEmpty() {
        return numEdges == 0;
    }

}
