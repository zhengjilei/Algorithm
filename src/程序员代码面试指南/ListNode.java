package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2018/12/20
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "[" + val + ",next=" + (this.next == null ? "null]" : this.next.val + "]");
    }
}
