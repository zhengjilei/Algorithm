package 程序员代码面试指南;

/**
 * 删除链表中的节点node ，只给定待删除节点 node，不给链表头结点
 * <p>
 * created by Ethan-Walker on 2018/12/25
 */
public class Q027_DeleteNode {


    public void deleteNode(ListNode node) {
        if (node == null) return;
        if (node.next == null) {
            // 删除的是链表尾节点，Java无法实现，C/C++ 传节点地址可以改变
            throw new RuntimeException("can't remove the last node");
        }
        // 将后一个节点的值赋值给当前节点，然后删除后一个节点
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
