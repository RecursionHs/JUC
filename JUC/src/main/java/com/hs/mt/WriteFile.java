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
                Integer countNum = getCountNum(conn, "EXE_WO");
                int ccNum = countNum / maxCyc;
                int yNum = countNum % maxCyc;

                for (int i = 0; i < maxCyc; i++) {
                    String sql = "select * from (select ROWNUM as cnn,t.* from EXE_WO t) p where p.cnn >= ? and p.cnn <?";
                    int tempi = i;
                    poolExecutor.execute(()->{
                        Connection connT = null;
                        try {
                            int addNum = 0;
                            if(tempi == maxCyc){
                                addNum = tempi*ccNum + yNum;
                            }else {
                                addNum = (tempi+1)*ccNum;
                            }

                            //connT = DbUtils.getConn();
                            PreparedStatement pstmt = connT.prepareStatement(sql);
                            pstmt.setString(1,String.valueOf(tempi * ccNum));
                            pstmt.setString(2,String.valueOf(addNum));
                            ResultSet resultSet = pstmt.executeQuery();
                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            for (int j = 1; j < columnCount; j++) {
                                System.out.print(metaData.getColumnName(j) + " ");
                            }
                            System.out.println();
                            while (resultSet.next()){
                                for (int j = 0; j < columnCount; j++) {
                                    System.out.print(resultSet.getString(j+1) + " ");
                                }
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            DbUtils.closeConn(connT,null,null);
                        }

                    });
                }

                poolExecutor.shutdown();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
          } finally {
            DbUtils.closeConn(conn,null,null);
        }

    }

    public static Integer getCountNum(Connection conn,String tab){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(1) from  " + tab);
            while (resultSet.next()){
                int num = Integer.valueOf(resultSet.getString(1));
                System.out.println("获取总表总量为: " + num);
                return num;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
}
class exeSqlTask implements Runnable{

    @Override
    public void run() {

    }
}
