package pat.乙级;

import java.util.Scanner;

/**
 * Created by lenovo on 2018/4/22.
 * 翻转链表
 * 给定一个常数K以及一个单链表L，请编写程序将L中每K个结点反转。
 * 例如：给定L为1→2→3→4→5→6，K为3，则输出应该为3→2→1→6→5→4；
 * 如果K为4，则输出应该为4→3→2→1→5→6，即最后不到K个元素不反转。
 */
public class P1025 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int headAddr = sc.nextInt();
        int nodeCount = sc.nextInt();
        int reverseCount = sc.nextInt();

        int[] arr = new int[100000];
        int[] value = new int[100000];

        for (int i = 0; i < nodeCount; i++) {
            int index = sc.nextInt();
            value[index] = sc.nextInt();
            arr[index] = sc.nextInt(); // index 节点的下一个 节点地址
        }

        // 逆转整个链表
        int head = reverse(arr, headAddr);
        int index = head;
        while ((index ) != -1) {
            System.out.print(value[index]+"  ");
            index = arr[index];
        }

    }


    /**
     * 逆转整个链表，返回 头地址
     *
     * @param arr
     * @param firstAddr
     * @return
     */
    private static int reverse(int[] arr, int firstAddr) {

        int pre = -1;
        int now = firstAddr;
        int next = arr[firstAddr];
        while (true) {
            arr[now] = pre;
            pre = now;
            now = next;
            if (now == -1) {
                break;
            }
            next = arr[next];
        }
        return pre;
    }

}
