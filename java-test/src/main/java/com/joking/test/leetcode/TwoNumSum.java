package com.joking.test.leetcode;

import java.util.*;

public class TwoNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,12,11,3,3,20,6};
        int[] index = twoSum2(nums, 9);
        for (int i : index) {
            System.out.println(i);
        }
    }

    public  static int[] twoSum2(int[] nums, int target) {
        int[] index = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if(map.containsKey(i1)) {
                index[0] = map.get(i1);
                index[1] = i;
                break;
            }
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        return index;
    }

    public  static int[] twoSum(int[] nums, int target) {
        int first = 0, sends = 0;
        int[] index = new int[2];
        for(int i = 0; i < nums.length; i ++ ) {
            if(i > target) {
                continue;
            }
            if(i < nums.length - 1) {
                first = nums[i];
                index[0] = i;
                boolean find = false;
                for(int j = i + 1; j < nums.length; j++) {
                    if(nums[j] <= target && (target - nums[j]) == first) {
                        sends = nums[j];
                        index[1] = j;
                        find = true;
                        break;
                    }
                }
                if(find) {
                    break;
                }
            }
            // 没有找到

        }
        return index;
    }
}