package graph.kruscal;

/**
 * created by Ethan-Walker on 2018/11/14
 */
public class Edge {
    public int vertex1;
    public int vertex2;
    double cost;

    public Edge(int a, int b, double cost) {
        this.vertex1 = a;
        this.vertex2 = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return vertex1 + "->" + vertex2 + ": " + cost+"\n";
    }
}
