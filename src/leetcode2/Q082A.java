package leetcode2;

import leetcode.ListNode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q082A {
    public ListNode deleteDuplicates(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode cur = pHead;
        ListNode prev = null, follow;
        pHead = null;
        while (cur != null) {
            // 判断 cur 是否是重复节点
            follow = cur.next;
            while (follow != null && follow.val == cur.val) {
                follow = follow.next;
            }
            if (cur.next == follow) {
                // cur 不是重复节点
                if (pHead == null) {
                    pHead = cur;
                } else {
                    prev.next = cur;
                }
                prev = cur;

            }
            cur = follow;
        }
        if(prev!=null){
            prev.next = null;
        }


        return pHead;

    }
}
