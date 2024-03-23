package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q0109_SortedListToBST {

    // 方法一: 先将链表的值存在 数组/数组列表中，随机访问中间节点容易
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return toBST(list, 0, list.size() - 1);
    }

    public TreeNode toBST(List<Integer> list, int left, int right) {
        if (left > right) return null;

        int mid = left + right >> 1;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = toBST(list, left, mid - 1);
        root.right = toBST(list, mid + 1, right);
        return root;
    }

    // 方法二：直接在链表中找 中间节点
    public TreeNode sortedListToBST2(ListNode head) {

        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        // >= 两个, 结束时 slow 指向中间两个的后一个
        ListNode fast = head, slow = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        // prev 不可能为空
        prev.next = null;

        TreeNode t = new TreeNode(slow.val);
        t.left = sortedListToBST2(head);
        t.right = sortedListToBST2(slow.next);
        return t;
    }


}
