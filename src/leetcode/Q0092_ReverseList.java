package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0092_ReverseList {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode leftTail = null, cur = head;

        // 前进 m-1 步，使得 cur 指向第 m 个节点
        for (int i = 1; i < m; i++) {
            leftTail = cur;
            cur = cur.next;
        }

        // 前进，使得 tail 指向第 n 个节点
        ListNode tail = cur;
        for (int i = 0; i < n - m; i++) {
            tail = tail.next;
        }
        // 先保存后面的节点，断开与后面的节点
        ListNode rightStart = tail.next;
        tail.next = null;

        ListNode reverHead = reverse(cur);
        // 反转之后 cur 成为反转那一部分的 tail 了
        cur.next = rightStart;  // 和后面进行连接

        if (leftTail == null) { // m==1
            head = reverHead;
        } else {
            leftTail.next = reverHead;
        }

        return head;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


}
