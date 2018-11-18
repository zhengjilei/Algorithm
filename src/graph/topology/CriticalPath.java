package graph.topology;

import graph.matrix.DirectedGraphMatrix;

/**
 * AOE网络
 * 关键路径
 * created by Ethan-Walker on 2018/11/18
 */
public class CriticalPath {

    DirectedGraphMatrix graph;

    void graphInit(int n) {
        graph = new DirectedGraphMatrix(n);
        graph.insertVertex("0");
        graph.insertVertex("1");
        graph.insertVertex("2");
        graph.insertVertex("3");
        graph.insertVertex("4");
        graph.insertVertex("5");
        graph.insertVertex("6");
        graph.insertVertex("7");
        graph.insertVertex("8");

        graph.insertEdge(0, 1, 6);
        graph.insertEdge(0, 2, 4);
        graph.insertEdge(0, 3, 5);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(2, 4, 1);
        graph.insertEdge(3, 5, 2);
        graph.insertEdge(4, 6, 9);
        graph.insertEdge(4, 7, 7);
        graph.insertEdge(5, 7, 4);
        graph.insertEdge(6, 8, 2);
        graph.insertEdge(7, 8, 4);

    }

    void criticalPath() {
        int n = 9;
        graphInit(n);
        double[] ve = new double[n]; // ve[i]=a 表示顶点i 表示的事件最早开始的时间为 a
        for (int i = 0; i < n; i++) {
            // 根据顶点 i 指向其他顶点的边 ，计算其他顶点的ve
            for (int j = 0; j < n; j++) {
                if (i != j && graph.getWeight(i, j) < graph.getMaxWeight()) {
                    if (ve[i] + graph.getWeight(i, j) > ve[j]) {
                        // ve[j] = max(ve[i]+<i,j>)
                        ve[j] = ve[i] + graph.getWeight(i, j);
                    }
                }
            }
        }
        double[] vl = new double[n]; // vl[i]=a 表示顶点i表示的事件最迟开始的时间
        for (int i = 0; i < n; i++) {
            vl[i] = graph.getMaxWeight();
        }
        vl[n - 1] = ve[n - 1];// 终点的 vl == ve
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph.getWeight(i, j) < graph.getMaxWeight()) {
                    if (vl[j] - graph.getWeight(i, j) < vl[i]) {
                        vl[i] = vl[j] - graph.getWeight(i, j);
                    }
                }
            }
        }
        double ae = 0;
        double al = 0;
        int[] path = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            // i 个顶点
            for (int j = 0; j < n; j++) {
                if (i != j && graph.getWeight(i, j) < graph.getMaxWeight()) {
                    ae = ve[i];
                    al = vl[j] - graph.getWeight(i, j);
                    if (ae == al) {
                        // 边 <i,j> 是关键路径
                        System.out.println("<" + i + "," + j + ">");
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        CriticalPath cp = new CriticalPath();
        cp.criticalPath();
    }
}
