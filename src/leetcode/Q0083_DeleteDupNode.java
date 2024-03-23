package leetcode;

/**
 * 删除有序链表中的重复元素，使得每个元素只出现一次。
 * created by Ethan-Walker on 2018/12/25
 */
public class Q0083_DeleteDupNode {

    /**
     * 思路： prev 指向开始节点，cur 往后遍历直到为空或者 指向第一个不等于 prev 的节点. 让prev链接到cur，并让 prev指向 cur。
     * 继续循环
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head, cur = head.next;

        while (cur != null) {
            // 判断 prev 是否是重复节点，cur 指向第一个不等于 prev 值的节点
            while (cur != null && cur.val == prev.val) {
                cur = cur.next;
            }

            prev.next = cur;        // 不论 prev 是否是重复节点，只保留了一个
            prev = cur;
            if (cur != null) {  // 防止 cur == null 空指针异常
                cur = cur.next;
            }
        }
        return head;
    }
}
