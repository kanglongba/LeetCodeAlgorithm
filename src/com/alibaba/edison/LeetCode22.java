package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成，medium
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 回溯法
 * LeetCode17也是回溯法，两者思路一样，可以结合起来看。
 * <p>
 * author: qonyqian
 * created on: 2022/2/5 9:46 下午
 * version：1.0
 * description:
 */
public class LeetCode22 {


    /**
     * 一共有n个左括号和n个右括号，第一个位置永远是左括号，第2n个位置永远是右括号，仅剩下中间2n-2个位置可以用来排列组合。
     * 每次填入括号时，要么填左括号，要么填右括号，没有其他选择。且为了满足字符串的合法行，已填入的左括号数量必须 >= 右括号数量。
     * <p>
     * 结合上述分析，每次填入括号时都会遇到岔路口（左括号 or 右括号），每次遇到岔路口都有剪枝条件（已填入的左括号数量必须 >= 右括号数量）。
     * 因此，可以用回溯法解决这个问题。
     *
     * 官方答案的代码更简洁：https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 1) {
            result.add("()");
            return result;
        }
        StringBuilder builder = new StringBuilder("(");
        int leftCount = 1; //已填入的左括号数量。第一个必定是左括号，所以初始值就是1
        int rightCount = 0; //已填入的右括号数量。初始值是0
        //使用递归遍历岔路口
        recursion(n, result, builder, leftCount, rightCount);
        return result;
    }

    /**
     * 使用递归遍历岔路口
     *
     * @param n          可填入的左括号和右括号总数
     * @param result
     * @param builder
     * @param leftCount  已填入的左括号数量
     * @param rightCount 已填入的右括号数量
     */
    public void recursion(int n, List<String> result, StringBuilder builder, int leftCount, int rightCount) {
        // 每一个if条件，都是岔路口的一种情况。这些岔路口，有的仅有一种选择，有的有两种选择。
        // if条件开始，代表开始探索这个岔路口；if条件返回，代表这个岔路口已经探索完成。
        // 所以不论岔路口有几种选择，都必须在if范围内，把探索岔路口的代码写完。
        if (leftCount == n) { //在这种岔路口，左括号已经用光了，只能全部填入右括号
            int count = n - rightCount;
            for (int i = 0; i < count; i++) {
                builder.append(")");
            }
            result.add(builder.toString()); //此时已是终点，没有其他路径可以探索
            builder.delete(2 * n - count, 2 * n);//左闭右开。把填入的右括号退出，回到上一个岔路口
        } else if (leftCount == rightCount) { //在这种岔路口，为了保持合法性，只能填入左括号
            builder.append("(");
            int count = builder.length();
            recursion(n, result, builder, leftCount + 1, rightCount); //仍未到达终点，继续探索
            builder.deleteCharAt(count - 1); //删除左括号，回到上一个岔路口
        } else if (leftCount > rightCount) { //在这种岔路口，有两种选择，既可以填入左括号，又可以填入右括号
            builder.append("(");
            int count = builder.length();
            recursion(n, result, builder, leftCount + 1, rightCount); //仍未到达终点，继续探索
            builder.deleteCharAt(count - 1); //删除左括号，继续探索这个岔路口的下一条路

            builder.append(")");
            count = builder.length();
            recursion(n, result, builder, leftCount, rightCount + 1); ////仍未到达终点，继续探索
            builder.deleteCharAt(count - 1); //删除右括号，这个岔路口已经探索完毕，返回上一个岔路口
        }
    }
}
