package com.korit.Ex;

public class Ex2 {
    public String name;
    public int age;

    // 1. 생성자
    public Ex2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 2. 정보 출력 메서드
    public void printInfo() {
        System.out.println("이름: " + name + ", 나이: " + age);
    }

    // 3. 성인 여부 확인 (20세 이상이면 true)
    public boolean isAdult() {
        return age >= 20;
    }

    public static void main(String[] args) {
        Ex2 m = new Ex2("홍길동", 23);
        m.printInfo();
        System.out.println("성인 여부: " + m.isAdult());
    }
}