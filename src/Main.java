import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                x[j] = sc.nextInt();
            }
            for (int j = 0; j < 4; j++) {
                y[j] = sc.nextInt();
            }

            boolean res = judge(x, y);
            if(res){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }

    private static boolean judge(int[] x, int[] y) {
        double[] distances = new double[6];
        distances[0] = cal(x[0], y[0], x[1], y[1]);
        distances[1] = cal(x[0], y[0], x[2], y[2]);
        distances[2] = cal(x[0], y[0], x[3], y[3]);
        distances[3] = cal(x[1], y[1], x[2], y[2]);
        distances[4] = cal(x[1], y[1], x[3], y[3]);
        distances[5] = cal(x[2], y[2], x[3], y[3]);

        int equalCount = 0;
        int[] equalCountIndex = new int[5];
        int countIndex = 0;
        for (int i = 1; i < 6; i++) {
            if (distances[0] == distances[i]) {
                equalCountIndex[countIndex++] = i;
                equalCount++;
            }
        }
        if (equalCount == 1) {
            double compareNum;
            if (equalCountIndex[0] == 5) {
                compareNum = distances[4];
            } else {
                compareNum = distances[equalCountIndex[0] + 1];
            }
            int countNum = 0;
            for (int i = 1; i < 6; i++) {
                if (compareNum == distances[i]) {
                    countNum++;
                }
            }
            if (countNum == 4) {
                return true;
            }else{
                return false;
            }
        } else if (equalCount == 3) {
            double[] comp = new double[2];
            int count = 0;
            for (int i = 1; i < 6; i++) {
                if (distances[i] != distances[0]) {
                    comp[count++] = distances[i];
                }
            }
            if (distances[0] == distances[1]) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    private static double cal(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

    }
}