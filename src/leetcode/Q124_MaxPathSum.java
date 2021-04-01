package leetcode;

import org.junit.Test;
import programmer_interview.TreeNode;
import programmer_interview.TreeUtil;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class Q124_MaxPathSum {

    public int maxPathSum(TreeNode root) {
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        max(root,result);
        return result[0];
    }
    // 返回的是  左右子树其中一条经过root节点的最大路径和
    private int max(TreeNode root,int[] result){
        if(root == null){
            return 0;
        }
        int left = Math.max(0,max(root.left,result)); //负数置0，说明不走这个分支
        int right = Math.max(0,max(root.right,result));
        int res = Math.max(left,right) + root.val;  //res是左右子树其中一条经过root节点的最大路径和
        result[0] = Math.max(result[0],left + right + root.val); //result是以每个节点作为根节点时最大的路径和，也就是左子树最大路径和加上右子树最大路径和加上自身，子树路径和为负数则取0，表示不走子树
        return res;
    }


    @Test
    public void test() {
        TreeNode root = TreeUtil.buildTreeByPreAndIn(new int[]{-3}, new int[]{-3});
        System.out.println(maxPathSum(root));
    }
}
