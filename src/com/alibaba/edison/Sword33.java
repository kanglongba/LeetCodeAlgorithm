package com.alibaba.edison;

import java.util.Stack;

/**
 * Created by 钧戈 on 2020/10/17.
 */
public class Sword33 {

    /**
     * 这个题很经典
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    /**
     * 递归分治法
     *
     * @param postorder
     * @param i
     * @param j
     * @return
     */
    boolean recur(int[] postorder, int i, int j) {
        if (i >= j) { return true; }
        int p = i;
        while (postorder[p] < postorder[j]) { p++; }
        int m = p;
        while (postorder[p] > postorder[j]) { p++; }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    /**
     * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
     * 辅助单调栈。同样是先做归纳总结出规律，然后用数学分析建模，最后选择合适的数据结构。
     * 真的是看题解都不明白，惭愧
     * @param postorder
     * @return
     */
    public boolean verifyPostorder1(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int parent = Integer.MAX_VALUE;
        //注意for循环是倒叙遍历的
        for (int i = postorder.length - 1; i >= 0; i--) {
            int cur = postorder[i];
            //当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙，
            //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
            while (!stack.isEmpty() && stack.peek() > cur) {
                //求出cur的根节点。因为是单调栈，所以栈中比cur大的所有节点中最小的那个就是根节点。
                parent = stack.pop();
            }
            //只要遇到了某一个左子节点，才会执行上面的代码，才会更
            //新parent的值，否则parent就是一个非常大的值，也就
            //是说如果一直没有遇到左子节点，那么右子节点可以非常大
            if (cur > parent) {
                //能走到这里，说明没有执行上面的while循环，说明cur是前一个循环中节点A的右子节点，但是节点A一定是
                //parent的左子节点（因为只有发现是左子节点时才会更新parent的值，并把当前的节点压入堆栈），所以正
                //常情况下，cur必须要小于parent。
                return false;
            }
            //入栈.1.右子节点：升序是右子节点，直接入栈；2.左子节点：倒序是左子节点，上面的while循环负责找到它的parent，并
            //把parent的右子节点全部出栈了，然后把cur入栈
            stack.add(cur);
        }
        return true;
    }

}
