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

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return this.val + "";
    }
}
