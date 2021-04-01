package linkedlist;

import org.junit.Test;

/**
 * Created by Ethan-Walker on 2018/4/2.
 */
public class LinkedListTest {
    @Test
    public void testInteger() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.reverseLinkedList();
        linkedList.print();

        linkedList.insert(1);
        linkedList.reverseLinkedList();
        linkedList.print();

        linkedList.insert(323);
        linkedList.insert(435);
        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.insert(48);
        linkedList.insert(128);
        linkedList.print();
        System.out.print("链表反向输出: ");
        linkedList.reversePrint();

        linkedList.reverseLinkedList();
        linkedList.print();

        MyLinkedList.Node endK = linkedList.getEndK(2);
        System.out.println(endK.val);

        linkedList.printMidNode();
        linkedList.query(435);
        linkedList.query(43);

        linkedList.update(435, 434);
        linkedList.print();

        linkedList.reverseLinkedList();
        linkedList.print();

        System.out.println(linkedList.hasLoop());
    }

    @Test
    public void testEndK() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(323);
        linkedList.insert(435);
        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.insert(48);
        linkedList.insert(128);

        System.out.println(linkedList.getEndK(0));
        System.out.println(linkedList.getEndK(1));
        System.out.println(linkedList.getEndK(4));
        System.out.println(linkedList.getEndK(6));
        System.out.println(linkedList.getEndK(7));
        System.out.println(linkedList.getEndK(8));

        System.out.println();
        System.out.println(linkedList.getEndK2(0));
        System.out.println(linkedList.getEndK2(1));
        System.out.println(linkedList.getEndK2(4));
        System.out.println(linkedList.getEndK2(6));
        System.out.println(linkedList.getEndK2(7));
        System.out.println(linkedList.getEndK2(8));


    }

    @Test
    public void testReversePrint() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.reversePrint();
        linkedList.reversePrint2();

        linkedList.insert(48);
        linkedList.insert(128);
        linkedList.reversePrint();
        linkedList.reversePrint2();

        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.reversePrint();
        linkedList.reversePrint2();
    }


    @Test
    public void testDeleteSpecNode() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(323);
        linkedList.insert(435);
        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.insert(48);
        linkedList.insert(128);

        linkedList.print();
        MyLinkedList.Node node = linkedList.findNode(323);
        MyLinkedList.Node node1 = linkedList.findNode(435);
        MyLinkedList.Node node2 = linkedList.findNode(45);
        MyLinkedList.Node node3 = linkedList.findNode(128);
        linkedList.deleteSpecNode(node3);
        linkedList.print();
    }

    @Test
    public void testDeleteDupNode() {
        MyLinkedList linkedList = new MyLinkedList();

        linkedList.insert(25);
        linkedList.insert(25);
        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.insert(45);
        linkedList.insert(48);
        linkedList.insert(128);
        linkedList.insert(128);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(435);
        linkedList.insert(435);
        linkedList.print();
        linkedList.deleteDupNode();
        linkedList.print();
    }

    @Test
    public void testDeleteAllDupNode() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(25);
        linkedList.insert(25);
        linkedList.insert(25);
        linkedList.insert(45);
        linkedList.insert(45);
//        linkedList.insert(48);
        linkedList.insert(128);
        linkedList.insert(128);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(323);
        linkedList.insert(435);
        linkedList.insert(435);

        linkedList.print();
        linkedList.deleteAllDupNode();
        linkedList.print();
    }

    @Test
    public void testUnionSortedList() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(5);
        linkedList.insert(11);
        linkedList.insert(18);
        linkedList.insert(18);
        linkedList.insert(25);

        MyLinkedList linkedList2 = new MyLinkedList();
        linkedList2.insert(2);
        linkedList2.insert(3);
        linkedList2.insert(14);
        linkedList2.insert(23);
        linkedList2.insert(29);
        
        MyLinkedList list3 = new MyLinkedList();
        list3.setHead(list3.union(linkedList.getHead(), linkedList2.getHead()));
        list3.print();
    }
}
