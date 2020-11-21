package com.alibaba.edison;

import java.util.Stack;

/**
 * Created by 钧戈 on 2020/9/17.
 */
public class Sword27 {

    public TreeNode mirrorTree(TreeNode root) {
        return digui(root);
    }

    //1.递归（DFS）
    private TreeNode digui(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        digui(root.left);
        digui(root.right);
        return root;
    }

    //2.辅助栈/队列（BFS）
    private TreeNode stack(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode temp = stack.pop();
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
            TreeNode node = temp.left;
            temp.left = temp.right;
            temp.right = node;
        }
        return root;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
