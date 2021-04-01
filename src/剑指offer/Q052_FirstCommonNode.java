package 剑指offer;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * 两个链表的第一个公共节点
 * created by Ethan-Walker on 2018/12/13
 */
public class Q052_FirstCommonNode {


    /**
     * 蛮力法
     * 时间复杂度: O(m*n)
     * 空间复杂度: O(1)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode simpleSolve(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return null;

        ListNode pNode = list1;
        ListNode qNode = list2;
        while (pNode != null) {
            qNode = list2;
            while (qNode != null) {
                if (pNode == qNode) {
                    return qNode;
                }
                qNode = qNode.next;
            }

            pNode = pNode.next;
        }
        return null;
    }

    /**
     * hash 存储
     * 时间复杂度: O(m+n)
     * 空间复杂度: O(m+n)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode getFirstCommonNode(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return null;
        HashMap<ListNode, Boolean> hashMap = new HashMap<>();
        ListNode pNode = list1, qNode = list2;

        while (pNode != null) {
            hashMap.put(pNode, true);
            pNode = pNode.next;
        }
        while (qNode != null) {
            if (hashMap.containsKey(qNode)) {
                return qNode;
            }
            qNode = qNode.next;
        }
        return null;

    }

    /**
     * 通过双栈解决
     * 时间复杂度: O(m+n)
     * 空间复杂度: O(m+n)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode getFirstCommonNodeByStack(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return null;

        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();

        ListNode pNode = list1, qNode = list2;
        while (pNode != null) {
            stack1.push(pNode);
            pNode = pNode.next;
        }
        while (qNode != null) {
            stack2.push(qNode);
            qNode = qNode.next;
        }
        ListNode prev = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();

            if (node1 == node2) {
                prev = node1;
            } else {
                break;
            }
        }
        if (prev != null) {
            return prev;
        }
        return null;
    }

    /**
     * 计算长度，间隔双指针法
     * 时间复杂度: O(m+n)
     * 空间复杂度: O(1)
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode getFirstCommonNodeByLength(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return null;
        ListNode pNode = list1, qNode = list2;
        int pLength = 0, qLength = 0;
        while (pNode != null) {
            pLength++;
            pNode = pNode.next;
        }

        while (qNode != null) {
            qLength++;
            qNode = qNode.next;
        }
        ListNode fastNode = list1, slowNode = list2;
        if (qLength > pLength) {
            fastNode = list2;
            slowNode = list1;
        }
        int l = Math.abs(pLength - qLength);
        while (l > 0) {
            fastNode = fastNode.next;
            l--;
        }
        while (fastNode != null && slowNode != null) {
            if (fastNode == slowNode) { // 必须放在前面 考虑链表 A:3->2 B：2 在 2点相交的情况
                return fastNode;
            }
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return null;
    }
}
