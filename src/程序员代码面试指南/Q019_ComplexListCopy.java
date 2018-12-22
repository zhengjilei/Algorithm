package 程序员代码面试指南;

import org.junit.Test;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2018/12/23
 */
public class Q019_ComplexListCopy {


    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param head
     * @return
     */
    public RandomListNode copy(RandomListNode head) {
        if (head == null) return head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode sourceNode = head;
        while (sourceNode != null) {
            map.put(sourceNode, new RandomListNode(sourceNode.label));
            sourceNode = sourceNode.next;
        }

        sourceNode = head;
        while (sourceNode != null) {
            map.get(sourceNode).random = map.get(sourceNode.random);
            map.get(sourceNode).next = map.get(sourceNode.next);
            sourceNode = sourceNode.next;
        }

        return map.get(head);
    }


    /**
     *
     * @param head
     * @return
     */
    public RandomListNode copy2(RandomListNode head) {

        if (head == null) return null;

        // 复制，将每个复制的节点插入原节点后面
        RandomListNode p = head;
        while (p != null) {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            p.next = node;

            p = node.next;
        }

        p = head;
        // 每个 p.next 的random 对应于 p.random.next
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        // 断开所有奇数节点和偶数节点，分别各为一个链表
        p = head;
        RandomListNode secondHead = p.next, q;
        while (p != null) {
            q = p.next;
            p.next = q.next;
            if (q.next != null) {   // q.next 可能为空，避免空指针
                q.next = q.next.next;
            }
            p = p.next;
            q = q.next;
        }

        return secondHead;
    }

    @Test
    public void test() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(null, "dsads");
        System.out.println(map.get(null));
    }


    @Test
    public void test2() {
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        RandomListNode d = new RandomListNode(4);
        RandomListNode e = new RandomListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        a.random = c;
        b.random = e;
        d.random = b;

        RandomListNode head = copy(a);

        int i = 0;
    }

    class RandomListNode {
        int label;
        RandomListNode next;
        RandomListNode random;

        public RandomListNode(int val) {
            this.label = val;
        }
    }
}
