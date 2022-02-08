package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集，medium
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 3:59 下午
 * version：1.0
 * description:
 */
public class LeetCode78 {

    /**
     * 又是探索所有的解，自然就会想到回溯法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); //空集也是子集
        List<Integer> combine = new ArrayList<>();
        recursion(result, combine, nums, 0);
        return result;
    }

    /**
     * 递归探索
     * @param result
     * @param combine
     * @param nums
     * @param index 从第几个元素开始探索
     */
    public void recursion(List<List<Integer>> result, List<Integer> combine, int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            combine.add(nums[i]);
            List<Integer> temp = new ArrayList<>();
            temp.addAll(combine);
            result.add(temp); //每探索一步，都是一个子集
            recursion(result, combine, nums, i + 1); //因为不能包含重复的元素，所以用过的元素就不能再用了，要从 i + 1 开始探索
            combine.remove(combine.size() - 1); //回溯，探索另一条岔路
        }
    }
}
