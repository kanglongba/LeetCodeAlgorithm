package com.alibaba.edison;

/**
 * 排序数组，medium
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * 官方讲解排序算法：https://leetcode-cn.com/problems/sort-an-array/solution/pai-xu-shu-zu-by-leetcode-solution/
 * 大神讲解排序算法：https://leetcode-cn.com/problems/sort-an-array/solution/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/
 *
 * Created by 钧戈 on 2020/10/31.
 */
public class LeetCode912 {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    /**
     * 快速排序
     * https://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
     * https://juejin.im/post/6844903910772047886
     * @param number
     * @param start
     * @param end
     */
    public void quickSort(int[] number, int start, int end) {
        if (start < end) {
            int pos = partition(number, start, end);
            quickSort(number, start, pos-1);
            quickSort(number, pos+1, end);
        }
    }

    private int partition(int[] number, int start, int end) {
        //pivot为哨兵，为我们这趟比较的主元素，默认取第一个元素
        int pivot = number[start];
        //保存哨兵的索引，将来做交换
        int index = start;
        while (start < end) {
            //从右向左，搜索第一个小于哨兵的元素，每次搜索都不能越界，所以end>start
            while (number[end] >= pivot && end > start) {
                end--;
            }
            //从左向右，搜索第一个大于哨兵的元素，同样不能越界
            while (number[start] <= pivot && start < end) {
                start++;
            }
            //把两个元素交互，使大于哨兵的在右边，小于哨兵的在左边
            swap(number, start, end);
        }
        //最后，把哨兵和start交换，完成本次划分
        swap(number, index, start);
        //返回哨兵的位置
        return start;
    }

    private void swap(int[] number, int positionA, int positionB) {
        int value = number[positionA];
        number[positionA] = number[positionB];
        number[positionB] = value;
    }
}
