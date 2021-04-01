package huffman;

/**
 * created by Ethan-Walker on 2018/11/6
 */
public class Huffman {
    HuffmanNode root;

    public void buildHuffman(double[] weight) {
        // 第一步，构造最小堆
        HuffmanMinHeap minHeap = new HuffmanMinHeap();
        HuffmanNode node;
        for (int i = 0; i < weight.length; i++) {
            node = new HuffmanNode(weight[i]);
            minHeap.insert(node);
        }
/*
        HuffmanNode[] heap = new HuffmanNode[weight.length];
        for (int i = 0; i < weight.length; i++) {
            heap[i] = new HuffmanNode(weight[i]);
        }
        HuffmanMinHeap minHeap = new HuffmanMinHeap(heap, weight.length);
*/
        minHeap.printHeap();

        // 第二步，构造 Huffman 树,循环 n-1 次 ，新生成n-1 个节点
        HuffmanNode a, b, c = null;
        for (int i = 0; i < weight.length - 1; i++) {
            a = minHeap.removeTop();
            b = minHeap.removeTop();
            c = new HuffmanNode(a.weight + b.weight);
            a.parent = c;
            b.parent = c;
            c.left = a;
            c.right = b;
            minHeap.insert(c);
        }
        root = c;
    }

    /**
     * 最小带权路径长度
     *
     * @return
     */
    public double wpl() {
        return cal(root, 0);
    }

    double cal(HuffmanNode node, int length) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return node.weight * length;
        } else {
            return cal(node.left, length + 1) + cal(node.right, length + 1);
        }
    }

    public void preOrder() {
        System.out.print("前序: ");
        preOrder(root);
        System.out.println();
    }

    public void inOrder() {
        System.out.print("中序: ");
        inOrder(root);
        System.out.println();
    }

    public void preOrder(HuffmanNode n) {
        if (n != null) {
            System.out.printf("%6.1f", n.weight);
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    public void inOrder(HuffmanNode n) {
        if (n != null) {
            inOrder(n.left);
            System.out.printf("%6.1f", n.weight);
            inOrder(n.right);
        }
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        huffman.buildHuffman(new double[]{2.0, 7.0, 4.0, 5.0});
        huffman.preOrder();
        huffman.inOrder();
        System.out.println(huffman.wpl());
    }
}
