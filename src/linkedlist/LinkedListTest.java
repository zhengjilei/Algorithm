package linkedlist;

import org.junit.Test;

/**
 * Created by lenovo on 2018/4/2.
 */
public class LinkedListTest {
    @Test
    public void testInteger(){
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
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

        linkedList.reverseLinkedList();
        linkedList.print();

        MyLinkedList<Integer>.Node<Integer> endK = linkedList.getEndK(2);
        System.out.println(endK.data);

        linkedList.printMidNode();
        linkedList.query(435);
        linkedList.query(43);

        linkedList.update(435,434);
        linkedList.print();

        linkedList.reverseLinkedList();
        linkedList.print();

        System.out.println(linkedList.hasLoop());
    }

    @Test
    public void testEndK(){
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
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
}
