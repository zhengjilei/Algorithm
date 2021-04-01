package graph.adjacency_list;

import java.util.ArrayDeque;

/**
 * 无向图的邻接表表示
 * created by Ethan-Walker on 2018/11/10
 */
public class GraphAdjacencyList {
    private int maxVertices;
    private int numVertices;
    private int numEdges;

    private Vertex[] vertices;

    class Edge {
        int destVertex; // 该边对应的另一个顶点位置
        double weight; // 权重
        Edge link;    // 链接该顶点的下一条边

        public Edge(int destVertex, double weight) {
            this.destVertex = destVertex;
            this.weight = weight;
        }
    }

    class Vertex {
        String name;
        Edge adj; // 邻接表， 指向该顶点的第一条边，通过该边可以找到该顶点所有的边

        Vertex() {
        }

        public Vertex(String a) {
            this.name = a;
        }
    }

    public GraphAdjacencyList(int sz) {
        this.maxVertices = sz;
        this.numEdges = 0;
        this.numVertices = 0;
        vertices = new Vertex[maxVertices];
    }

    /**
     * 求位置为 v 的顶点的第一个邻居节点的位置
     *
     * @param v
     * @return
     */
    public int getFirstNeighbor(int v) {
        Vertex vertex = vertices[v];
        if (vertex.adj != null) {
            return vertex.adj.destVertex;
        }
        return -1;
    }

    /**
     * 求位置为 v 的顶点的 邻居顶点neighbor 的下一个邻居顶点
     *
     * @param v
     * @param neighbor
     * @return
     */
    public int getNextNeighbor(int v, int neighbor) {
        Edge edge = vertices[v].adj;
        while (edge != null) {
            if (edge.destVertex == neighbor && edge.link != null) {
                return edge.link.destVertex;
            }
        }
        return -1;
    }

    /**
     * 获取边 v1 v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public double getWeight(int v1, int v2) {
        if (v1 >= 0 && v1 < numVertices && v2 >= 0 && v2 < numVertices) {
            Edge edge = vertices[v1].adj;
            while (edge != null) {
                if (edge.destVertex == v2) {
                    return edge.weight;
                }
                edge = edge.link;
            }
        }
        return -1;
    }

    /**
     * 插入顶点
     *
     * @param vertexName
     * @return
     */
    public boolean insertVertex(String vertexName) {
        if (maxVertices == numVertices) return false;
        vertices[numVertices++] = new Vertex(vertexName);
        return true;
    }

    /**
     * 删除顶点
     *
     * @param v
     * @return
     */
    public boolean deleteVertex(int v) {
        if (v < 0 || v >= numVertices || numVertices == 1) {
            return false;
        }
        // 删除顶点v关联的每一条边
        Edge edge = vertices[v].adj, p, q;
        int dest;
        while (edge != null) {
            dest = edge.destVertex;
            p = null;
            q = vertices[dest].adj;

            while (q != null && q.destVertex != v) {
                p = q;
                q = q.link;
            }
            if (q != null) {
                if (p == null) {
                    vertices[dest].adj = q.link; // 说明只有一个节点
                } else {
                    p.link = q.link;
                }
            }
            numEdges--;
            edge = edge.link;

        }

        vertices[v] = vertices[numVertices - 1];//填补
        vertices[numVertices - 1] = null;
        // 将原来和 numVertices-1 节点关联的边上 所有的 destVertex 改成 v
        Edge e = vertices[v].adj;
        while (e != null) {
            dest = e.destVertex;
            p = vertices[dest].adj;
            while (p != null) {
                if (p.destVertex == numVertices - 1) {
                    p.destVertex = v;
                    break;
                } else {
                    p = p.link;
                }
            }
            e = e.link;
        }
        numVertices--;
        return true;
    }

