package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和，medium
 * <p>
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以
 * 列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * <p>
 * 1.这道题，暴力法是行不通的。
 * 2.只能用回溯法。通常这种探索所有可能解的题目，都可以用回溯法。
 * <p>
 * LeetCode22 和 LeetCode17 也是回溯法，可以一并看看
 * <p>
 * 未能独立解答
 * <p>
 * author: qonyqian
 * created on: 2022/2/7 3:25 下午
 * version：1.0
 * description:
 */
public class LeetCode39 {

    /**
     * 对于这类寻找所有可行解的题，我们都可以尝试用「搜索回溯」的方法来解决。并且，搜索回溯的过程一定存在一些优秀的剪枝方法来使得程序运行
     * 得更快。
     * <p>
     * 这道题可以当成一棵树（甚至所有的回溯法都可以当成一棵树），target就是根节点，每向下搜索一步，都是一个岔路口，面临n条岔路（本题里，就是
     * candidates的所有元素），选择其中一条岔路继续探索，探索完毕，再选下一条路径探索，直到把所有路径都探索完毕。
     * <p>
     * 至于剪枝函数，通常在面临岔路口，选择路径时起作用。根据一些条件，可以预先排除一些岔路，加快探索的效果。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>(); //已经探索过的路径节点
        int index = 0; //从岔路口的第几条路开始探索。初始肯定是从第一条探索。
        recursion(result, combine, candidates, target, index); //递归探索
        return result;
    }

    /**
     * 又是熟悉的递归探索
     *
     * @param result
     * @param combine    已经探索过的路径节点
     * @param candidates 候选数组
     * @param target     根节点的值
     * @param index      从岔路口的第几条路开始探索。在本题中，candidates的每个元素，都是一条岔路，都可以探索。但是为了避免重复，
     *                   前面循环内探索过的值，就不用再次探索了，否则会产生很多重复数据。即使把index去掉，也可以探索出所有的结果。
     */
    public void recursion(List<List<Integer>> result, List<Integer> combine, int[] candidates, int target, int index) {
        for (int i = index; i < candidates.length; i++) {
            combine.add(candidates[i]);
            int sum = combine.stream().mapToInt(value -> value).sum(); //这里有个遍历求和的过程，可以优化。把target变成target-sum
            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                temp.addAll(combine); //深拷贝
                result.add(temp);
            } else if (sum < target) {
                recursion(result, combine, candidates, target, i);
            } else if (sum > target) {
                //这条路径不满足要求
            }
            combine.remove(combine.size() - 1);
        }
    }


    /**
     * 官方代码，
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> officialCombinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

}
