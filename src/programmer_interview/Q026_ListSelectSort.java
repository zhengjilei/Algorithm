package programmer_interview;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class Q026_ListSelectSort {


    /**
     * 选择排序
     *
     * @param head
     * @return
     */
    public ListNode selectSortLL(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode first = head, second;
        ListNode minNode;
        while (first.next != null) {
            second = first.next;
            minNode = first;

            while (second != null) {
                if (second.val < minNode.val) {
                    minNode = second;
                }
                second = second.next;
            }
            int t = minNode.val;
            minNode.val = first.val;
            first.val = t;

            first = first.next;
        }
        return head;
    }


    public void selectSort(int[] arr) {
        int length = arr.length;
        int minIndex = 0;

        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(int[] arr, int i, int minIndex) {
        int t = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = t;
    }

}
