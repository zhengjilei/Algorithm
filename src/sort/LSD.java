package sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Arrays;
import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class LSD {

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    // 桶内结构
    class LinkedList {
        Node head;
        Node tail;
    }

    public void LSDSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // 遍历得到最大值，同时将数组初始化为一个链表
        Node head = new Node(nums[0]);
        Node prev = head, cur = null;
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = new Node(nums[i]);
            prev.next = cur;
            prev = cur;
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        prev.next = null;

        // 初始桶
        LinkedList[] bucket = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            bucket[i] = new LinkedList();
        }


        int posDiv = 1, remain = 0; //  posDiv = 1 表示求个位 , 10 十位, 100..
        cur = head;
        Node next, tail;
        while (maxVal / posDiv != 0) {
            cur = head;
            // 分配
            while (cur != null) {
                next = cur.next;
                cur.next = null;
                remain = cur.val / posDiv % 10;
                if (bucket[remain].head == null) {
                    bucket[remain].head = cur;
                    bucket[remain].tail = cur;
                } else {
                    // nums[i] 添加到 链尾
                    bucket[remain].tail.next = cur;
                    bucket[remain].tail = cur;
                }
                cur = next;
            }

            // 收集
            head = null;
            tail = null;
            for (int i = 0; i < 10; i++) {
                if (bucket[i].head != null) {
                    if (head == null) {
                        head = bucket[i].head;
                        tail = bucket[i].tail;
                    } else {
                        tail.next = bucket[i].head;
                        tail = bucket[i].tail;
                    }
                }
                bucket[i].head = bucket[i].tail = null; // 清空
            }
            posDiv *= 10;
        }

        int i = 0;
        while (head != null) {
            nums[i++] = head.val;
            head = head.next;
        }
    }

    @Test
    public void test2() {
        int[] a = {9, 7, 13, 43, 15, 67, 98};
        LSDSort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test() {

        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
            LSDSort(a);

            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }

}
