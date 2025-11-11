package com.korit.study2.dao;

import com.korit.study2.dto.GetUserListRespDto;
import com.korit.study2.entity.User;
import com.korit.study2.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public int addUser(User user) {
        String sql = "insert into user2_tb values (0, ?, ?, ?, now());";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            int updateInt = ps.executeUpdate();

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

    public Optional<User> searchUsernameByUsername(String username) {
        String sql = "select * from user2_tb where username = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<User> searchUsernameByEmail(String email) {
        String sql = "select * from user2_tb where email = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<User> searchUserAll() {
        String sql = "select * from user2_tb;";
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

    public List<GetUserListRespDto> searchUserByKeyword(String str) {
        String sql = "select user_id, username, email, create_dt from user2_tb where username like?";
        List<GetUserListRespDto> userListDto = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + str + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userListDto.add(toUserDto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userListDto;
    }

    public User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }

    public GetUserListRespDto toUserDto (ResultSet rs) throws SQLException {
        return GetUserListRespDto.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }

    /*
    * username, email 조회
    * user 추가
    * user 전체 조회
    * */

}
