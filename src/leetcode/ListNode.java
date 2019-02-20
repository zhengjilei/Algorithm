package leetcode;

/**
 * created by Ethan-Walker on 2018/12/23
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return this.val + "";
    }
}
