package 剑指offer;

/**
 * created by Ethan-Walker on 2018/12/5
 */
public class Q018A_DeleteDupNode {


    /**
     * 在一个排序的链表中，删除多余的重复节点(多个节点重复，保留第一个)
     *
     * @param head
     */
    public void delDupNode(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p = head, q = head.next;
        while (q != null) {
            if (p.val != q.val) {
                if (p.next != q) {
                    // 说明p q 之间有重复元素 p
                    p.next = q;
                }
                p = q;
                q = q.next;
            } else {
                q = q.next;
            }
        }
        if (p.next != q) {
            p.next = q;
        }

    }

    /**
     * 没有附加头结点
     * 在一个排序的链表中，删除所有的重复节点(多个节点重复，删除所有)
     * 1. 所有节点都被删除
     * 2. 没有重复节点
     * 3. 首节点是重复节点
     * 4. 尾节点是重复节点
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;

        ListNode prev = null, p = pHead; // prev 指向倒数最后一个非重复节点
        ListNode q, head = null;
        while (p != null) {
            q = p.next;
            if (q != null && q.val == p.val) {
                // p 需要删除
                while (q != null && q.val == p.val) {
                    q = q.next;
                }
                if (prev != null) {
                    prev.next = q;
                }
            } else {
                // q == null || q.val != p.val
                // p 是非重复节点
                if (prev == null) {
                    head = p;  // p 是第一个不需要删除的节点

                } else {
                    prev.next = p;
                }
                prev = p;

            }
            p = q;
        }
        return head;
    }


    public void test() {

    }

}
