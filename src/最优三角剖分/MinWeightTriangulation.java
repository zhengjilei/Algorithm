package 最优三角剖分;

/**
 * 凸多边形的最优三角剖分: 将凸多边形分割成多个三角形，每个三角形的权重值 之和的最小值
 * （三角形的权重值等于 三条边的权重之和）\
 * 备忘录递归
 * Created by EthanWalker on 2017/11/28.
 */
public class MinWeightTriangulation {

    private static int[][] weight = {{0, 2, 2, 3, 1, 4}, {2, 0, 1, 5, 2, 3}, {2, 1, 0, 2, 1, 4}, {3, 5, 2, 0, 6, 2}, {1, 2, 1, 6, 0, 1}, {4, 3, 4, 2, 1, 0}};

    private static int[][] minWeightPolygon = new int[7][7];

    public static int calWeightTriangle(int a, int b, int c) {
        return weight[a-1][b-1] + weight[b-1][c-1] + weight[a-1][c-1];
    }
    public static int calMinWeightPolygon(int i, int j){
        if(j-i<=1) return 0;
        if(minWeightPolygon[i][j]!=0){
            return minWeightPolygon[i][j];
        }
        int min= calMinWeightPolygon(i,i+1) +calMinWeightPolygon(i+1,j)+calWeightTriangle(i,i+1,j);
        for(int k = i+2;k<j;k++){
            int temp = calMinWeightPolygon(i,k) +calMinWeightPolygon(k,j)+ calWeightTriangle(i,k,j);
            if(temp<min){
                min = temp;
            }
        }
        minWeightPolygon[i][j] = min;
        return min;
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
