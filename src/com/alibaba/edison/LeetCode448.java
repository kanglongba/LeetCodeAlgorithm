package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字，easy
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * 未能独立想到最优解
 * author: qonyqian
 * created on: 2022/2/2 11:06 下午
 * version：1.0
 * description:
 */
public class LeetCode448 {

    /**
     * 1.最简单的方法是先排序再遍历
     * 2.建立一个同样长度的数组，下标表示数字，值表示数字出现的次数，最后遍历数组，取出值为0的元素，共需遍历两次。
     * 3.最优解【官方】：具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。由于 nums 中所有数均在 [1,n] 中，增加以后，这
     * 些数必然大于 n。最后我们遍历 nums，若 nums[i] 未大于 n，就说明没有遇到过数 i+1。这样我们就找到了缺失的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //核心思想，就是把原数组 nums，当成辅助数组来用，也只有这种方法，才能使空间复杂度是O(1)
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n; //当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    /**
     * 第二种解法
     *
     * @param nums
     * @return
     */
    public List<Integer> extraArray(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        int[] temp = new int[n + 1];
        for (int num : nums) {
            temp[num] = temp[num] + 1;
        }
        for (int i = 1; i <= n; i++) {
            if (temp[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }
}
