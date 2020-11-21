package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/18.
 */
public class Sword26 {

    /**
     * 这种题有类似的套路，参考答案：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/pi-pei-lei-er-cha-shu-ti-mu-zong-jie-by-z1m/
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return dfs(A, B);
    }

    /**
     * 深度优先搜索(先序遍历+递归)
     * @param A
     * @param B
     * @return
     */
    private boolean dfs(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || dfs(A.left, B) || dfs(A.right, B));
    }

    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * 深度优先搜索(先序遍历+递归)
     * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/di-gui-fang-shi-jie-jue-by-sdwwld/
     * @param A
     * @param B
     * @return
     */
    private boolean dfsQ(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return digui(A, B) || dfsQ(A.left, B) || dfsQ(A.right, B);
    }

    private boolean digui(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && digui(A.left, B.left) && digui(A.right, B.right);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
