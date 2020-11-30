package com.joking.test.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SingleNumber {

    public static void main(String[] args) {
        int i = singleNumber(new int[]{2, 2, 3, 3, 6, 6, 1, 9, 9});
        System.out.println(i);
    }

    public static int singleNumber(int[] nums) {
        List<Integer> set = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                set.remove(Integer.valueOf(nums[i]));
            } else {
                set.add(nums[i]);
            }
        }
        return set.get(0);
    }
}
