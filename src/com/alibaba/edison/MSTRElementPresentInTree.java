package com.alibaba.edison;

/**
 * author: qonyqian
 * created on: 2022/3/10 4:55 下午
 * version：1.0
 * description:
 */
public class MSTRElementPresentInTree {

    private static class Node {
        Node left, right;
        int data;

        Node(int newData) {
            left = right = null;
            data = newData;
        }
    }

    /**
     * 判断二叉搜索树中是不是存在某个点
     * @param root
     * @param val
     * @return 1，存在；0，不存在
     */
    private static int isPresent(Node root, int val){
        if (root == null) {
            return 0;
        }
        if (find(root, val)) {
            return 1;
        } else {
            return 0;
        }
    }

    private static boolean find(Node root, int val) {
        if (root == null) {
            return false;
        }
        if (val < root.data ) {
            boolean left = find(root.left, val);
            if (left) {
                return true;
            }
        }
        if (root.data == val) {
            return true;
        }
        if (root.data > val) {
            return false;
        }
        boolean right = find(root.right, val);
        return right;
    }
}
