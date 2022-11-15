package com.asurplus.myutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SqlUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/asurplus-vue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useSSL=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String pwd = "6666";

    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet res = null;

    private static final Logger log = LoggerFactory.getLogger(SqlUtil.class);


    public static void executeUpdate(String sql) {
        int count;
        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
            stmt = conn.prepareStatement(sql);
            count = stmt.executeUpdate();
            log.info("更新记录条数：" + count);
        } catch (ClassNotFoundException | SQLException e) {
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
    }

    public static int executeQuery(String sql) {
        int num = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();
            if (res.next()) {
                num = res.getInt("status");
                return num;
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        return num;
    }
}
