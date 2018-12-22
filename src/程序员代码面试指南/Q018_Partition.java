package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 根据给定的值，将链表进行左右划分
 * created by Ethan-Walker on 2018/12/22
 */
public class Q018_Partition {


    /**
     * 遍历节点，存储到数节点列表集合中，对集合中的数据进行划分，然后再将数据复制回去
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * <p>
     * 缺点：空间复杂度。原链表的各个部分内的顺序会被改变
     *
     * @param head
     * @param key
     * @return
     */
    public ListNode partition(ListNode head, int key) {
        if (head == null || head.next == null) return head;
        ListNode node = head;
        ArrayList<ListNode> list = new ArrayList<>();
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int length = list.size();

        int index = 0;
        int smallIndex = 0;
        int bigIndex = length - 1;

        while (index <= bigIndex) {
            if (list.get(index).val < key) {
                swap(list, smallIndex++, index++);
            } else if (list.get(index).val == key) {
                index++;
            } else {
                swap(list, bigIndex--, index);
            }
        }

        for (int i = 0; i < list.size() - 1; i++) {
            ListNode a = list.get(i);
            a.next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;

        return list.get(0);
    }

    public void swap(ArrayList<ListNode> list, int i, int j) {
        ListNode node = list.get(i);
        list.set(i, list.get(j));
        list.set(j, node);
    }


    /**
     * 要求空间复杂度: O(1) 且原链表中各部分的顺序不会被改变
     * 比如 1 9 8 4 4 7 6 key = 6
     * 划分后应该得到 1 4 4 6 9 8 7  （9/8/7 保证和原先的顺序相同）
     * 而不能是      1 4 4 6 7 9 8
     *
     * @param head
     * @param key
     * @return
     */
    public ListNode partition2(ListNode head, int key) {

        if (head == null || head.next == null) return head;
        ListNode smallHead = null, smallTail = null;
        ListNode equalHead = null, equalTail = null;
        ListNode bigHead = null, bigTail = null;

        ListNode node = head, next = null;
        while (node != null) {
            next = node.next;
            node.next = null; // 断掉当前节点和后面节点的关系，以免最后形成的链表有环

            if (node.val < key) {
                if (smallHead == null) {
                    smallHead = node;
                    smallTail = node;
                } else {
                    smallTail.next = node;
                    smallTail = node;
                }
            } else if (node.val == key) {
                if (equalHead == null) {
                    equalHead = node;
                    equalTail = node;
                } else {
                    equalTail.next = node;
                    equalTail = node;
                }
            } else {
                if (bigHead == null) {
                    bigHead = node;
                    bigTail = node;
                } else {
                    bigTail.next = node;
                    bigTail = node;
                }
            }
            node = next;
        }

        if (smallHead != null) {
            head = smallHead;
            if (equalHead != null) {
                smallTail.next = equalHead;
                // bigHead 不必判断是否为空
                equalTail.next = bigHead;

            } else {
                smallTail.next = bigHead;
            }
        } else if (equalHead != null) {
            head = equalHead;
            equalTail.next = bigHead;
        } else {
            head = bigHead;
        }

        return head;
    }

    @Test
    public void test() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(9);
        ListNode a3 = new ListNode(8);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(4);
        ListNode a6 = new ListNode(7);
        ListNode a7 = new ListNode(6);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;

        ListUtil.print(a1);
        ListNode partition = partition2(a1, 6);
        ListUtil.print(partition);
    }


}
