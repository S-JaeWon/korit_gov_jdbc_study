package com.korit.Ex;

public class Ex4 {
    public static void main(String[] args) {
        int[] scores = {95, 67, 88, 100, 73, 91};

        int sum = 0;
        int countHigh = 0;

        // 1. 총점과 90점 이상 개수 구하기
        for (int num : scores) {
            sum += num;
            if (num >= 90) {
                countHigh++;
            }
        }

        double avg = (double) sum / scores.length;

        System.out.println("총점: " + sum);
        System.out.println("평균: " + avg);
        System.out.println("90점 이상 인원 수: " + countHigh);
    }
}
