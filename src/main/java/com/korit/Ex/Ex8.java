package com.korit.Ex;

public class Ex8 {
    public static void main(String[] args) {
        String raw = "10,20,30,40";

        String[] parts = raw.split(",");

        int sum = 0;

        for (int i = 0; i < parts.length; i++) {
            String p = parts[i];
            int num = Integer.parseInt(p);
            sum += num;
        }

        System.out.println("합계" + sum);
    }


}
