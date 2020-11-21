package com.alibaba.edison;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by 钧戈 on 2020/10/5.
 */
public class Sword56_ii {
    public int singleNumber(int[] nums) {

        return sort(nums);
    }

    /**
     * leetCode第一赞思路太牛逼了。
     * 如下图所示，考虑数字的二进制形式，对于出现三次的数字，各 二进制位 出现的次数都是 3 的倍数。
     * 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。
     *
     * @param nums
     * @return
     */
    private int byteCount(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    private int byteCount2(int[] nums) {
        int[] counts = new int[32];
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }

    private int sort(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre) {
                count++;
            } else if (count == 1) {
                break;
            } else {
                pre = nums[i];
                count = 1;
            }
        }
        return pre;
    }

    private int hash(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                map.put(num, count+1);
            } else {
                map.put(num, 1);
            }
        }
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 二刷 2020-11-01 18:12:08
     * @param nums
     * @return
     */
    public int ershua(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= (count[31-i] % 3);
        }
        return res;
    }
}
