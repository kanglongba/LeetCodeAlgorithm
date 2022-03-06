package com.alibaba.edison;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 每日温度，medium
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度。如果
 * 气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 未能想到最优解
 *
 * author: qonyqian
 * created on: 2022/2/15 7:36 下午
 * version：1.0
 * description:
 */
public class LeetCode739 {

    /**
     * 1.暴力法，由后向前遍历，复用前面的计算结果，加快搜索速度
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        ans[length - 1] = 0; //最后一天的后面没有更高温度了
        for (int i = length - 2; i >= 0; i--) { //从倒数第二天开始，向前探索
            if (temperatures[i] < temperatures[i + 1]) { //小于后一天
                ans[i] = 1;
            } else if (temperatures[i] == temperatures[i + 1]) { //等于后一天
                ans[i] = ans[i + 1] == 0 ? 0 : ans[i + 1] + 1;  //注意这里要判断后面还没有更高的温度
            } else {                                    //大于后一天
                int j = i + 1 + ans[i + 1];             // 借助 ans，直接跳转到 比后一天高的那一天
                ans[i] = 0;
                while (j < length) {
                    if (temperatures[i] < temperatures[j]) {  // 找到了更高的气温
                        ans[i] = j - i;
                        break;
                    } else if (ans[j] == 0) { //后面已经没有更高的温度了
                        break;
                    } else {
                        j = j + ans[j];  //借助 ans，直接跳转到 气温更高 的一天
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 单调栈，即元素递增、或递减栈。
     * 官方代码：https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     *
     * 单调栈的算法思想确实很巧妙。
     * 在本题中维护一个单调递减的栈，栈中存储下标，如果遇到更大的元素，就依次把栈中的元素出栈，然后下标相减，就是第几天温度会更高。
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperaturesStack(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>(); //存储下标。Deque很牛逼，同时具有Queue、Stack 和 双端队列 的接口方法。
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            // 这里有一个特别需要注意的地方，LinkedList当作Stack用时，它是在队头出栈和入栈，即pop把队头出栈，push把元素插入队头。
            // 这里的peek方法同时兼容了Stack和Queue，它取第一个元素。我猜正是为了使peek能同时兼容Stack和Queue，才会安排LinkedList在
            // 队头入栈（push）和出栈（pop）。
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) { // 遇到更大的元素，不符合单调递减。
                int prevIndex = stack.pop();     // 把元素出栈，直到满足单调递减特性。
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i); //维护单调递减的栈
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode739 leet = new LeetCode739();
        int[] temperatures = new int[] {80,73,74,75,71,69,72,76,73};
        int[] ans = leet.dailyTemperaturesStack(temperatures);
        System.out.println("args = " + Arrays.toString(ans));
    }

}
