package com.alibaba.edison;

import java.util.ArrayList;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword28 {

    public boolean isSymmetric(TreeNode root) {
        //return check1(root, root);
        return check(root);
    }

    /**
     * 题解
     * @param p
     * @param q
     * @return
     */
    public boolean check1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check1(p.left, q.right) && check1(p.right, q.left);
    }

    /**
     * 错误了，从思路上就错了。
     * 二刷 2020-10-31 16:40:45
     * 利用中序遍历
     * @param root
     * @return
     */
    private boolean check(TreeNode root) {
        inOrder(root);
        if (list.size() == 0) {
            return true;
        }
        int mid = list.size()/2;
        boolean res = true;
        int start = 0;
        int end = list.size()-1;
        while (start != mid && end != mid) {
            if (list.get(start).val != list.get(end).val) {
                res =false;
                break;
            }
            start++;
            end--;
        }
        return res && start == end;
    }

    private ArrayList<TreeNode> list = new ArrayList<>();
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root);
        inOrder(root.right);
    }

    /**
     * 三刷 2020-11-15 22:23:20
     * @param root
     * @return
     */
    private boolean check2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return digui(root.left, root.right);
    }

    private boolean digui(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        } else if (p1 == null) {
            return false;
        } else if (p2 == null) {
            return false;
        } else if (p1.val != p2.val) {
            return false;
        }
        return digui(p1.left, p2.right) && digui(p1.right, p2.left);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
