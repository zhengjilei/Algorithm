package huffman;


import org.junit.Test;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class HuffmanCode {


    HashMap<Character, String> res;  // 存储  <字符, 01序列编码>

    TreeNode root;

    public void buildHuffmanTree(char[] chs, double[] prob) {
        PriorityQueue<TreeNode> minHeap = new PriorityQueue<>(Comparator.comparingDouble(TreeNode::getVal));

        TreeNode t;

        for (int i = 0, len = chs.length; i < len; i++) {
            t = new TreeNode(prob[i], chs[i]);
            minHeap.add(t);
        }

        TreeNode a, b;
        while (minHeap.size() > 1) {
            a = minHeap.poll();
            b = minHeap.poll();
            t = new TreeNode(a.val + b.val);
            t.left = a;
            t.right = b;
            minHeap.add(t);
        }

        root = minHeap.poll();
    }

    public void initCode() {
        res = new HashMap<>();
        buildCode(root, new ArrayList<>(), 0);
    }

    private void buildCode(TreeNode node, List<Byte> seq, int index) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < index; i++) {
                sb.append(seq.get(i));
            }
            res.put(node.c, sb.toString());
            return;
        }
        if (node.left != null) {
            if (seq.size() > index) {
                seq.set(index, (byte) 0);
            } else {
                seq.add((byte) 0);
            }
            buildCode(node.left, seq, index + 1);
        }
        if (node.right != null) {
            if (seq.size() > index) {
                seq.set(index, (byte) 1);
            } else {
                seq.add(index, (byte) 1);
            }
            buildCode(node.right, seq, index + 1);

        }
    }


    public double wpl() {
        return cal(root, 0);
    }

    public double cal(TreeNode node, int index) {
        if (node == null) return 0.0;
        if (node.left == null && node.right == null) {
            return node.val * index;
        } else {
            return cal(node.left, index + 1) + cal(node.right, index + 1);
        }
    }

    class TreeNode {
        char c; // 只有叶节点有字符
        double val;
        TreeNode left, right;

        public TreeNode(double val) {
            this.val = val;
        }

        public TreeNode(double val, char ch) {
            this.val = val;
            this.c = ch;
        }

        public double getVal() {
            return val;
        }
    }

    @Test
    public void test() {
        HuffmanCode hc = new HuffmanCode();
        char[] chs = new char[]{'a', 'b', 'c', 'd', 'e'};
        double[] prob = new double[]{0.12, 0.40, 0.15, 0.08, 0.25};
        hc.buildHuffmanTree(chs, prob);
        hc.initCode();
        Set<Map.Entry<Character, String>> entries = hc.res.entrySet();
        for (Map.Entry<Character, String> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("--------------");
        double wpl = hc.wpl();
        System.out.println("wpl: " + wpl);
    }

}
