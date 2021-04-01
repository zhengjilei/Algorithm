package bytedance;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/2/13
 */
public class Q160_IntersectionLL {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);

        ListNode fast = headA, slow = headB;
        if (lenB > lenA) {
            fast = headB;
            slow = headA;
        }

        int step = Math.abs(lenA - lenB);
        while (step > 0) {
            fast = fast.next;
            step--;
        }

        while (fast != null  && fast != slow) {
            fast= fast.next;
            slow = slow.next;
        }

        if(fast==null){  // 两链表无交点
            return null;
        }else{
            return fast;
        }
    }


    public int getLen(ListNode l) {
        int len = 0;
        while (l != null) {
            l = l.next;
            len++;
        }
        return len;
    }

}
