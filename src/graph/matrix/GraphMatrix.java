package graph.matrix;

import java.util.ArrayDeque;

/**
 * 无向带权图的邻接矩阵表示
 * created by Ethan-Walker on 2018/11/9
 */
public class GraphMatrix {
    int numVertices; // 当前顶点数
    int maxVertices; //最大顶点数
    int numEdges;   // 当前边数

    String[] verticesList; // 顶点表,存储各顶点的没名字
    double[][] edge; // 邻接矩阵 存的是边对应的权值
    double maxWeight = Double.MAX_VALUE;

    public GraphMatrix(int sz) {
        this.maxVertices = sz;
        numEdges = 0;
        numVertices = 0;

        verticesList = new String[maxVertices];
        edge = new double[maxVertices][maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                if (i != j) {
                    edge[i][j] = Double.MAX_VALUE;
                } else {
                    edge[i][j] = 0.0;
                }
            }
        }
    }

    /**
     * 获取顶点 v 的第一个邻接顶点
     *
     * @param v
     * @return
     */
    public int getFirstNeighbor(int v) {
        if (v >= 0 && v < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (edge[i][v] > 0 && edge[i][v] < maxWeight) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 获取顶点 v 的某邻接顶点 w 的下一个邻接顶点
     *
     * @param v
     * @param w
     * @return
     */
    public int getNextNeighbor(int v, int w) {
        if (v >= 0 && v < numVertices && w >= 0 && w < numVertices) {
            for (int j = w + 1; j < numVertices; j++) {
                if (edge[v][j] > 0 && edge[v][j] < maxWeight) {
                    return j;
                }
            }
        }
        return -1;
    }

    /**
     * 插入顶点
     *
     * @param vertex 顶点值
     * @return
     */
    public boolean insertVertex(String vertex) {
        if (numVertices < maxVertices) {
            verticesList[numVertices++] = vertex;
            return true;
        }
        return false;
    }

    /**
     * 删除顶点
     *
     * @param v 顶点位置
     * @return
     */
    public boolean removeVertex(int v) {
        if (numVertices == 1) {
            return false; // 只有一个顶点，不删除
        }
        if (v >= 0 && v < numVertices) {
            verticesList[v] = verticesList[numVertices - 1]; // 最后一个顶点填补

            for (int i = 0; i < numVertices; i++) {
                if (edge[v][i] > 0 && edge[v][i] < maxWeight) numEdges--;
            }
            // 将最后一行赋值到替换节点的对应行中
            for (int i = 0; i < numVertices; i++) {
                edge[v][i] = edge[numVertices - 1][i];
            }
            // 最后一列复制到替换节点对应的列上
            for (int i = 0; i < numVertices; i++) {
                edge[i][v] = edge[i][numVertices - 1];
            }

            for (int i = 0; i < numVertices; i++) {
                edge[i][numVertices - 1] = edge[numVertices - 1][i] = maxWeight;
            }
            numVertices--;
            return true;
        }

        return false;
    }

    /**
     * 插入边
     *
     * @param v1
     * @param v2
     * @param weight
     * @return
     */
    public boolean insertEdge(int v1, int v2, int weight) {
        if (v1 >= 0 && v1 < numVertices & v2 >= 0 && v2 < numVertices && edge[v1][v2] == maxWeight) {
            edge[v1][v2] = edge[v2][v1] = weight;
            numEdges++;
            return true;
        }
        return false;
    }

    /**
     * 删除边
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean removeEdge(int v1, int v2) {
        if (v1 >= 0 && v1 < numVertices & v2 >= 0 && v2 < numVertices && edge[v1][v2] > 0 && edge[v1][v2] < maxWeight) {
            edge[v1][v2] = edge[v2][v1] = maxWeight;
            numEdges--;
            return true;
        }
        return false;
    }

    /**
     * 递归dfs
     * visited[v] == true 表示第 v 行（或者理解为第 v 个元素）被访问过了
     *
     * @param v
     * @param visited
     */
    public void dfs(int v, boolean[] visited) {
        if (v < 0 || v > numVertices || visited[v] == true) return;
        visited[v] = true;
        System.out.printf("%5s", verticesList[v]);
        for (int i = 0; i < numVertices; i++) {
            if (visited[i] == false && edge[v][i] > 0 && edge[v][i] < maxWeight) {
                dfs(i, visited);
            }
        }
    }

    public void dfs() {
        dfs(0, new boolean[maxVertices]);
        System.out.println();
    }

    class A {
        int vertex; //顶点索引
        int index; // 索引为 vertex的顶点已经探索到的列数

        public A(int a, int b) {
            this.vertex = a;
            this.index = b;
        }
    }

    public void dfsStack() {
        ArrayDeque<A> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[numVertices];
        visited[0] = true;
        stack.push(new A(0, 0));
        System.out.printf("%5s", verticesList[0]);
        while (!stack.isEmpty()) {
            A a = stack.pop();
            int index = a.index + 1;
            int vertex = a.vertex;
            while (index < numVertices) {
                if (visited[index] == false && edge[vertex][index] > 0 && edge[vertex][index] < maxWeight) {
                    stack.push(new A(vertex, index));
                    System.out.printf("%5s", verticesList[index]);
                    visited[index] = true;
                    // 深入
                    vertex = index;
                    index = 0;
                } else {
                    index++;
                }
            }
        }
        System.out.println();
    }

    public void printAdjMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (edge[i][j] == maxWeight) {
                    System.out.printf("%-5s", "F");
                } else {
                    System.out.printf("%-5.0f", edge[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void bfs() {
        int v = 0;
        boolean[] visited = new boolean[numVertices];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        visited[v] = true;
        System.out.printf("%5s", verticesList[v]);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int i = 0; i < numVertices; i++) {
                if (edge[index][i] > 0 && edge[index][i] < maxWeight && visited[i] == false) {
                    System.out.printf("%5s", verticesList[i]);
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix(10);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertVertex("H");
        graph.insertVertex("I");

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 3, 6);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(1, 4, 2);
        graph.insertEdge(1, 2, 3);
        graph.insertEdge(4, 6, 4);
        graph.insertEdge(3, 5, 7);
        graph.insertEdge(2, 5, 8);
        graph.insertEdge(5, 7, 9);
        graph.insertEdge(7, 8, 10);

        graph.dfs();
//        graph.bfs();
        graph.printAdjMatrix();
        graph.dfsStack();
    }

}
