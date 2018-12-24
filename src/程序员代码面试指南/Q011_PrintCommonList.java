package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2018/12/20
 */
public class Q011_PrintCommonList {


    /**
     * 打印两个有序链表中具有相同值的节点的值
     *
     * @param head1
     * @param head2
     */
    public void printSeq(ListNode head1, ListNode head2) {

        while (head1 != null && head2 != null) {
            if (head1.val == head2.val) {
                System.out.print(head1.val + ",");
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.val < head2.val) {
                head1 = head1.next;
            } else {
                head2 = head2.next;
            }
        }
    }

    /**
     * 打印两个链表的公共部分（重合节点）
     * 思路：找到两个链表的第一个公共节点
     * 关键点： 两个链表是走向分叉 还是走向聚合（错误，不可能分叉，只有一个 next 节点，怎么可能分叉）
     *
     * @param head1
     * @param head2
     */
    public void print(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return;
        }

        if (head1 == head2) {
            while (head1 == head2 && head1 != null) {
                System.out.print(head1.val);
                head1 = head1.next;
                head2 = head2.next;
            }
        } else {
            int length1 = getLength(head1);
            int length2 = getLength(head2);

            ListNode fast = head1, slow = head2;
            if (length1 < length2) {
                fast = head2;
                slow = head1;
            }
            int step = Math.abs(length1 - length2);
            while (step > 0) {
                fast = fast.next;
                step--;
            }

            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            while (fast != null) {
                System.out.print(fast.val + ",");
                fast = fast.next;
                slow = slow.next;
            }
        }
    }

    public int getLength(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }
}
