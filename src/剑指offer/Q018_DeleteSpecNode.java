package 剑指offer;


/**
 * 给定链表头指针和指定节点p，在O(1)时间内删除节点 p
 * created by Ethan-Walker on 2018/12/4
 */
public class Q018_DeleteSpecNode {

    /**
     * 删除节点 p, 返回头结点
     * <p>
     * 1. 删除的链表 只有待删除节点一个节点
     * 2. 删除的节点是尾结点
     *
     * @param head
     * @param p
     * @return
     */
    public ListNode deleteSpecNode(ListNode head, ListNode p) { // 假设无附加头结点
        if (head == null || p == null || head == p) return null;

        ListNode q = p.next;
        if (q == null) {
            //说明 p 是最后一个节点，只能顺序查找到 p 的前驱节点
            ListNode t = head;
            while (t.next != p) {
                t = t.next;
            }
            t.next = null;
        } else {
            // 将 q 节点内容复制给 p, 删除 q 节点
            p.val = q.val;
            p.next = q.next;
        }
        return head;

    }

}
