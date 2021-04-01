package pat.乙级;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/22.
 */
public class P1025New {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int headAddr = sc.nextInt();
        int nodeCount = sc.nextInt();
        int reverseGap = sc.nextInt();

        int[] data = new int[100001];
        int[] next = new int[100001];
        int[] list = new int[nodeCount + 1];
        for (int i = 0; i < nodeCount; i++) {
            int curAddr = sc.nextInt();
            data[curAddr] = sc.nextInt();
            next[curAddr] = sc.nextInt();
        }
        int tempAddr = headAddr;
        int sum = 0;
        while (tempAddr != -1) {
            list[sum++] = tempAddr;
            tempAddr = next[tempAddr];
        }
        // 地址依次存到  list 中, 数据存到 data[addr]
        // 反转，只需将 list 中的地址进行反转即可

        for (int i = 0; i < sum - sum % reverseGap; i += reverseGap) {
            reverse(list, i, reverseGap);  // 反转从索引i 开始，反转数量为： reverseGap
        }
        list[sum] = -1;  // 最后一个节点的 下一个地址是 -1
        for (int i = 0; i < sum - 1; i++) {
            System.out.printf("%05d %d %05d\n", list[i], data[list[i]], list[i + 1]);
        }

        System.out.printf("%05d %d -1\n", list[sum - 1], data[list[sum - 1]]);
    }

    /**
     * 从索引 i 处开始，反转的数量为 reverseGap
     * @param list
     * @param i
     * @param reverseGap
     */
    private static void reverse(int[] list, int i, int reverseGap) {
        int start = i;
        int end = i + reverseGap - 1;
        while (start < end) {
            list[start] = list[start] + list[end] - (list[end] = list[start]);
            start++;
            end--;
        }
    }
}
