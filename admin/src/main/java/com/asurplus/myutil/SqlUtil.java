package com.asurplus.myutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/asurplus-vue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useSSL=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String pwd = "6666";

    private static Connection conn = null;
    private static Statement stmt = null;


    public static int insertUtil(String sql) {
        int count = 0;
        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
            stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);
            System.out.println("更新记录条数：" + count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }
}
