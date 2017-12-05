package org.workholiday.coffeemaker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Function: 数据库连接工具类
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     20:17 06/07/2017
 * Version:  1.0
 */
public class JdbcUtil {

    private static Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

    private JdbcUtil() {
        // to avoid creating instance
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {

        Connection conn = null;
        String url         = PropertiesUtil.getInstance().getProperty("jdbc.url");
        String driverClass = PropertiesUtil.getInstance().getProperty("jdbc.driver.class");
        String userName    = PropertiesUtil.getInstance().getProperty("jdbc.username");
        String password    = PropertiesUtil.getInstance().getProperty("jdbc.password");

        try {
            Class.forName(driverClass) ;
            conn = DriverManager.getConnection(url, userName, password);

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error acquiring DB connection.", e);
        }

        return conn;

    }

    /**
     * 关闭连接
     * @param conn
     */
    public static void close(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }







}
