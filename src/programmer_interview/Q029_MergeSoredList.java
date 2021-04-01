package programmer_interview;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q029_MergeSoredList {


    /**
     * 代码量少的写法
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        ListNode newHead = null, prev = null;

        if (head1.val <= head2.val) {
            newHead = head1;
            head1 = head1.next;
        } else {
            newHead = head2;
            head2 = head2.next;
        }
        prev = newHead;
        while (head1 != null || head2 != null) {
            if (head2 == null || (head1 != null && head1.val <= head2.val)) { // head2 直接进（说明head1!=null） 保证head1==null时不会进入该条件中
                prev.next = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }
            prev = prev.next;
        }
        return newHead;
    }

    // 更清晰易懂的写法
    public ListNode merge2(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        ListNode newHead = null, prev = null;

        if (head1.val <= head2.val) {
            newHead = head1;
            head1 = head1.next;
        } else {
            newHead = head2;
            head2 = head2.next;
        }
        prev = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                prev.next = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }
            prev = prev.next;
        }

        while (head1 != null) {
            prev.next = head1;
            head1 = head1.next;
            prev = prev.next;
        }
        while (head2 != null) {
            prev.next = head2;
            head2 = head2.next;
            prev = prev.next;
        }
        return newHead;
    }
}
