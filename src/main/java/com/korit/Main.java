package com.korit;

import com.korit.dao.UserDao;
import com.korit.entity.User;

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
    }
}