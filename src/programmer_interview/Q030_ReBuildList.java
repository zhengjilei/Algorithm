package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q030_ReBuildList {


    public ListNode rebuild(ListNode head) {
        if (head == null || head.next == null) return head;

        // 找出链表的中间节点（偶数个找后一个），以及中间节点的 前趋节点prev

        ListNode fast = head, slow = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // head~prev 构成左半区  slow~tail  构成有半区
        prev.next = null; // 断开左右半区
        ListNode left = head, right = slow;
        ListNode rightFollow = null;
        while (left.next != null) {
            // 将 right 插入到 left 后
            rightFollow = right.next;
            right.next = left.next;
            left.next = right;

            left = right.next;
            right = rightFollow;
        }

        left.next = right; // 左半区最后一个节点，和右半区剩余节点直接相连
        return head;
    }


    @Test
    public void test() {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);

        a1.next = a2;
        a2.next = a3;
//        a3.next = a4;
//        a4.next = a5;
//        a5.next = a6;


        ListUtil.print(rebuild(a1));

    }
}
