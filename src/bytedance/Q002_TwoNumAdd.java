package bytedance;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q002_TwoNumAdd {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 != null ? l1 : l2;
        int jinwei = 0;
        int sum = l1.val + l2.val;

        if (sum >= 10) {
            jinwei = 1;
            sum %= 10;
        }
        l1 = l1.next;
        l2 = l2.next;
        ListNode res = new ListNode(sum);
        ListNode prev = res;
        while (l1 != null || l2 != null) {
            sum = jinwei;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum >= 10) {
                jinwei = 1;
                sum %= 10;
            } else {
                jinwei = 0;
            }
            prev.next = new ListNode(sum);
            prev = prev.next;
        }
        if (jinwei == 1) {
            prev.next = new ListNode(1);
        }
        return res;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
