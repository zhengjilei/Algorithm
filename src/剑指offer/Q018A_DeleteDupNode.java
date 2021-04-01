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
    public ListNode delDupNode(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        if (pHead == null) return null;
        ListNode prev = pHead;
        ListNode next = pHead.next;
        while (next != null) {
            while (next != null && next.val == prev.val) {
                next = next.next;
            }
            prev.next = next;
            if (next != null) {
                prev = next;
                next = next.next;
            }
        }
        return pHead;

    }

    /**
     * 删除排序链表中的所有重复节点（重复节点不保留）
     * 头结点可能需要删除
     *
     * @param head
     * @return
     */
    public ListNode deleteSortedDupNode2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, newHead = null;
        ListNode cur = head, follow = null;

        while (cur != null) {
            // 过滤掉与 cur 相同值的所有节点
            follow = cur.next;
            while (follow != null && follow.val == cur.val) {
                follow = follow.next;
            }
            // follow 要么指向第一个不等于 cur 的节点, 要么等于 null

            if (cur.next == follow) {
                // 说明 cur 不是重复节点(无论 follow是否为 null)
                if (newHead == null) {
                    newHead = cur;
                } else {
                    prev.next = cur;
                }
                prev = cur;
            }
            cur = follow; // 继续判断下一个节点是否是重复节点
        }
        // 这里涉及到的可能是需要一次删除多个重复节点，没有进行删除跳过（prev.next= follow）
        // 而是找到一个非重复节点才链接到 prev 后 prev.next= cur
        // 这就埋下一个隐患，当结束后 prev指向最后一个非重复节点
        // 如果prev 原先链接的后面的节点是重复节点时，并没有断开与prev的连接. 所以最后这里必须手动断开
        if (prev != null) {  // 示例 1 2 2
            prev.next = null;
        }
        return newHead;
    }

    // 另一种写法，上面的写法其实更高效一点（当有大量重复节点时）
    public ListNode deleteSortedDupNode2Another(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, newHead = null;
        ListNode cur = head, follow = null;

        while (cur != null) {
            // 过滤掉与 cur 相同值的所有节点
            follow = cur.next;
            while (follow != null && follow.val == cur.val) {
                follow = follow.next;
            }
            // follow 要么指向第一个不等于 cur 的节点, 要么等于 null

            if (cur.next == follow) {
                // 说明 cur 不是重复节点(无论 follow是否为 null)
                if (newHead == null) {
                    newHead = cur;
                } else {
                    prev.next = cur;
                }
                prev = cur;
            } else {
                // cur 是重复节点
                if (prev != null) {
                    prev.next = follow; // 删除中间所有的重复节点，链接到下一个比较的节点
                }
            }
            cur = follow; // 继续判断下一个节点是否是重复节点
        }
        return newHead;
    }


    public void test() {

    }

}
