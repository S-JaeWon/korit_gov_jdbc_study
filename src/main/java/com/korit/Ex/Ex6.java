package com.korit.Ex;

public class Ex6 {
    public static void main(String[] args) {
        int[] nums = {30, 10, 50, 7, 20};

        int min = nums[0];
        int minIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
                minIndex = i;
            }
        }
        System.out.println(min);
        System.out.println(minIndex);
    }
}
