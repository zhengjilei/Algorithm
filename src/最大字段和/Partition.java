package 最大字段和;

/**
 * Created by EthanWalker on 2017/12/1.
 */
public class Partition {

    private static int[] c = {10, 3, -6, -9, 11, -3, 8, -5, -6};

    public static int maxChildMulti(int start, int end) {
        if (start == end) return c[start];

        int mid = (start + end) / 2;
        int leftMax = maxChildMulti(start, mid);
        int rightMax = maxChildMulti(mid + 1, end);

        int temp = c[mid];
        int midLeftMax = temp;

        for (int i = mid - 1; i >= start; i--) {
            temp = temp + c[i];
            if (temp > midLeftMax) {
                midLeftMax = temp;
            }
        }
        temp = c[mid + 1];
        int midRightMax = temp;
        for (int j = mid + 1; j <= end; j++) {
            temp = temp + c[j];
            if (temp > midRightMax) {
                midRightMax = temp;
            }
        }
        int midMax = midLeftMax + midRightMax;
        return Math.max(Math.max(leftMax, rightMax), midMax);
    }

    public static void main(String[] args) {
        int max = maxChildMulti(0, 8);
        System.out.println(max);
    }
}
