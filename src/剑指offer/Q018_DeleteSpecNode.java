package 剑指offer;


/**
 * 给定链表头指针和指定节点p，在O(1)时间内删除节点 p
 * created by Ethan-Walker on 2018/12/4
 */
public class Q018_DeleteSpecNode {

    /**
     * <p>
     * 1. 删除的链表 只有待删除节点一个节点
     * 2. 删除的节点是尾结点
     *
     * @param head
     * @return
     */
    public ListNode deleteSpecNode(ListNode head, ListNode toDelete) { // 假设无附加头结点
        if (head == null || toDelete == null) {
            throw new RuntimeException("头结点或者待删除节点不能为null");
        }
        if (head == toDelete) return head.next; // head = toDelete 的情况，特殊处理(包括两种情况，只有一个节点/多个节点)

        if (toDelete.next != null) {
            // 非尾结点
            toDelete.val = toDelete.next.val;
            toDelete.next = toDelete.next.next;
        } else {

            ListNode prev = head;
            while (prev != null && prev.next != toDelete) {
                prev = prev.next;
            }
            if (prev == null) {
                throw new RuntimeException("未找到待删除节点");
            }
            prev.next = null;
        }
        return head;

    }

}
