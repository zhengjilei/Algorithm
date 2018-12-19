package 程序员代码面试指南;

/**
 * 删除链表的第 a/b 个节点
 * created by Ethan-Walker on 2018/12/20
 */
public class Q013A_DeletePercentNode {

    public ListNode deleteABPercentNode(ListNode node, int a, int b) {
        if (node == null) return null;

        int length = getLength(node);

        int kth = (int) Math.ceil(a * length * 1.0 / b);//删除第 k 个节点

        if (kth > length) throw new RuntimeException("a b 取值不正确");

        if (kth == 1) {
            return node.next;
        }
        // 找到第 k-1 个节点
        kth -= 1;
        ListNode prev = node;
        while (kth > 1 && prev != null) {
            prev = prev.next;
            kth -= 1;
        }
        prev.next = prev.next.next;

        return node;
    }

    public int getLength(ListNode node) {
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }
}
