package com.korit.Ex;


import java.util.Arrays;

public class Ex3 {
    public static void main(String[] args) {
        String raw = "Kim,23; Lee,19; Park,32; Choi,25";

        // 1. 세미콜론(;) 기준으로 분리하여 people 배열에 저장
        String[] people = raw.split(";");

        // 2. 앞뒤 공백 제거
        for (int i = 0; i < people.length; i++) {
            people[i] = people[i].trim();
        }

        // 3. 이름 기준(문자열 기준) 오름차순 정렬
        Arrays.sort(people);

        // 4. 결과 출력
        System.out.println(Arrays.toString(people));
    }
}
