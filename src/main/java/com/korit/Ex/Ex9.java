package com.korit.Ex;

public class Ex9 {
    public static void main(String[] args) {
        int [] nums = {10, -3, 0, 25, 7};

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            if (n > 0) {
                sum += n;
            }
        }
        System.out.println(sum);
    }
}
