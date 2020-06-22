package com.hs.mt;

import java.sql.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    private static final int maxPoolSize = 100;
    private static List<Connection> pool = Collections.synchronizedList(new LinkedList<>());


    static {
        try {
            Class.forName(DRIVER_NAME);
            createConn(5);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 建立jdbc链接
     * @return
     * @throws SQLException
     */
    public synchronized static Connection getConn() throws SQLException {
        if(pool.size() > 0){
            Iterator<Connection> iterator = pool.iterator();
            while (iterator.hasNext()){
                Connection connection = iterator.next();
                if(connection != null){
                    iterator.remove();
                    return connection;
                }
            }
        }
        return  null;
    }

    public static List<Connection> createConn(int connNum) throws SQLException {
        for (int i = 0; i < connNum; i++) {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS_WORD);
            pool.add(conn);
            System.out.println("添加连接:" + i);
        }
        return pool;
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
                pool.add(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConn());
        System.out.println(getConn());
        System.out.println(getConn());
        System.out.println(getConn());
    }
}
