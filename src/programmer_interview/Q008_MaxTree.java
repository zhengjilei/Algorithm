package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/19
 */
public class Q008_MaxTree {


    public Node buildMaxTree(int[] arr) {

        int length = arr.length;
        int[] leftFirstMax = new int[length];  // 存储的是下标
        int[] rightFirstMax = new int[length];
        for (int i = 0; i < length; i++) {
            leftFirstMax[i] = -1;
            rightFirstMax[i] = -1;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 栈中从栈底到栈顶 从大到小 ，计算每个数左边第一个比其大的值，存储的是数值在数组中的下标
        for (int index = 0; index < length; index++) {
            while (!stack.isEmpty() && arr[index] >= arr[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                leftFirstMax[index] = stack.peek();
            }
            stack.push(index);
        }

        stack.clear();
        // 栈中从栈底到栈顶 顺序,从右往左遍历，计算每个数右边第一个比其大的值
        for (int index = length - 1; index >= 0; index--) {
            while (!stack.isEmpty() && arr[index] >= arr[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                rightFirstMax[index] = stack.peek();
            }
            stack.push(index);
        }

        Node[] nodes = new Node[length]; // 每个元素对应一个节点
        for (int i = 0; i < length; i++) {
            nodes[i] = new Node(arr[i]);
        }
        Node root = null;
        int minIndex = -1;
        for (int i = 0; i < length; i++) {
            if (leftFirstMax[i] != -1 && rightFirstMax[i] != -1) {
                minIndex = arr[leftFirstMax[i]] < arr[rightFirstMax[i]] ? leftFirstMax[i] : rightFirstMax[i];
            } else if (leftFirstMax[i] != -1) {
                minIndex = leftFirstMax[i];
            } else if (rightFirstMax[i] != -1) {
                minIndex = rightFirstMax[i];
            } else {
                root = nodes[i];
                continue;
            }
            // nodes[i] 的父亲指向 nodes[min]
            if (nodes[minIndex].left == null) {
                nodes[minIndex].left = nodes[i];
            } else {
                nodes[minIndex].right = nodes[i];
            }
        }
        return root;
    }


    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    @Test
    public void test() {
        Node node = buildMaxTree(new int[]{3, 4, 5, 1, 2});
        System.out.println();
    }
}
