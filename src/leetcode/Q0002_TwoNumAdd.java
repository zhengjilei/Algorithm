package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q0002_TwoNumAdd {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Node = l1, l2Node = l2;
        ListNode resHead = new ListNode(0);

        ListNode resNode = null, prev = resHead;
        int jinwei = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += jinwei;
            resNode = new ListNode(sum % 10);
            prev.next = resNode;
            prev = resNode;
            if (sum >= 10) {
                jinwei = 1;
            } else {
                jinwei = 0;
            }
        }
        if (jinwei == 1) {
            resNode = new ListNode(jinwei);
            prev.next = resNode;
        }
        return resHead.next;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }

}
