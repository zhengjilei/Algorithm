package leetcode;

/**
 * 删除有序链表中的重复节点，只保留原始链表中 没有重复出现 的数字。
 * created by Ethan-Walker on 2018/12/25
 */
public class Q082_DeleteDupNode {

    /**
     * 思路：
     * 一个节点 prev ，是 cur 的前趋节点
     * 判断节点 cur 是否是重复节点 ,另一个节点 follow 往后遍历，直到为空或者遇到第一个不等于 cur 的节点
     * 如果 cur.next== follow 说明没有重复节点，更新prev/cur/follow  ，继续循环
     * 如果 cur.next != follow 说明中间有重复节点，需要删除 [cur,follow) 之间的所有节点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        return head;
    }

}
