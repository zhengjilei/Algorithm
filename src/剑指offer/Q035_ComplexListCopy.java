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
     * @param sourceHead
     * @return
     */
    public RandomListNode copy(RandomListNode sourceHead) {
        if (sourceHead == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode destHead = new RandomListNode(sourceHead.label);

        RandomListNode destNode = destHead;
        map.put(sourceHead, destHead);

        RandomListNode sourceNode = sourceHead.next;
        while (sourceNode != null) {
            destNode.next = new RandomListNode(sourceNode.label);
            map.put(sourceNode, destNode.next);
            destNode = destNode.next;
            sourceNode = sourceNode.next;
        }

        destNode.next = null;

        sourceNode = sourceHead;
        destNode = destHead;

        while (sourceNode != null) {
/*
            if (sourceNode.random != null) { // 不能加这个判断，出错 ，why?
                destNode.random = map.get(sourceNode.random);
            }
*/
            destNode.random = map.get(sourceNode.random);
            sourceNode = sourceNode.next;
            destNode = destNode.next;
        }
        return destHead;

    }

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
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
