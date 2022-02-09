package com.alibaba.edison;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历，medium
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 典型的bfs
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 11:05 下午
 * version：1.0
 * description:
 */
public class LeetCode102 {

    /**
     * bfs
     * 借助辅助队列
     *
     * 官方答案中，用一个指针代替了 two 的作用，降低了空间复杂度。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> one = new ArrayDeque<>(); //存储上一层节点
        Queue<TreeNode> two = new ArrayDeque<>(); //存储下一层节点
        one.offer(root); //初始状态，添加根节点
        List<Integer> temp = new ArrayList<>();
        while (!one.isEmpty() || !two.isEmpty()) { //如果两个队列都空了，说明已经遍历完树的所有节点
            if (!one.isEmpty()) { //one还有值，说明这一层还没遍历完，不能遍历下一层
                TreeNode node = one.poll();
                temp.add(node.val);
                if (node.left != null) { //把它的左节点添加到下一层
                    two.add(node.left);
                }
                if (node.right != null) { //把它的右节点添加到下一层
                    two.add(node.right);
                }
            } else { //one空了，说明这一层遍历完了，要开始遍历下一层
                result.add(temp); //把这一层的遍历添加到结果列表
                temp = new ArrayList<>();
                Queue queue = one; //交换one和two，在下一个循环开始遍历下一层
                one = two;
                two = queue;
            }
        }
        if (!temp.isEmpty()) { //叶子节点没有左右子树，所以最后一层的叶子节点在循环里没有来得及添加到结果列表，这里要给他补上
            result.add(temp);
        }
        return result;
    }

}
