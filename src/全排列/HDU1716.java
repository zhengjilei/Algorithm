package 全排列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HDU1716 {

    public static void permutation(int[] a, int n) {
        Arrays.sort(a);
        if (a[0] == 0) {
            for (int i = 1; i < n; i++) {
                if (a[i] != 0) {
                    swap(a, 0, i);
                    break;
                }
            }
        }
        ArrayList<StringBuilder> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(a[i]);
        }
        list.add(sb);
        while (true) {
            int index = -1;
            for (int j = n - 2; j >= 0; j--) {
                if (a[j] < a[j + 1]) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                if (index == 0) {
                    // 说明最高位要变了
                    int i = 0;
                    for (i = 0; i < list.size() - 1; i++) {
                        System.out.print(list.get(i).toString() + " ");
                    }
                    System.out.println(list.get(i).toString());
                    list.clear();
                }
                StringBuilder s = new StringBuilder();
                int min = a[index + 1];
                int minIndex = index + 1;
                for (int j = minIndex + 1; j < n; j++) {
                    if (a[j] > a[index] && a[j] < min) {
                        min = a[j];
                        minIndex = j;
                    }
                }
                swap(a, index, minIndex);
                Arrays.sort(a, index + 1, n);
                for (int i = 0; i < n; i++) {
                    s.append(a[i]);
                }
                list.add(s);
            } else {
                if (list.size() != 0) {
                    int i = 0;
                    for (i = 0; i < list.size() - 1; i++) {
                        System.out.print(list.get(i).toString() + " ");
                    }
                    System.out.println(list.get(i).toString());
                    list.clear();
                }
                break;
            }
        }
    }

    private static void swap(int[] a, int index, int minIndex) {
        int t = a[index];
        a[index] = a[minIndex];
        a[minIndex] = t;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] a = new int[n];
        boolean flag;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            flag = false;
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                if (a[i] != 0) {
                    flag = true;
                }
            }
            if (count == 0) {
                count++;
            } else {
                if (flag) {
                    System.out.println();
                }
            }
            if (flag) {
                permutation(a, n);
            } else {
                break;
            }
        }
    }
}
