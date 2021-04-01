package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 判断单链表是否是回文结构
 * 1->2->3->2->1
 * created by Ethan-Walker on 2018/12/22
 */
public class Q017_Palindrome {

    /**
     * 遍历一遍，将链表中的值存到辅助数组中,然后比较数组是否是回文结构
     * for i< length /2
     * a[i] == a[length-i-1]
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度: O(n)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int length = list.size();

        for (int i = 0; i < length / 2; i++) {
            if (list.get(i) != list.get(length - i - 1)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 思考：回文结构就是正着 反着都是一样，正、反可以联想到用栈
     * 遍历一遍存到栈中
     * 第二遍遍历时，依次与栈顶元素比较
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param node
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) return false;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
        }


        while (head != null) {
            if (head.val != stack.pop()) return false;
            head = head.next;
        }
        return true;
    }

    /**
     * 也是用栈，但只用存一半的数据即可
     * 1 2 3 4 3 2 1  只要依次存 3 2 1 即可 ,弹出和链表头开始依次比较
     * 1 2 3 4 4 3 2 1  只要依次存 4 3 2 1 即可, 弹出和链表头开始依次比较
     * <p>
     * 首先找到中间节点（偶数个节点时，一定要找到中间两个节点的后一个,故fast slow 初始指向同一个节点）
     * <p>
     * 时间复杂度:O(n)
     * 空间复杂度:O(n) n/2
     *
     * @param head
     * @return
     */
    public boolean isPalindrome3(ListNode head) {

        if (head == null) return false;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != head.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 反转右半部分链表，然后比较完恢复
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome4(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow 指向中间节点（偶数的话，指向中间两个的第二个）
        // 从节点 slow 开始反转
        ListNode prev = null, cur = slow, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // prev 指向原先链表的尾结点

        ListNode tail = prev;

        boolean flag = true;
        // 只要有一个节点为 null 说明已经比较结束
        while (tail != null && head != null) {
            if (tail.val != head.val) {
                flag = false;
                break;
            }
            head = head.next;
            tail = tail = next;
        }
        //（不论是否满足回文结构） 恢复链表,从 tail 节点开始
        tail = prev;
        prev = null;
        next = null;
        while (tail != null) {
            next = tail.next;
            tail.next = prev;
            prev = tail;
            tail = next;
        }


        return flag;

    }

    @Test
    public void test() {

    }
}
