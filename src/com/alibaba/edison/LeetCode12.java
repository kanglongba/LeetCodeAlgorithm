package com.alibaba.edison;

/**
 * 整数转罗马数字，medium
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于
 * 大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * 示例1:
 * <p>
 * 输入:num = 3
 * 输出: "III"
 * 示例2:
 * <p>
 * 输入:num = 4
 * 输出: "IV"
 * 示例3:
 * <p>
 * 输入:num = 9
 * 输出: "IX"
 * 示例4:
 * <p>
 * 输入:num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例5:
 * <p>
 * 输入:num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * author: qonyqian
 * created on: 2022/7/17 23:03
 * version：1.0
 * description:
 */
public class LeetCode12 {

    /**
     * 1 <= num <= 3999
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        while (true) {
            if (num - 1000 >= 0) {
                builder.append("M");
                num -= 1000;
            } else if (num - 900 >= 0) {
                builder.append("CM");
                num -= 900;
            } else if (num - 500 >= 0) {
                builder.append("D");
                num -= 500;
            } else if (num - 400 >= 0) {
                builder.append("CD");
                num -= 400;
            } else if (num - 100 >= 0) {
                builder.append("C");
                num -= 100;
            } else if (num - 90 >= 0) {
                builder.append("XC");
                num -= 90;
            } else if (num - 50 >= 0) {
                builder.append("L");
                num -= 50;
            } else if (num - 40 >= 0) {
                builder.append("XL");
                num -= 40;
            } else if (num - 10 >= 0) {
                builder.append("X");
                num -= 10;
            } else if (num - 9 >= 0) {
                builder.append("IX");
                num -= 9;
            } else if (num - 5 >= 0) {
                builder.append("V");
                num -= 5;
            } else if (num - 4 >= 0) {
                builder.append("IV");
                num -= 4;
            } else if (num - 1 >= 0) {
                builder.append("I");
                num -= 1;
            } else {
                break;
            }
        }
        return builder.toString();
    }
}
