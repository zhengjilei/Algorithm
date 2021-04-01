package leetcode;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q382A_RandomNode {
    private ListNode head;
    private Random random;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public Q382A_RandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        int count = 1;
        ListNode select = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (random.nextInt(++count) == 0) {
                select = cur;
            }
            cur = cur.next;
        }
        return select.val;
    }
}
