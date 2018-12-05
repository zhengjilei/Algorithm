package 剑指offer;

/**
 * 求链表的中间节点
 * created by Ethan-Walker on 2018/12/6
 */
public class Q022_MidNode {

    /**
     * 如果是奇数，返回中间节点，偶数，返回中间两个节点的靠后节点
     *
     * @param head
     * @return
     */
    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = head, q = head;

        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }

        return p;
    }

    /**
     * 如果是奇数，返回中间节点，偶数，返回中间两个节点的靠前节点
     * @param head
     * @return
     */
    public ListNode getMidNode2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head, q = head;

        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if (q.next.next == null) {
                return p;
            }
        }
        return p;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
