package com.korit.util;

import com.korit.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    /*
    * 정적 초기화 블록
    * -> 클래스가 처음으로 로딩이 될 때 단 한 번만 실행 된다.
    * */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * DriverManager.getConnection()은 매번 새로운 연결을 만든다.
    * Connection은 값이 아니라 DB와의 세션/자원이므로 반드시 사용이 끝나면 close()를 해줘야함
    * 멤버 변수로 등록 해두고 오래 들고 있으면 안됨.
    *
    * SQLException -> 연결실패 예외
    * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
    }
}
