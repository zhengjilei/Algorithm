package programmer_interview;

import org.junit.Test;

/**
 * 在单链表和双链表中删除倒数第 k 个节点
 * created by Ethan-Walker on 2018/12/20
 */
public class Q012_DeleteLastKth {


    // 考虑边界条件 删除节点是链表头、中、尾

    /**
     * 单链表中删除倒数第 k 个节点
     * 返回链表头
     * <p>
     * 思路：找到倒数第 k+1 个顶点
     *
     * @param node
     */
    public ListNode deleteLastKthInSingleList(ListNode node, int k) {

        if (k <= 0) throw new RuntimeException("k 不能<=0");
        if (node == null) return null;

        ListNode fast = node, slow = node;
        int step = 0;

        // 找到倒数第 k+1 个节点
        while (step < k + 1 && fast != null) {
            fast = fast.next;
            step++;
        }
        // step !=k+1  说明找不到倒数第 k+1 个节点

        if (step == k) {        // 只能找到倒数第 k 个节点，说明要删除链表头元素
            return node.next;
        } else if (step < k) {// 说明要删除的元素超过了 链表长度
            throw new RuntimeException("没有倒数第" + k + "个节点");
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow 指向倒数第 k+1 个节点
        slow.next = slow.next.next;
        return node;

    }

    /**
     * 双向链表
     * <p>
     * 思路：由于是双向，找到倒数第 k 个顶点即可
     *
     * @param node
     * @param k
     * @return
     */
    public DualListNode deleteLastKthInDualList(DualListNode node, int k) {
        if (k <= 0) throw new RuntimeException("k 不能<=0");
        if (node == null) return null;

        DualListNode fast = node, slow = node;
        int step = 0;
        while (fast != null && step < k) {
            step++;
            fast = fast.next;
        }
        if (step < k) {
            // 找不到倒数第 k 个节点
            throw new RuntimeException("没有倒数第" + k + "个节点");
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow 指向倒数第 k 个节点
        DualListNode prev = slow.prev, next = slow.next;
        if (prev == null) { // 删除的是链表头结点
            return next;
        } else if (next == null) { // 删除的是链表尾节点
            prev.next = null;
        } else { // 删除的是中间节点
            prev.next = next;
            next.prev = prev;
        }
        return node;
    }

    public void print(ListNode node) {
        if (node == null) {
            System.out.println("链表为空");
            return;
        }
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }

    public void print(DualListNode node){
        if (node == null) {
            System.out.println("链表为空");
            return;
        }
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }


    @Test
    public void testSingle() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        ListNode single = deleteLastKthInSingleList(a, 5);
        print(single);

    }    @Test
    public void testDual() {
        DualListNode a = new DualListNode(1);
        DualListNode b = new DualListNode(2);
        DualListNode c = new DualListNode(3);
        DualListNode d = new DualListNode(4);
        DualListNode e = new DualListNode(5);

        a.next = b; b.prev = a;
//        b.next = c; c.prev = b;
//        c.next = d; d.prev = c;
//        d.next = e; e.prev = d;

        DualListNode dual = deleteLastKthInDualList(a,2);
        print(dual);

    }
}
