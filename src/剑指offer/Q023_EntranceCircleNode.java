package 剑指offer;

/**
 * 寻找环的入口节点
 * 1. 判断链表是否有环  快慢指针法
 * 2. 获得环的长度 k
 * 3. 寻找入口节点
 * created by Ethan-Walker on 2018/12/6
 */
public class Q023_EntranceCircleNode {


    public ListNode getEntranceNode(ListNode head) {
        if (head == null || head.next == null) return null;
        if (head.next == head) return head;
        ListNode p = head, q = head.next;

        while (q.next != null && p != q) {
            p = p.next;
            q = q.next.next;
        }
        if (q.next == null) return null; // 无环

        //p 、q 在环内, 计算环的长度
        q = q.next;
        int len = 1;
        while (q != p) {
            q = q.next;
            len++;
        }


        // q 先走 len 步，然后p q 一起走，当 p == q 时，p、q 在环入口处汇合（q比p多走了一个环）
        p = head;
        q = head;
        for (int i = 0; i < len; i++) {
            q = q.next;
        }
        while (p != q) {
            p = p.next;
            q = q.next;
        }

        return p;


    }

}
