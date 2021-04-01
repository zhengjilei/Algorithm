package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q061_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        // 得到链表的长度
        int len = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            len++;
        }

        k %= len; // 多余的倍数没有用

        // 找到倒数第 k+1 个节点 和 尾结点
        ListNode fast = head, slow = head;
        while (k > 0) { // 前进k步
            k--;
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // fast 指向尾结点 ,slow 指向倒数第 k+1 个节点
        fast.next = head; // 链接成环
        head = slow.next; // 新的头结点
        slow.next = null; // 断开形成新链
        return head;
    }

}
