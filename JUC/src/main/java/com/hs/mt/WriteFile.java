package com.hs.mt;

import java.sql.*;
import java.util.concurrent.*;

/**
 * @ClassName WriteFile
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/21 下午5:05
 * @Version 1.0
 */
public class WriteFile {
    public static void main(String[] args) {
        int num  = 500;
        int maxThread = 10;
        int maxCyc = 5;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(maxThread/2, maxThread, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());

        CyclicBarrier cb = new CyclicBarrier(maxCyc);
        Connection conn = null;


            try {
                conn = DbUtils.getConn();

                String sql = "select * from EXE_WO t where rownum<=900";
                PreparedStatement prepareStatement = conn.prepareStatement(sql);
                ResultSet resultSet = prepareStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i < columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + " ");
                }
                System.out.println();
                while (resultSet.next()){
                    for (int i = 0; i < columnCount; i++) {
                        System.out.print(resultSet.getString(i+1) + " ");
                    }
                    System.out.println();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
          } finally {
            DbUtils.closeConn(conn,null,null);
        }

    }
    
}
