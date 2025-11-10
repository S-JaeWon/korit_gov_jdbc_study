package com.korit.study2;

import com.korit.study2.dto.GetUserListRespDto;
import com.korit.study2.dto.SigninReqDto;
import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = UserService.getInstance();

        while (true) {
            System.out.println("[ 회원관리 ]");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 전체회원 조회");
            System.out.println("4. 회원 검색");
            System.out.println("q. 프로그램 종료");
            System.out.print(": ");
            String select = scanner.nextLine();

            if ("q".equals(select)) {
                System.out.println("종료");
                break;
            } else if ("1".equals(select)) {
                System.out.println("[ 회원가입 ]");

                SignupReqDto signupReqDto = new SignupReqDto();

                while (true) {
                    System.out.print("아이디: ");
                    signupReqDto.setUsername(scanner.nextLine());
                    if (userService.isDupleUsername(signupReqDto.getUsername())) {
                        System.out.println("중복된 아이디 입니다.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("비밀번호: ");
                    signupReqDto.setPassword(scanner.nextLine());

                    if (signupReqDto.getPassword() == null || signupReqDto.getPassword().isBlank()) {
                        System.out.println("유효하지 않은 비밀번호입니다.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("이메일: ");
                    signupReqDto.setEmail(scanner.nextLine());

                    if (userService.isDupleEmail(signupReqDto.getEmail())) {
                        System.out.println("중복된 이메일입니다.");
                        continue;
                    }
                    break;
                }

                userService.signup(signupReqDto);
                System.out.println("회원가입 완료");
            }
            else if ("2".equals(select)) {
                System.out.println("[ 로그인 ]");
                SigninReqDto signinReqDto = new SigninReqDto();
                System.out.print("아이디: ");
                signinReqDto.setUsername(scanner.nextLine());
                System.out.print("비밀번호: ");
                signinReqDto.setPassword(scanner.nextLine());
                if () {

                }


                userService.signin(signinReqDto);
                System.out.println("로그인 완료");

            }
            else if ("3".equals(select)) {
                System.out.println("[ 전체회원 조회 ]");
                GetUserListRespDto getUserListRespDto = new GetUserListRespDto();
                System.out.println(userService.userListAll());

            }
            else if ("4".equals(select)) {
                System.out.println("[ 회원 검색 ]");

            }

        }
    }
}
