package a_review.graph;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Edge {
    int v1, v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
