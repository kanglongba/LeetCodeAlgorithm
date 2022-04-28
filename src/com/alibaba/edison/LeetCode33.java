package com.alibaba.edison;

/**
 * 搜索旋转排序数组，medium
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在
 * 下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * author: qonyqian
 * created on: 2022/2/7 1:10 下午
 * version：1.0
 * description:
 */
public class LeetCode33 {

    /**
     * 1.最简单的就是直接遍历
     * 2.利用旋转特性，如果target比nums[0]大，说明在前半部分，如果比nums[0]小，说明在后半部分
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums[0] == target) {
            return 0;
        }
        int length = nums.length;
        //nums[0]就是轴点，以轴点为锚，分别搜索前半部分和后半部分
        if (nums[0] > target) {
            int index = length-1;
            while (index > 0 && nums[index] < nums[0]) {
                if (nums[index] == target) {
                    return index;
                }
                index--;
            }
        }
        if (nums[0] < target) {
            int index = 1;
            while (index <= length-1 && nums[index] > nums[0]) {
                if (nums[index] == target) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }
}
