package linkedlist;

/**
 * created by Ethan-Walker on 2019/1/15
 */
public class LinkedListApply {

    class ListNode {
        int val;
        ListNode next;
    }

    /**
     * 判断单链表是否有环（双指针法 )
     * 快指针每次走两步，慢指针每次走1步
     * 假设有环：慢指针进入环的入口节点时，快指针已经在环内了，设从入口节点开始编号的话，快指针离慢指针此时相差 k 个节点
     * 从慢指针进入环的入口节点时开始统计走的步数 n
     * 2n - n = k 时， 即快指针比慢指针多走了 k 个节点（恰弥补了刚开始的 k 个节点），故此时慢指针快指针相遇
     * <p>
     * 故如果快慢指针相遇，说明一定有环
     *
     * @return
     */
    public boolean hasLoop(ListNode head) {
        if (head == null) return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode prev = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * l1、l2 均为递增链表，合并两个链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode tail = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        while (l1 != null) {
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;
        }
        while (l2 != null) {
            tail.next = l2;
            l2 = l2.next;
            tail = tail.next;
        }

        tail.next = null;
        return head;
    }

    /**
     * 求链表倒数第 k 个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode endK(ListNode head, int k) {
        if (head == null || k < 1) return null;
        ListNode slow = head, fast = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if (k != 0) return null; // 说明fast==null 时，k>0 , 快节点没法先往前走 k 步，即 没有倒数第 k 个节点

        // fast 指向 null 时候，slow 恰指向倒数第 k 个节点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 删除倒数第 k 个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {

        if (head == null || k < 1) return head;
        ListNode slow = head, fast = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if (k != 0) return head; // 说明fast==null 时，k>0 , 快节点没法先往前走 k 步，即 没有倒数第 k 个节点

        ListNode prev = slow;
        // fast 指向 null 时候，slow 恰指向倒数第 k 个节点
        while (fast != null) {
            prev = slow; // 保存倒数第 k 个节点的前一个节点
            slow = slow.next;
            fast = fast.next;
        }

        if (slow == head) {     // 倒数第k 个节点是头结点
            return head.next;
        } else {
            prev.next = slow.next;
        }
        return head;
    }

    public ListNode getMid(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 偶数个节点时：
        // fast 初始化为 head,      则slow 最后返回的是 中间两个节点的后一个节点
        // fast 初始化为 head.next, 则slow 最后返回的是 中间两个节点的前一个节点
        return slow;
    }
}
