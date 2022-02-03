package com.alibaba.edison;

/**
 * 移动零，easy
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * author: qonyqian
 * created on: 2022/2/2 5:09 下午
 * version：1.0
 * description:
 */
public class LeetCode283 {

    /**
     * 1.最简单肯定是冒泡，并把沿途碰到的0集合到一起，然后集体冒泡。这样只需要一趟遍历。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        //第一个0的位置
        int start = -1;
        //最后一个0的位置
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (start == -1) { //第一个0
                    start = i;
                    end = i;
                } else { //又遇到一个0，把它加到集合里
                    end = i;
                }
            } else {
                if (start == -1) { //还没有0的集合，继续向前探索
                    continue;
                } else { //有0集合，并且遇到了非0元素
                    nums[start] = nums[i]; //把0集合的第一个元素和探索到的非0元素交换，这样可以显著减少移动次数
                    nums[i] = 0;
                    start++;
                    end++;
                }
            }
        }
    }
}
