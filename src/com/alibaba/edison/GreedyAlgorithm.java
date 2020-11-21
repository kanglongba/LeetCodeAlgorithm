package com.alibaba.edison;

/**
 * https://zhuanlan.zhihu.com/p/53334049
 * Created by 钧戈 on 2020/5/23.
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        int[][] points = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};

        int result = findMinArrowShots(points);
        System.out.println("args = [" + result + "]");
    }

    public static int findMinArrowShots(int[][] points) {
        int[] boom = new int[points.length];
        int count=0;
        for(int i=0;i<points.length;i++){
            if(boom[i] == 0) {
                boom[i]=1;
                count++;
                int start = points[i][0];
                int end = points[i][1];
                for(int j=i+1;j<points.length;j++) {
                    if(boom[j]==0) {
                        if(points[j][0] <= start) {
                            if(start<=points[j][1]) {
                                end = points[j][1]>=end?end:points[j][1];
                                boom[j]=1;
                            }
                        } else {
                            if (end >= points[j][0]) {
                                start = points[j][0];
                                end = points[j][1]>=end?end:points[j][1];
                                boom[j]=1;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
