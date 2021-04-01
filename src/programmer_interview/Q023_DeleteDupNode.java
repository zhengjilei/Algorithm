package programmer_interview;

import java.util.HashSet;

/**
 * 删除无序链表重复出现的节点，删除所有重复的元素，使得每个元素只出现一次。
 * 5->2->2->3 删除之后 5->2->3
 */
public class Q023_DeleteDupNode {


    /**
     * 思路一：哈希表，将每个节点值存入，如果节点值已经存在集合中，删除；不存在，放入哈希表中
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param head
     * @return
     */
    public ListNode delete(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head, cur = head.next;
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(head.val);

        while (cur != null) {
            if (hashSet.contains(cur.val)) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;  //虽然cur 可能被删除了，但其 next 指针仍然指向下一个节点
        }
        return head;
    }

    // 如果要求空间复杂度为O(1) ，可以将无序链表转换成有序链表（要求该过程空间复杂度也是 O(1) ）
    // 时间复杂度: O(nlogn)~O(n^2)  可以采用链表的归并排序


}
