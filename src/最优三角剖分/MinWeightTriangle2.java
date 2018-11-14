package 最优三角剖分;

/**
 * Created by EthanWalker on 2017/11/28.
 * 动态规划迭代实现
 */
public class MinWeightTriangle2 {

    private static int[][] weight = {{0, 2, 2, 3, 1, 4}, {2, 0, 1, 5, 2, 3}, {2, 1, 0, 2, 1, 4}, {3, 5, 2, 0, 6, 2}, {1, 2, 1, 6, 0, 1}, {4, 3, 4, 2, 1, 0}};

    private static int[][] minWeightPolygon = new int[7][7];

    public static int calWeightTriangle(int a, int b, int c) {
        return weight[a-1][b-1] + weight[b-1][c-1] + weight[a-1][c-1];
    }
    public static int calMinWeightPolygon(int i, int j){
        for(int sep = 2; sep<=j-i;sep++){
            for(int t = 1;t+sep<=j;t++){
                int min = minWeightPolygon[t][t+1]+minWeightPolygon[t+1][t+sep]+calWeightTriangle(t,t+1,t+sep);
                for(int d = t+2; d<t+sep;d++ ){
                    int temp = minWeightPolygon[t][d]+minWeightPolygon[d][t+sep] + calWeightTriangle(t,d,t+sep);
                    if(temp<min){
                        min = temp;
                    }
                }
                minWeightPolygon[t][t+sep] = min;
            }
        }

        return minWeightPolygon[i][j];
    }

    public static void main(String[] args) {
        for(int i =0;i<7;i++){
            for(int j=0;j<7;j++){
                minWeightPolygon[i][j] = 0;
            }
        }
        System.out.println(calMinWeightPolygon(1,6));
    }
}
