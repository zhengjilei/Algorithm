package programmer_interview;

/**
 * created by Ethan-Walker on 2018/12/22
 */
public class ListUtil {


    public static void print(ListNode node) {
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

}
