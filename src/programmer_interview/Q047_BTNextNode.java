package programmer_interview;

/**
 * 中序遍历下某个节点的后继结点
 * <p>
 * 给出的条件：每个节点有父指针
 * created by Ethan-Walker on 2018/12/30
 */
public class Q047_BTNextNode {


    public TreeNode getNextNode(TreeNode node) {

        if (node == null) return null;

        if (node.right != null) {
            // 右子树不为空，返回右子节点的最左下角顶点
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else if (node.parent != null) {
            // 父节点不为空，向上追溯找到某个节点是 父节点的左子树，下一个节点就是该父节点

            TreeNode parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = parent.parent;
            }
            return parent; // parent == null 表示当前节点是最后一个节点，next 也是 null
        }
        return null;
    }
}
