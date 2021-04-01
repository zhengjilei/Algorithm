package leetcode;

/**
 * created by Ethan-Walker on 2018/12/23
 */
public class Q142_CircleLinkedList {


    /**
     * 计算环的入口点
     *
     * @param head
     * @return
     */
    public ListNode getEnterLoop(ListNode head) {
        if (head == null) return null;

        ListNode fast = head, slow = head;
        boolean hasLoop = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return null;

        // 有环，且 fast slow 在环中

        // 先计算环的长度
        int count = 1;
        while (slow.next != fast) {
            count++;
            slow = slow.next;
        }

        fast = slow = head;
        // 快指针先前行 count 步
        while (count > 0) {
            fast = fast.next;
            count--;
        }

        // 已经判断出有环了，不用再判断fast==null slow==null
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


}
