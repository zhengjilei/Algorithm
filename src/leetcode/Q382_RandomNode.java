package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q382_RandomNode {

    private ListNode head;
    Random random;
    private List<Integer> list;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public Q382_RandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
        list = new ArrayList<>();
        ListNode t = head;
        while (t != null) {
            list.add(t.val);
            t = t.next;
        }
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }


}
