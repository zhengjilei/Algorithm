import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/1.
 */
public class BinarySearch {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = random.nextInt(100);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(200);
//            Arrays
        }

        Arrays.sort(arr);
        int value = arr[n - 10];
        int index = binarySearch(arr, value);
        int value2 = 200;
        int index2 = binarySearch(arr, value2);
        System.out.println(index);
        System.out.println(index2);
    }

    public static int binarySearch(int[] arr, int value) {

        int l = 0, r = arr.length - 1;
        int mid;
        while (l <= r) {
            mid = (r + l) / 2;
            if (value < arr[mid]) {
                r = mid - 1;
            } else if (value > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;

    }
}
