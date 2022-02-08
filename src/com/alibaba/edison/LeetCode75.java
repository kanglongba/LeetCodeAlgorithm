package com.alibaba.edison;

/**
 * 颜色分类，medium
 * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 3:06 下午
 * version：1.0
 * description:
 */
public class LeetCode75 {

    /**
     * 1.直接单个元素暴力冒泡
     * 2.没有套路，只能分析规律，然后实现。双指针+团体冒泡。
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        //团体冒泡，使目标团体一直紧贴着next元素，一步一步把目标团体冒上去。
        for (int i = 2; i > 0; i--) { //一共三种元素，当把 2 和 1 排好了，0 自然也就排好了，所以只需要两次循环
            int left = -1, right = -1; //双指针
            for (int j = 0; j < nums.length; j++) { //开始团体冒泡
                if (nums[j] == i) { //next是目标元素
                    if (left == -1) { //以前未发现目标团体，则直接更新双指针
                        left = j;
                        right = j;
                    } else { //以前发现了目标团体，直接扩充移动右边界指针，扩充团体
                        right = j;
                    }
                } else if (nums[j] < i) { //next比目标元素小，所以应该把它移动团体的左边
                    if (left == -1) { //左边还没有目标团体
                        continue;
                    } else { //左边已有目标团体，直接交换左指针和next
                        int temp = nums[j];
                        nums[j] = nums[left];
                        nums[left] = temp;
                        left++; //更新边界指针
                        right++; //更新边界指针
                    }
                } else { //因为我们是先排大元素，再排小元素，所以如果遇到比目标团体大的next，说明已经后面已经都是排好序的元素，那么就直接结束本次冒泡
                    break;
                }
            }
        }
    }
}
