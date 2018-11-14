package graph.adjacency_list;

/**
 * 有向图的邻接表表示
 * created by Ethan-Walker on 2018/11/10
 */
public class DirectedGraphAdjList {


    class Edge {
        int destOrPrevious; // 存储的是该边的终点 或者 起点
        byte flag; // flag = 0 表示存的是 dest， 否则存的是 previous
        double weight;
    }

    class Vertex {
        String name;
        Edge output; // 邻接表，该顶点为始点的所有边集合
        Edge input;  // 逆邻接表，该顶点为终点的所有边集合
    }
}
