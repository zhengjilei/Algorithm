package a_review.huffman;

import org.junit.Test;
import programmer_interview.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class Huffman {

    TreeNode root;

    public void buildHuffmanTree(int[] val) {
        PriorityQueue<TreeNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(TreeNode::getVal));
        for (int i = 0; i < val.length; i++) {
            minHeap.add(new TreeNode(val[i]));
        }

        TreeNode a, b, c;
        while (minHeap.size() > 1) { // >=两个节点，需要继续组装
            a = minHeap.poll();
            b = minHeap.poll();
            c = new TreeNode(a.val + b.val);
            c.left = a;
            c.right = b;
            minHeap.add(c);
        }

        root = minHeap.poll();

    }


    public int wpl() {
        return calTotalWeight(root, 0);
    }

    public int calTotalWeight(TreeNode node, int level) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return node.val * level;
        } else {
            return calTotalWeight(node.left, level + 1) + calTotalWeight(node.right, level + 1);
        }
    }


    @Test
    public void test() {
        buildHuffmanTree(new int[]{2, 7, 4, 5, 3, 11});
        System.out.println(wpl());
    }
}
