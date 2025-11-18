package com.korit.Ex;

public class Ex10 {
    public static int sumArray(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];

            sum += n;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] data = {3, 6, 9, 12};
        int result = sumArray(data);
        System.out.println("합계: " + result);
    }
}
