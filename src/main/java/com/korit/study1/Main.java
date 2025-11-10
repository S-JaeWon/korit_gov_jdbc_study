package com.korit.study1;

import com.korit.study1.dao.UserDao;
import com.korit.study1.entity.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        // insert
        User user = User.builder()
                .username("jw1234")
                .password("1q2w3e")
                .age(18)
                .build();

        int count = userDao.addUser(user);
        System.out.println("추가된 행 개수: " + count);
        System.out.println("추가된 유저 정보: " + user);

        //단건조회
        User searchUser = userDao.findUserByUsername("user3");
        System.out.println(searchUser);

        //다건조회
        List<User> searchUserAll = userDao.getUserAllList();
        searchUserAll.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.print("검색 >>> ");
        String keyword = scanner.nextLine();
        List<User> searchUserByKeyword = userDao.getUserByKeyword(keyword);
        searchUserByKeyword.forEach(System.out::println);
    }
}