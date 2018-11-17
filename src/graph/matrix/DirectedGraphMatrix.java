package graph.matrix;

/**
 * created by Ethan-Walker on 2018/11/15
 */
public class DirectedGraphMatrix {
    private int numVertices; // 当前顶点数
    private int maxVertices; //最大顶点数
    private int numEdges;   // 当前边数

    private String[] verticesList; // 顶点表,存储各顶点的名字
    private double[][] edge; // 邻接矩阵 存的是边对应的权值
    private double maxWeight = Double.MAX_VALUE;

    public DirectedGraphMatrix(int sz) {
        this.maxVertices = sz;
        this.numEdges = 0;
        this.numVertices = 0;
        edge = new double[maxVertices][maxVertices];
        verticesList = new String[maxVertices];
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                if (i != j) {
                    edge[i][j] = maxWeight;
                } else {
                    edge[i][j] = 0.0;
                }
            }
        }
    }

    public boolean insertVertex(String vertexName) {
        if (numVertices < maxVertices) {
            verticesList[numVertices++] = vertexName;
            return true;
        }
        return false;
    }

    public boolean removeVertex(int v) {
        if (numVertices <= 1 || v < 0 || v >= numVertices) return false;
        verticesList[v] = verticesList[numVertices - 1];
        for (int i = 0; i < numVertices; i++) {
            edge[v][i] = edge[numVertices - 1][i];
        }
        for (int i = 0; i < numVertices; i++) {
            edge[i][v] = edge[i][numVertices - 1];
        }
        for (int i = 0; i < numVertices; i++) {
            edge[i][numVertices - 1] = edge[numVertices - 1][i] = maxWeight;
        }
        numVertices--;
        return true;
    }

    public boolean insertEdge(int a, int b, double cost) {
        if (a < 0 || a >= numVertices || b < 0 || b >= numVertices || edge[a][b] < maxWeight) {
            return false;
        }
        edge[a][b] = cost;
        numEdges++;
        return true;
    }

    public boolean removeEdge(int a, int b) {
        if (a < 0 || a >= numVertices || b < 0 || b >= numVertices || edge[a][b] == maxWeight || edge[a][b] == 0) {
            return false;
        }
        edge[a][b] = maxWeight;
        numEdges--;
        return true;
    }

    public double getWeight(int a, int b) {
        return edge[a][b];
    }

    public double getMaxWeight() {
        return maxWeight;
    }

}