    /**
     * 插入边
     *
     * @param v1
     * @param v2
     * @param weight
     * @return
     */
    public boolean insertEdge(int v1, int v2, double weight) {
        if (v1 >= 0 && v1 < numVertices && v2 >= 0 && v2 < numVertices) {
            Edge p = null, q = vertices[v1].adj;
            if (q == null) {
                vertices[v1].adj = new Edge(v2, weight);
            } else {
                while (q != null) {
                    p = q;
                    if (q.destVertex == v2) return false;
                    else q = q.link;
                }
                p.link = new Edge(v2, weight);
            }

            p = null;
            q = vertices[v2].adj;
            if (q == null) {
                vertices[v2].adj = new Edge(v1, weight);
            } else {
                while (q != null) {
                    p = q;
                    if (q.destVertex == v1) return false; // 边已经有了，重复插入
                    else q = q.link;
                }
                p.link = new Edge(v1, weight);
            }
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
        if (v1 < 0 || v1 >= numVertices | v2 < 0 || v2 >= numVertices) return false;

        Edge q = vertices[v1].adj, p = null;
        while (q != null && q.destVertex != v2) {
            p = q;
            q = q.link;
        }
        if (q == null) return false;
        else {
            if (p == null) {
                vertices[v1].adj = q.link; // q.link ==null
            } else {
                p.link = q.link;
            }
        }
        q = vertices[v2].adj;
        p = null;
        while (q != null && q.destVertex != v1) {
            p = q;
            q = q.link;
        }
        if (q == null) return false;
        else {
            if (p == null) {
                vertices[v2].adj = q.link;
            } else {
                p.link = q.link;
            }
        }
        numEdges--;
        return true;
    }

    /**
     * 输出整个邻接表
     */
    public void printAdj() {
        Edge p = null;
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%5s:", vertices[i].name);
            p = vertices[i].adj;
            while (p != null) {
                System.out.printf("->%-2s=%-5.1f", vertices[p.destVertex].name, p.weight);
                p = p.link;
            }
            System.out.println();
        }
    }

    public void dfs() {
        System.out.printf("dfs: ");
        dfs(0, new boolean[numVertices]);
        System.out.println();
    }

    /**
     * 递归
     * 对邻接表表示的图采用dfs遍历
     *
     * @param v        当前遍历到的顶点的位置
     * @param visisted 访问列表
     */
    public void dfs(int v, boolean[] visisted) {
        if (v < 0 || v >= numVertices || visisted[v] == true) return;
        visisted[v] = true;
        System.out.printf("%5s", vertices[v].name);
        Edge p = vertices[v].adj;
        while (p != null) {
            if (visisted[p.destVertex] == false) {
                dfs(p.destVertex, visisted);
            }
            p = p.link;
        }
    }


    public void bfs() {
        System.out.printf("bfs:\t");
        int v = 0;
        boolean[] visited = new boolean[numVertices];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.push(v);
        visited[v] = true;
        System.out.printf("%5s", vertices[v].name);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            Edge e = vertices[index].adj;
            while (e != null) {
                if (visited[e.destVertex] == false) {
                    System.out.printf("%5s", vertices[e.destVertex].name);
                    visited[e.destVertex] = true;
                    queue.offer(e.destVertex);
                }
                e = e.link;
            }
        }
        System.out.println();
    }

    public void dfsStack() {
        System.out.printf("dfsStack:\t");
        ArrayDeque<Edge> stack = new ArrayDeque<>();
        Edge e = vertices[0].adj;
        System.out.printf("%5s", vertices[0].name);
        boolean[] visited = new boolean[numVertices];
        visited[0] = true;
        stack.push(e);
        while (!stack.isEmpty()) {
            e = stack.pop();
            while (e != null) {
                if (visited[e.destVertex] == false) {
                    visited[e.destVertex] = true;
                    System.out.printf("%5s", vertices[e.destVertex].name);
                    e = vertices[e.destVertex].adj;
                    stack.push(e);
                } else {
                    e = e.link;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList(10);
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
        graph.printAdj();

        graph.dfs();
        graph.dfsStack();
        graph.bfs();
    }
}
