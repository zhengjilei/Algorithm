package a_review.graph;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Dijkstra {

    int[] dijkstra(int[][] matrix, int source) {

        int n = matrix.length;
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = matrix[source][i];

        }

        boolean[] inSet = new boolean[n];
        inSet[source] = true;

        // 加入 n-1 个顶点
        for (int i = 1; i < n; i++) {

            // 选出源点 source 到各不在集合中顶点的距离中的最小值
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!inSet[j] && matrix[source][j] < min) {
                    min = matrix[source][j];
                    minIndex = j;
                }
            }
            // 选择 minIndex 加入集合中
            inSet[minIndex] = true;

            // 更新 source 到集合外其他顶点的距离
            for (int j = 0; j < n; j++) {
                if (!inSet[j]
                        && matrix[minIndex][j] < Integer.MAX_VALUE
                        && dist[minIndex] + matrix[minIndex][j] < dist[j]) {
                    dist[j] = dist[minIndex] + matrix[minIndex][j];
                }
            }

        }
        return dist;

    }
}
