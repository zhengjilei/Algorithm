package sort.mergesort;

import leetcode.ListNode;

/**
 * 链表的归并排序
 * 空间复杂度: O(1)
 * <p>
 * created by Ethan-Walker on 2018/12/25
 */
public class ListMergeSort {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null) return head; // 只有一个节点就不要划分了，防止划分后，一个链表尾空，合并时出错

        ListNode mid = getMidNode(head);

        ListNode head2 = mergeSort(mid.next);   // 先对后半段排序，防止设置 mid.next = null 后，导致找不到后半部分

        mid.next = null;   // 使得前一半单独成链
        ListNode head1 = mergeSort(head);

        return merge(head1, head2);
    }

    /**
     * 合并两个链表，返回新链表的头结点
     *
     * @param head1
     * @param head2
     * @return
     */
    private ListNode merge(ListNode head1, ListNode head2) {

        ListNode head = null, prev = null;

        if (head1.val <= head2.val) {
            head = head1;
            head1 = head1.next;
        } else {
            head = head2;
            head2 = head2.next;
        }
        prev = head;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                prev.next = head1;
                prev = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                prev = head2;
                head2 = head2.next;
            }
        }
        while (head1 != null) {
            prev.next = head1;
            prev = head1;
            head1 = head1.next;
        }
        while (head2 != null) {
            prev.next = head2;
            prev = head2;
            head2 = head2.next;
        }
        return head;
    }


    /**
     * 快慢指针，返回链表中间节点
     * 偶数个：返回中间节点的前一个
     *
     * @param head
     * @return
     */
    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head.next, slow = head;// 偶数个，结束时 slow 指向中间节点的前一个
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }

}
