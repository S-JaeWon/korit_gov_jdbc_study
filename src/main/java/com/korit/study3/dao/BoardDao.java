package com.korit.study3.dao;

import com.korit.study2.util.ConnectionFactory;
import com.korit.study3.entity.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDao {
    private static BoardDao instance;

    private BoardDao() {};

    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }

    public int addBoard(Board board) {
        String sql = "insert into board_tb values (0, ?, ?, ?, now())";

        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getUsername());

            int  updateInt = ps.executeUpdate();

            try (ResultSet key = ps.getGeneratedKeys()) {
                if (key.next()) {
                    int id = key.getInt(1);
                    board.setBoardId(id);
                }
            }
            return updateInt;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Optional<Board> searchTitleByTitle(String title) {
        String sql = "select * from board_tb where title = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toBoard(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Board> searchBoardById(Integer boardId) {
        String sql = "select * from board_tb where board_id = ? order by create_dt desc";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, boardId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toBoard(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Board> searchBoardByUsername(String username) {
        String sql = "select * from board_tb where username = ? order by create_dt desc";
        List<Board> boardList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    boardList.add(toBoard(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }

    public List<Board> searchBoardByKeyword(String keyword) {
        String sql = "select * from board_tb where title like ? or content like ? order by create_dt desc";
        List<Board> boardList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    boardList.add(toBoard(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }

    private Board toBoard(ResultSet rs) throws SQLException {
        return Board.builder()
                .boardId(rs.getInt("board_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .username(rs.getString("username"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}
