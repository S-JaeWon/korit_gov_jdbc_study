package com.korit.Ex;

public class Ex1 {
    public static void main(String[] args) {

        int kor = 75;
        int eng = 88;
        int math = 92;

        int sum = kor + eng + math;

        double avg = (double) sum / 3;

        int max = kor;

        if (eng > max) {
            max = eng;
        }

        if (math > max) {
            max = math;
        }

        System.out.println("총점: " + sum);
        System.out.println("평균: " + avg);
        System.out.println("최고점: " + max);
    }
}
