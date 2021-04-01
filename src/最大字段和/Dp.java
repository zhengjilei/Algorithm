package 最大字段和;

/**
 * Created by EthanWalker on 2017/12/1.
 */
public class Dp {

    private static int[] c = {10, 3, -6, -9, 11, -3, 8, -5, -6};

    public static int dp() {
        int n = 9;
        int[] b = new int[n];
        b[0] = c[0];
        for (int i = 1; i < n; i++) {
            if (b[i - 1] <= 0) b[i] = c[i];
            else b[i] = b[i - 1] + c[i];
        }

        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(b[i]>max){
                max = b[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int dp = dp();
        System.out.println(dp);
    }
}
