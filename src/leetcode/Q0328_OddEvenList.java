package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0328_OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        // 排除0 个或 1个节点的情况，保证奇偶节点都有，方便后面处理
        if (head == null || head.next == null) return head;


        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddTail = oddHead, evenTail = evenHead;


        ListNode nextOdd = evenTail.next, nextEven;
        while (nextOdd != null) {
            nextEven = nextOdd.next;
            // 链接新的  odd 节点
            oddTail.next = nextOdd;
            oddTail = nextOdd;

            // even 节点判断是否存在，存在则链接
            if (nextEven != null) {
                evenTail.next = nextEven;
                evenTail = nextEven;
                nextOdd = nextEven.next;
            } else {
                nextOdd = null;
            }
        }

        oddTail.next = evenHead;
        evenTail.next = null;

        return oddHead;
    }
}
