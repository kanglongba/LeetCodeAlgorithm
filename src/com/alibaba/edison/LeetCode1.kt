package com.alibaba.edison

import java.util.*

/**
 * 两数之和，easy
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组
 * 下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。
 *
 * 只想到排序法，未能想到最优解
 * author: qonyqian
 * created on: 2022/1/17 9:10 下午
 * version：1.0
 * description:
 */

fun main(vararg args: String) {

}

class Solution {

    /**
     * 先排序，在从两头往中间查找
     * 这样子的话，顺序会乱
     */
    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val numsCopy = nums.copyOf()
        Arrays.sort(nums)
        var i = 0
        var j = nums.size - 1
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                break
            } else if (nums[i] + nums[j] < target) {
                i++
            } else {
                j--
            }
        }
        var m = 0
        var n = 0
        for (index in numsCopy.indices) {
            if (numsCopy[index] == nums[i] && m == 0) {
                m = index
            } else if (numsCopy[index] == nums[j] && n == 0) {
                n = index
            }
        }
        return if (m < n) {
            intArrayOf(m, n)
        } else {
            intArrayOf(n, m)
        }
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        var map = hashMapOf<Int, Int>()
        for (index in nums.indices) {
            if (map.contains(target - nums[index])) {
                return intArrayOf(map[target - nums[index]]!!, index)
            }
            map[nums[index]] = index
        }
        return intArrayOf(0)
    }
}