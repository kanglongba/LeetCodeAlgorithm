package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程表，medium
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习
 * 课程 ai 则 必须 先学习课程 bi 。
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 未能独立解答
 *
 * 本题是一道经典的「拓扑排序」问题。
 * 给定一个包含 n 个节点的有向图 G，我们给出它的节点编号的一种排列，如果满足：
 * 对于图 G 中的任意一条有向边 (u, v)，u 在排列中都出现在 v 的前面。
 * 那么称该排列是图 G 的「拓扑排序」
 *
 * 根据上述的定义，我们可以得出两个结论：
 * 1.如果图 G 中存在环（即图 G 不是「有向无环图」），那么图 G 不存在拓扑排序
 * 2.如果图 GG 是有向无环图，那么它的拓扑排序可能不止一种
 *
 * 有了上述的简单分析，我们就可以将本题建模成一个求拓扑排序的问题了：
 * 1.我们将每一门课看成一个节点；
 * 2.如果想要学习课程 A 之前必须完成课程 B，那么我们从 B 到 A 连接一条有向边。这样以来，在拓扑排序中，B 一定出现在 A 的前面。
 * 求出该图是否存在拓扑排序，就可以判断是否有一种符合要求的课程学习顺序。
 *
 *
 *
 * author: qonyqian
 * created on: 2022/2/11 3:44 下午
 * version：1.0
 * description:
 */
public class LeetCode207 {

    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;


    /**
     * 就是判断一个图中是否存在环
     * 我们可以将深度优先搜索的流程与拓扑排序的求解联系起来，用一个栈来存储所有已经搜索完成的节点。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

}
