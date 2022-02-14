package com.alibaba.edison;

import java.util.*;

/**
 * 除法求值，medium
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示
 * 等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要
 * 用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 * 未能独立解答。
 *
 * 这道题很难，没有价值，直接放弃。
 *
 * author: qonyqian
 * created on: 2022/2/14 5:09 下午
 * version：1.0
 * description:
 */
public class LeetCode399 {

    /**
     * 这题描述太复杂了，根本没有做的欲望
     * 官方答案：https://leetcode-cn.com/problems/evaluate-division/solution/chu-fa-qiu-zhi-by-leetcode-solution-8nxb/
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (ia == ib) {
                    result = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<Integer>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    class Pair {
        int index;
        double value;

        Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

}
