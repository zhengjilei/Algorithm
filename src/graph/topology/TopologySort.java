package graph.topology;

import graph.matrix.DirectedGraphMatrix;

import java.util.ArrayDeque;

/**
 * AOV网络 拓扑排序
 * created by Ethan-Walker on 2018/11/17
 */
public class TopologySort {

    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertEdge(0, 3, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(1, 5, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(4, 0, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(4, 5, 1);

        graph.insertEdge(3, 4, 1);
    }

    /**
     * 拓扑排序
     * 可以用来判断图是否有循环
     */
    void topologySort() {
        int n = 6;
        graphInit(n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();// 存放入度为 0 的顶点
        boolean flag = true;
        int[] inDegree = new int[n];
        // 计算所有顶点的入度,压入所有入度为0的顶点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph.getWeight(j, i) < graph.getMaxWeight()) {
                    inDegree[i]++;
                }
            }
            if (inDegree[i] == 0) stack.push(i);
        }

        int count = 0, t;
        while (!stack.isEmpty()) {
            t = stack.pop(); // 删除该顶点并且删除与该顶点相关的边
            count++;
            for (int j = 0; j < n; j++) {
                if (t != j && graph.getWeight(t, j) < graph.getMaxWeight()) {
                    inDegree[j]--;
                    if (inDegree[j] == 0) {
                        stack.push(j);
                    }
                }
            }
        }
        if (count < n) {
            System.out.println("AOV存在有向环");
        }
    }

    public static void main(String[] args) {
        TopologySort t = new TopologySort();
        t.topologySort();
    }
}
