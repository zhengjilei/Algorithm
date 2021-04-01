package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/23
 */
public class Q021A_TwoLinkedList {


    ListNode firstCommonNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;

        ListNode enterNode1 = getLoopNode(l1);
        ListNode enterNode2 = getLoopNode(l2);

        if (enterNode1 == null && enterNode2 == null) {
            return getNoLoopCommon(l1, l2);
        } else if (enterNode1 != null && enterNode2 != null) {
            // 两个链表均有环
            return getBothLoopCommon(l1, l2, enterNode1, enterNode2);
        }
        return null;

    }


    /**
     * 求两个都有环的链表的第一个公共节点
     * 1. enter1==enter2 两个链表共用一个环，且入口点相同
     * 2. enter1!=enter2
     * 2.1 两个链表独立，各自有一个环
     * 2.2 两个链表共用一个环，但入口点不同
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode getBothLoopCommon(ListNode l1, ListNode l2, ListNode enter1, ListNode enter2) {
        if (enter1 == enter2) {
            // 两个链表共用一个环，且入口点相同,算法和 两个无环单链表相同
            // 只不过在计算链表长度时，需要统计环的长度和头结点到入口节点的长度之和
            // 先计算环的长度
            int count = 1;
            ListNode node = enter1;
            while (node.next != enter1) {
                node = node.next;
                count++;
            }
            int length1 = count, length2 = count;
            ListNode l1Node = l1, l2Node = l2;

            //计算头结点到入口节点的长度
            while (l1Node != enter1) {
                length1++;
                l1Node = l1Node.next;
            }
            while (l2Node != enter2) {
                length2++;
                l2Node = l2Node.next;
            }

            ListNode fast = l1, slow = l2;
            int step = Math.abs(length1 - length2);
            if (length2 > length1) {
                fast = l2;
                slow = l1;
            }

            while (step > 0) {
                fast = fast.next;
                step--;
            }

            while (fast != null && slow != null) {
                if (fast == slow) {
                    return fast;
                }
                fast = fast.next;
                slow = slow.next;
            }

            return null;
        } else {
            // 两入口节点不等：可能是两个独立的链表（两个环），可能是同一个环，不同入口

            ListNode node = enter1;
            while (node.next != enter1) {
                if (node.next == enter2) {
                    // 共用一个环，返回 enter1 enter2均可
                    return enter1;
                }
                node = node.next;
            }
            return null;
        }
    }


    /**
     * 求两个无环节点的第一个公共节点
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode getNoLoopCommon(ListNode l1, ListNode l2) {

        int length1 = getLength(l1);
        int length2 = getLength(l2);

        ListNode fast = l1, slow = l2;
        if (length2 > length1) {
            fast = l2;
            slow = l1;
        }

        int step = Math.abs(length1 - length2);
        while (step > 0) {
            fast = fast.next;
            step--;
        }

        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }


    /**
     * 链表有环，返回入口节点；无环，返回null
     *
     * @param head
     * @return
     */
    public ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null) return null;

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

        int count = 1;
        while (fast.next != slow) {
            fast = fast.next;
            count++;
        }

        fast = slow = head;
        while (count > 0) {
            fast = fast.next;
            count--;
        }

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    @Test
    public void test() {

        ListNode a0 = new ListNode(0);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);

        a0.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a3;
        ListNode b1 = new ListNode(9);
        b1.next = a4;

        System.out.println(firstCommonNode(a0, b1).val);

    }

}
