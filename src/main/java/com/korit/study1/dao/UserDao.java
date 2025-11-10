package com.korit.study1.dao;

import com.korit.study1.entity.User;
import com.korit.study1.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /*
    * Data Access Object
    * DB에 접근하고 데이터를 조작하는 사용하는 객체
    * */
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    // addUser(User user)
    public int addUser(User user) {
        String sql =
                "insert into " +
                        "user_tb(user_id, username, password, age, create_dt) " +
                "values " +
                        "(0, ?, ?, ?, now());";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Statement.RETURN_GENERATED_KEYS, AI 키 받음
            // PreparedStatement, SQLInjection 방지
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            int updateInt = ps.executeUpdate(); // 변경된 행의 개수 반환

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setUserId(id);
                }
            }
            return updateInt;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public User findUserByUsername(String username) {
        String sql =
                "select * from user_tb where username = ?;";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) { // 조회할 때 executeQuery()
                return rs.next() ? toUser(rs) : null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUserAllList() {
        String sql =
                "select * from user_tb";
        List<User> userAll = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                userAll.add(toUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAll;
    }

    public List<User> getUserByKeyword(String str) {
        String sql =
                "select * from user_tb where username like ?";
        List<User> userList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + str + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userList.add(toUser(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .age(rs.getInt("age"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}
