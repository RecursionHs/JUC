package com.hs.mt;

import java.sql.*;

/**
 * @ClassName DbUtils
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/21 下午5:14
 * @Version 1.0
 */
public class DbUtils {
    private static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
    private static final String JDBC_URL="jdbc:oracle:thin:@47.112.197.158:8083/orclpdb1";
    private static final String USER_NAME="CHECKADMS";
    private static final String PASS_WORD="CHECKADMS";

    static {
        try {
            Class.forName(DRIVER_NAME);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立jdbc链接
     * @return
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
        return conn;
    }

    public static void closeConn(Connection conn, Statement statement, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(statement != null){
                statement.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConn());
    }
}
