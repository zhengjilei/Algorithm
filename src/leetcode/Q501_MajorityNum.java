package leetcode;

import programmer_interview.TreeNode;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q501_MajorityNum {

    public int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        inOrder(root, map);
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        List<Integer> res = new ArrayList<>();
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }

        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == maxCount) {
                res.add(entry.getKey());
            }
        }
        int[] a = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            a[i] = res.get(i);
        }
        return a;
    }

    public void inOrder(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) return;
        inOrder(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 1)+1);
        inOrder(root.right, map);
    }
}
