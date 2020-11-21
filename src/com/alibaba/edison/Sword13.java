package com.alibaba.edison;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 钧戈 on 2020/10/18.
 */
public class Sword13 {

    public int movingCount(int m, int n, int k) {
        return dfs(m, n, k);
    }

    /**
     * 深度优先搜索
     * @param m
     * @param n
     * @param k
     * @return
     */
    private int dfsCount = 0;

    private int dfs(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] visited = new boolean[m][n];
        dfs(0, 0, m, n, k, visited);
        return dfsCount;
    }

    private void dfs(int x, int y, int m, int n, int k, boolean[][] visited) {
        if (x < m && y < n && access(x, y, k) && !visited[x][y]) {
            visited[x][y] = true;
            dfsCount++;
            //向下
            dfs(x + 1, y, m, n, k, visited);
            //向右
            dfs(x, y + 1, m, n, k, visited);
        }
    }

    /**
     * 广度优先搜索
     * @param m
     * @param n
     * @param k
     * @return
     */
    private int bfs(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        // 向右和向下的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        boolean[][] vis = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 2; ++i) {
                int tx = dx[i] + x;
                int ty = dy[i] + y;
                if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || get(tx) + get(ty) > k) {
                    continue;
                }
                queue.offer(new int[]{tx, ty});
                vis[tx][ty] = true;
                ans++;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    private int bfs1(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int count = 1;
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            //向右移动
            if (point.y + 1 < n && access(point.x, point.y + 1, k) && !visited[point.x][point.y + 1]) {
                queue.offer(new Point(point.x, point.y + 1));
                visited[point.x][point.y + 1] = true;
                count++;
            }
            //向下移动
            if (point.x + 1 < m && access(point.x + 1, point.y, k) && !visited[point.x + 1][point.y]) {
                queue.offer(new Point(point.x + 1, point.y));
                visited[point.x + 1][point.y] = true;
                count++;
            }
        }
        return count;

    }

    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * 题目限制x,y的值[0,99]
     * @param x
     * @param y
     * @param k
     * @return
     */
    private boolean access(int x, int y, int k) {
        int xT = x/10;
        int xG = x%10;
        int yT = y/10;
        int yG = y%10;
        return (xT+xG+yT+yG) <= k;
    }
}
