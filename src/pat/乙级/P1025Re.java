package pat.乙级;

import java.util.Scanner;

/**
 * 00100 6 4
 * 00000 4 99999
 * 00100 1 12309
 * 68237 6 -1
 * 33218 3 00000
 * 99999 5 68237
 * 12309 2 33218
 * <p>
 * created by Ethan-Walker on 2018/12/24
 */
public class P1025Re {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int startAddr = s.nextInt();
        int count = s.nextInt();
        int k = s.nextInt();

        int[] value = new int[100001]; // value[addr] = y  ：地址为addr上的值为 y
        int[] next = new int[100001]; // next[addr1] = addr2 ： addr1节点的下一个是 addr2节点

        for (int i = 0; i < count; i++) {
            int addr = s.nextInt();
            value[addr] = s.nextInt();
            next[addr] = s.nextInt();
        }

        int[] address = new int[count + 1];
        address[0] = startAddr;
        for (int i = 1; i <= count; i++) {
            address[i] = next[address[i - 1]];
        }

        for (int i = 0; i + k <= count; i += k) {
            reverse(address, i, k);
        }
        address[count] = -1;
        for (int i = 0; i < count - 1; i++) {
            System.out.printf("%05d %d %05d\n", address[i], value[address[i]], address[i + 1]);
        }
        System.out.printf("%05d %d -1\n", address[count - 1], value[address[count - 1]]);
    }

    public static void reverse(int[] address, int start, int num) {
        int end = start + num - 1;
        while (start < end) {
            int t = address[start];
            address[start] = address[end];
            address[end] = t;

            start++;
            end--;
        }

    }

}
