package 剑指offer;

import org.junit.Test;

import java.util.Random;

/**
 * created by Ethan-Walker on 2018/12/8
 */
public class Q035_ComplexListCopy2 {
    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }


    public RandomListNode copy(RandomListNode sourceHead) {
        if (sourceHead == null) return null;
        copyAndConnect(sourceHead);
        randomNodeExec(sourceHead);
        return getNewList(sourceHead);
    }

    /**
     * 将复制的每个新节点插入的原节点后面
     * <p>
     * 先不管 random 节点
     *
     * @param sourceHead
     * @return
     */
    public void copyAndConnect(RandomListNode sourceHead) {

        RandomListNode newNode, sourceNode = sourceHead;
        RandomListNode tmpNode = null;
        while (sourceNode != null) {
            newNode = new RandomListNode(sourceNode.label);

            tmpNode = sourceNode.next;
            newNode.next = tmpNode;
            sourceNode.next = newNode;

            sourceNode = tmpNode;
        }

    }

    /**
     * 统一处理 randomNode
     *
     * @param sourceHead
     */
    public void randomNodeExec(RandomListNode sourceHead) {
        RandomListNode destNode = null;
        RandomListNode sourceNode = sourceHead;
        while (sourceNode != null) {
            destNode = sourceNode.next;

            if (sourceNode.random != null) {
                destNode.random = sourceNode.random.next;
            }
            sourceNode = destNode.next;
        }
        return;
    }

    /**
     * 获取新链表
     * 断开称为两个链表
     *
     * @param sourceHead
     * @return
     */
    public RandomListNode getNewList(RandomListNode sourceHead) {
        RandomListNode sourceNode = sourceHead, destHead = sourceHead.next, destNode = null;

        RandomListNode tmpNode = null;
        while (sourceNode != null) {
            destNode = sourceNode.next;

            tmpNode = destNode.next;

            sourceNode.next = tmpNode;

            if (tmpNode != null) {
                destNode.next = tmpNode.next;
                destNode = tmpNode.next;
            } else {
                destNode.next = null;
            }
            sourceNode = tmpNode;

        }

        return destHead;
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

        RandomListNode result = copy(a);

    }
}
