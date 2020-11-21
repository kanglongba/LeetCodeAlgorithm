package com.alibaba.edison;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 钧戈 on 2020/10/16.
 */
public class Sword34 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    /**
     * 深度优先遍历 递归
     *
     * @param root
     * @param sum
     * @return
     */
    private LinkedList<Integer> path = new LinkedList<>();
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> dfs(TreeNode root, int sum) {
        TreeNode temp = root;
        path.add(root.val);
        digui(temp.left, sum - root.val);
        digui(temp.right, sum - root.val);
        return res;
    }

    private void digui(TreeNode root, int num) {
        if (root == null) {
            return;
        } else {
            path.add(root.val);
            num -= root.val;
            if (num == 0 && root.left == null && root.right == null) {
                res.add(new LinkedList<>(path));
            } else {
                digui(root.left, num);
                digui(root.right, num);
            }
            path.pollLast();
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    /**
     * 二刷 2020-11-01 23:02:38
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> ershua(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        dfs2(root, sum);
        return res2;
    }

    List<List<Integer>> res2 = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    private void dfs2(TreeNode root, int sum) {
        list.add(root.val);
        if (root.left == null && root.right == null) {
            //叶子节点
            if (sum == root.val) {
                //路径正确
                ArrayList<Integer> copy = new ArrayList<>(list);
                res2.add(copy);
            }
        }
        if (root.left != null) {
            //左子树
            dfs2(root.left, sum-root.val);
        }
        if (root.right != null){
            //右子树
            dfs2(root.right, sum-root.val);
        }
        list.remove(list.size()-1);
    }

}
