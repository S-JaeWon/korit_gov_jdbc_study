package com.korit.study2.service;

import com.korit.study2.dao.UserDao;
import com.korit.study2.dto.GetUserListRespDto;
import com.korit.study2.dto.SigninReqDto;
import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.entity.User;
import com.korit.study2.util.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;

    private UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(UserDao.getInstance());
        }
        return instance;
    }

    public void signup(SignupReqDto signupReqDto) {
        User user = signupReqDto.toEntity();
        userDao.addUser(user);
    }

    public int signin(SigninReqDto signinReqDto) {
        Optional<User> searchUser = userDao.searchUsernameByUsername(signinReqDto.getUsername());
        searchUser.get();

        if (searchUser.isEmpty() || !PasswordEncoder.match(signinReqDto.getPassword(), searchUser.get().getPassword())) {
            return 1;
        }
    }

    public boolean isDupleUsername(String username) {
        Optional<User> searchUser = userDao.searchUsernameByUsername(username);
        return searchUser.isPresent();
    }

    public boolean isDupleEmail(String email) {
        Optional<User> searchUser = userDao.searchUsernameByEmail(email);
        return searchUser.isPresent();
    }

    public List<User> userListAll() {
        return userDao.searchUserAll();
    }
    /*
     *
     * 회원가입
     * 로그인
     * 전체조회
     * 회원이름 조회
     * */
}
