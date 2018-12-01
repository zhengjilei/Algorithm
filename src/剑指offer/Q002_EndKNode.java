package 剑指offer;

import org.testng.annotations.Test;

/**
 * 倒数第 k 个节点
 * 初始前后指针都指向第一个节点
 * created by Ethan-Walker on 2018/12/1
 */
public class Q002_EndKNode {

    /**
     * 序号从 1 开始编号，最后一个节点为倒数第 1 个节点， 后指针前进 k步
     * 当后指针 q 指向null，说明前指针 p 正指向倒数第 k 个节点
     * 边界条件：
     * 1. head == null
     * 3. k = 0
     * 2. 总数小于 k 个
     *
     * @param head
     * @param k
     * @return
     */
    Node findKthToTailNode(Node head, int k) {
        if (head == null || k <= 0) return null;
        Node p = head, q = head;
        int i = 0;
        while (i < k && q != null) { // q 向前走 k 步
            q = q.next;
            i++;
        }
        if (i < k) return null; // 总数小于 k 个
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;

        }
    }


}
