package com.korit.Ex;

public class Ex7 {
    public static void main(String[] args) {
        int[] scores = {50, 82, 77, 90, 100, 68};
        int[] highScores = new int[scores.length];

        int index = 0;

        // 1. 80점 이상인 점수만 highScores에 저장
        for (int s : scores) {
            if (s >= 80) { //80점 이상인지를 검사하는 조건식을 작성하시오.
                highScores[index] = s;
                index++; //highScores에 값을 넣었을 때 인덱스를 증가시키는 문장을 작성하시오.
            }
        }

        // 2. 결과 출력
        System.out.println("80점 이상 점수:");
        for (int i = 0; i < index; i++) {
            System.out.println(highScores[i]);
        }
    }
}

