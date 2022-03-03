package com.alibaba.edison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列，medium
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * <p>
 * 典型的回溯法。
 * 结合 17、22、39 一起看
 * <p>
 * author: qonyqian
 * created on: 2022/2/7 7:01 下午
 * version：1.0
 * description:
 */
public class LeetCode46 {

    /**
     * 本题求所有可能的解，很明显就是回溯法。
     * 剪枝函数：不能有重复数字，在遇到岔路口时，直接把已经用过的数字的路径给剪掉。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> permute = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int length = nums.length;
        recursion(result, permute, set, nums, length, 0);
        return result;
    }


    /**
     * @param result
     * @param permute
     * @param set     剪枝函数，用来判断数字是否重复
     * @param nums
     * @param length
     * @param index   探索到全排列的第几重，一共 nums.length 重。
     */
    public void recursion(List<List<Integer>> result, List<Integer> permute, HashSet<Integer> set, int[] nums, int length, int index) {
        if (index == length) { //探索完成
            List<Integer> list = new ArrayList<>();
            list.addAll(permute);
            result.add(list);
            return;
        }
        for (int i = 0; i < length; i++) { //每一个岔路口，都有 length 条岔路
            if (!set.contains(nums[i])) { //剪枝函数，把重复数字剪掉
                permute.add(nums[i]);
                set.add(nums[i]); //剪枝函数要同步加减
                recursion(result, permute, set, nums, length, index + 1); //递归探索全排列下一重
                permute.remove(permute.size() - 1);
                set.remove(nums[i]); //剪枝函数要同步加减
            }
        }
    }
}
