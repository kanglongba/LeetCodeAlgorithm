package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword11 {

    /**
     * 二刷 2020-10-31 17:50:37
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        return min(numbers);
    }

    /**
     * 题解
     * @param numbers
     * @return
     */
    private int min(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 思路对了，但是代码写错了
     * 二刷 2020-10-31 17:52:40
     * 先分析出题目的规律，再选用合适的方法
     * 先一段是上升，后一段也是上升，那么先用二分法确定是在前半段还是后半段，逐渐缩小范围
     * @param numbers
     * @return
     */
    private int mid(int[] numbers) {
        int low = 0;
        int high = numbers.length -1;
        while (low < high) {
            int mid = (low+high)/2;
            if (numbers[mid] > numbers[low] && numbers[mid] > numbers[high]) {
                low = mid+1;
            } else if (numbers[mid] < numbers[high] && numbers[mid] < numbers[low]) {
                high = mid-1;
            } else {
                break;
            }
        }
        return Math.min(numbers[low], numbers[high+1]);
    }

    /**
     * 二分查找算法
     * @param number
     * @param num
     * @return
     */
    public int midSerach(int[] number, int num) {
        Arrays.sort(number);
        int low = 0;
        int high = number.length-1;
        int mid;
        while (low <= high) {
            mid = (low+high)/2;
            if (number[mid] == num) {
                return mid;
            } else if (number[mid] > num) {
                high = mid-1;
            } else if (number[mid] < num) {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * 快速排序
     * https://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
     * https://juejin.im/post/6844903910772047886
     * @param number
     * @return
     */
    public void quick(int[] number) {
        quickSort(number, 0, number.length-1);
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
