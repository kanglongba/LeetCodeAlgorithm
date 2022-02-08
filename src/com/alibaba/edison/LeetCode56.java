package com.alibaba.edison;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并区间，medium
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的
 * 区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * <p>
 * author: qonyqian
 * created on: 2022/2/7 11:17 下午
 * version：1.0
 * description:
 */
public class LeetCode56 {

    /**
     * 这种类型的题，没有套路。就是分析规律，然后用代码实现。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        //分析规律：先排序，再遍历
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; //升序
            }
        });
        int length = intervals.length;
        for (int i = 0; i < length; i++) {
            queue.add(intervals[i]);
        }
        List<int[]> result = new ArrayList<>();
        int[] temp = queue.poll();
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            if (temp[1] >= next[1]) { //前一个区间完全包围后一个区间
                temp = new int[]{temp[0], temp[1]}; //扩大范围，向后继续探索
            } else if (temp[1] >= next[0]) { //建一个区间与后一个区间存在交集
                temp = new int[]{temp[0], next[1]}; //扩大范围，向后继续探索
            } else { //前一个区间与后一个区间，没有交集
                result.add(temp); //前一个区间探索完毕，添加到结果列表
                temp = next; //把下一个区间设置为探索区间，继续下一个循环
            }
        }
        result.add(temp); //集合已经遍历完成，把最有一个区间添加到结果列表
        int[][] array = new int[result.size()][]; //把list转化为数组
        return result.toArray(array);
    }
}
