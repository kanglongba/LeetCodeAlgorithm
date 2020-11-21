package com.alibaba.edison;

import java.util.*;

/**
 * Created by 钧戈 on 2020/10/16.
 */
public class Sword33_iii {

    public List<List<Integer>> levelOrder(TreeNode root) {
        return bfs(root);
    }

    /**
     * 广度优先遍历
     * 用一个双端队列
     * @param root
     * @return
     */
    private List<List<Integer>> bfs2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        int level = 1;
        deque.offer(root);
        while (!deque.isEmpty()) {
            //答案中还有一种解法很巧妙，它把list用双端队列表示
            List<Integer> list = new ArrayList<>();
            for (int i = 0 , j=deque.size(); i < j; i++) {
                TreeNode node;
                if (level % 2 == 1) {
                    node =  deque.pollFirst();
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                } else {
                    node = deque.pollLast();
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
                list.add(node.val);
            }
            level++;
            res.add(list);
        }
        return res;
    }

    /**
     * 广度优先遍历
     * 用两个栈
     * @param root
     * @return
     */
    private List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //奇数层用正向栈
        Stack<TreeNode> zhengStack = new Stack<>();
        //偶数层用反向栈
        LinkedList<TreeNode> fanStack = new LinkedList<>();
        int level = 1;
        zhengStack.push(root);
        while (!zhengStack.isEmpty() || !fanStack.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (level % 2 == 1) {
                while (!zhengStack.isEmpty()) {
                    TreeNode node = zhengStack.pop();
                    list.add(node.val);
                    if (node.left != null) {
                        fanStack.push(node.left);
                    }
                    if (node.right != null) {
                        fanStack.push(node.right);
                    }
                }
                level++;
            } else {
                while (!fanStack.isEmpty()) {
                    TreeNode node = fanStack.pop();
                    list.add(node.val);
                    //先入栈右子树，再入栈左子树
                    if (node.right != null) {
                        zhengStack.push(node.right);
                    }
                    if (node.left != null) {
                        zhengStack.push(node.left);
                    }
                }
                level++;
            }
            res.add(list);
        }
        return res;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
