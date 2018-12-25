package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q028_InsertSortedList {

    public ListNode insertSorted(ListNode head, int num) {
        if (head == null) return new ListNode(num);

        ListNode cur = head, prev = null;
        //找到第一个比 num 大于等于的节点，prev 为其前一个节点，链接到 prev 和 cur 之间
        while (cur != null && cur.val < num) {
            prev = cur;
            cur = cur.next;
        }
        ListNode node = new ListNode(num);
        if (prev == null) {
            // 说明头结点就大于等于 num 了 , num 作为新的头结点返回
            node.next = head;
            return node;
        } else {
            //cur 指向第一个大于等于 num 的节点，prev为其前一个节点
            node.next = cur;
            prev.next = node;
            return head;
        }
    }
}
