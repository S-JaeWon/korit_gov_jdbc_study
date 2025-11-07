package com.korit.dao;

import com.korit.entity.User;
import com.korit.util.ConnectionFactory;

import java.sql.*;

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
    // findUserByUsername(String username)
    // getUserAllList()
}
