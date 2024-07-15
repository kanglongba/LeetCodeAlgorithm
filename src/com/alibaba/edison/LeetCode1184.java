package com.alibaba.edison;

/**
 * easy，公交站间的距离
 *
 * 环形公交路线上有n个站，按次序从0到n - 1进行编号。我们已知每一对相邻公交站之间的距离，distance[i]表示编号为i的车站和编号为(i + 1) % n的车站之间的距离。
 *
 * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
 *
 * 返回乘客从出发点start到目的地destination之间的最短距离。
 *
 * 输入：distance = [1,2,3,4], start = 0, destination = 1
 * 输出：1
 * 解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
 *
 * 输入：distance = [1,2,3,4], start = 0, destination = 2
 * 输出：3
 * 解释：公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
 *
 * 输入：distance = [1,2,3,4], start = 0, destination = 3
 * 输出：4
 * 解释：公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
 *
 *
 * author: qonyqian
 * created on: 2022/7/24 16:47
 * version：1.0
 * description:
 */
public class LeetCode1184 {

    /**
     * 顺时针搜索，取start->destination和destination->start的最小值
     *
     * @param distance
     * @param start
     * @param destination
     * @return
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int i = start;
        int length = 0;
        int n = distance.length;
        while (i != destination) {
            length += distance[i];
            i = (i + 1) % n;
        }
        int length1 = 0;
        int j = destination;
        while (j != start) {
            length1 += distance[j];
            j = (j + 1) % n;
        }
        return Math.min(length, length1);
    }
}
