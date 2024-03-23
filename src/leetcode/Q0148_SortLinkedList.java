package leetcode;

/**
 * 链表的归并排序
 * 由于链表节点可以直接利用，故不需要额外的空间
 * created by Ethan-Walker on 2018/12/24
 */
public class Q0148_SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null) return head; // 只有一个节点就不要划分了，防止划分后，一个链表尾空，合并时出错

        ListNode mid = getMidNode(head);

        ListNode head2 = mergeSort(mid.next);// 先对后半段排序，防止设置 mid.next = null 后，导致找不到后半部分

        mid.next = null;   // 使得前一半单独成链
        ListNode head1 = mergeSort(head);

        return merge(head1, head2);
    }

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
