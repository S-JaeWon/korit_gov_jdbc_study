package com.korit.Ex;

public class Ex5 {
    public static void main(String[] args) {
        int n = 10;
        int i = 1;
        int sum = 0;

        while (i <= n) {
            sum = sum + i;
            i++;
        }

        System.out.println("1부터 " + n + "까지의 합: " + sum);
    }
}
