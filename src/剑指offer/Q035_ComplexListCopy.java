package 剑指offer;


import org.junit.Test;

import java.util.HashMap;

/**
 * 复杂链表复制
 * created by Ethan-Walker on 2018/12/7
 */
public class Q035_ComplexListCopy {


    /**
     * 用 HashMap
     * node1 -> newNode1
     * node2 -> newNode2
     * 使得根据 node1.sibling 能快速O(1)确定 node2.sibling
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
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

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int val) {
            this.label = val;
        }
    }

    @Test
    public void test() {
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

}
