package programmer_interview;

/**
 * 如果两个链表都没有环, enterNode1 == enterNode2 ==null 可能相交
 * 如果两个链表有环（一定是公共的环），可能入口点不一样
 * created by Ethan-Walker on 2018/12/23
 */
public class Q021_TwoLinkedList {
    public ListNode solve(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        ListNode enterNode1 = hasLoop(list1);
        ListNode enterNode2 = hasLoop(list2);

        if (enterNode1 == null && enterNode2 != null
                || enterNode1 != null && enterNode2 == null) {
            // 说明一个有环，一个无环  -->一定不相交
            return null;
        }

        int length1 = getLength(list1, enterNode1);
        // 当两个链表有公共的环，但是入口点不一样，这样求就会有问题， 因为带环的链表求长度是 根据入口点是否重复
        int length2 = getLength(list2, enterNode2);

        ListNode fast = list1, slow = list2;
        if (length1 < length2) {
            fast = list2;
            slow = list1;
        }
        int step = Math.abs(length1 - length2);
        while (step > 0) {
            fast = fast.next;
            step--;
        }

        while (fast != null && slow != null) {
            if (fast == slow) return fast;  // 必须放在前面，防止 A:1->2  B: 2
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * 计算链表的长度，可能有环，enterNode!=null 表示有环
     *
     * @param node
     * @param enterNode
     * @return
     */
    int getLength(ListNode node, ListNode enterNode) {

        if (enterNode == null) {
            int count = 0;
            while (node != null) {
                count++;
                node = node.next;
            }
            return count;
        } else {
            // 带环的链表计算长度，先计算环的节点总数
            int count = 1;
            ListNode t = enterNode;
            while (t.next != enterNode) {
                t = t.next;
                count++;
            }

            while (node != enterNode) {
                node = node.next;
                count++;
            }
            return count;
        }

    }

    /**
     * 判断链表是否有环,有的话，返回环的入口节点,没有，返回null
     *
     * @return
     */
    public ListNode hasLoop(ListNode head) {

        ListNode fast = head, slow = head;

        boolean hasLoop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return null;

        // 求入口节点
        // 首先求环的长度,fast和 slow 一定是在环中相遇

        int count = 1;
        while (slow.next != fast) {
            slow = slow.next;
            count++;
        }

        // 快指针从头结点先走 count 步（环的长度）
        slow = fast = head;
        while (count > 0) {
            fast = fast.next;
            count--;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow 和 fast 相遇时就是入口节点
        return slow;
    }


}
