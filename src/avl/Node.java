package avl;

/**
 * created by Ethan-Walker on 2018/11/8
 */
public class Node {

    int bf = 0; //balance factor平衡因子，左子树的高度-右子树的高度 ,要求绝对值<=1
    int val;
    Node left, right;

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }
}
