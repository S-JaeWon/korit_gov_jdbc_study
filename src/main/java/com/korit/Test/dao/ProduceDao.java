package com.korit.Test.dao;

import com.korit.Test.entity.Produce;
import com.korit.Test.util.ConnectionFactory;

import java.sql.*;

public class ProduceDao {
    private static ProduceDao instance;
    private ProduceDao() {};
    public static ProduceDao getInstance() {
        if (instance == null) {
            instance = new ProduceDao();
        }
        return instance;
    }

    public int add(Produce produce) {
        String sql = "insert into produce_tb(produce_id, name, price, color, create_dt) values (0, ?, ?, ?, now())";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, produce.getName());
            ps.setInt(2, produce.getPrice());
            ps.setString(3, produce.getColor());

            int updateInt = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    produce.setProduceId(id);
                }
            }
            return updateInt;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Produce getProduce(String name) {
        String sql = "select produce_id, name, price, color, create_dt from produce_tb where name = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? toProduce(rs) : null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Produce toProduce(ResultSet rs) throws SQLException {
        return Produce.builder()
                .produceId(rs.getInt("produce_id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .color(rs.getString("color"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }

}
