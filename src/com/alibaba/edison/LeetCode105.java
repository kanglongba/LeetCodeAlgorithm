package com.alibaba.edison;

/**
 * 从前序与中序遍历序列构造二叉树，medium
 * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 很经典的一道二叉树题目
 *
 * author: qonyqian
 * created on: 2022/2/9 3:07 下午
 * version：1.0
 * description:
 */
public class LeetCode105 {

    /**
     * 稍微分析了下先序遍历和中序遍历的特点，这道题应该是递归。
     * <p>
     * 1.先序遍历，第一个是根节点，在中序遍历中可以靠他切分左右子树
     * 2.递归处理左右子树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode head = new TreeNode();
        recursion(head, preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return head;
    }

    /**
     * 递归处理左右子树。有一点需要情况，子树在先序遍历数组和中序遍历数组中都是连续的。
     *
     * @param node     子树的根节点
     * @param preorder 先序遍历数组
     * @param inorder  中序遍历数组
     * @param preLeft  子树在先序遍历数组中的左边界
     * @param preRight 子树在先序遍历数组中的右边界
     * @param inLeft   子树在中序遍历数组中的左边界
     * @param inRight  子树在中序遍历数组中的右边界
     */
    public void recursion(TreeNode node, int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) { // 下标非法，直接返回。像这种返回条件，不用去推断是否真实存在。只要涉及到
            return;                                   // 数组，上去就写个越界检查。
        }                                             // 实际上，本算法不加这个越界检查也是对的。
        node.val = preorder[preLeft]; //把根节点挂到树上。根节点一定是存在的，根节点的左子树和右子树不一定存在。
        if (preLeft == preRight || inLeft == inRight) { // 如果左边界=右边界，说明只有根节点自己，根节点没有左子树和右子树。
            return;                                     // 实际上，这两个条件，只判断一个就行
        }
        int head = preorder[preLeft];  // 先序遍历的左边界一定是根节点
        int pos = findNode(head, inorder); // 查找根节点在中序遍历中的位置，用来切分左子树和右子树。一定能找到根节点，永不会返回-1
        int leftChildTreeLength = pos - inLeft; // 在中序遍历中切分左子树
        int rightChildTreeLength = inRight - pos; // 在中序遍历中切分右子树

        if (leftChildTreeLength != 0) { //用长度判断左子树是否为空
            node.left = new TreeNode();
            recursion(node.left, preorder, inorder, preLeft + 1, preLeft + leftChildTreeLength, inLeft, pos - 1);
        }
        if (rightChildTreeLength != 0) { //用长度判断右子树是否为空
            node.right = new TreeNode();
            recursion(node.right, preorder, inorder, preLeft + leftChildTreeLength + 1, preRight, pos + 1, pos + rightChildTreeLength);
        }
    }

    /**
     * 这个查找数组可以优化一下，不从头开始遍历，而是在 (inLeft, inRight) 区间内遍历
     *
     * @param element
     * @param inorder
     * @return
     */
    public int findNode(int element, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == element) {
                return i;
            }
        }
        return -1;
    }
}
