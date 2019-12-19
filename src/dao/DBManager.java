package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String username = "root";
    private static final String password = "123456";
    private static final String url = "jdbc:mysql://localhost:3306/ecard?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static Connection instance;

    /***
     * 采用单例模式，获取数据库连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    Class.forName("com.mysql.jdbc.Driver");
                   instance=DriverManager.getConnection(url, username, password);
                }
            }
        }
        return instance;
    }

    /**
     * 断开连接
     * @throws SQLException
     */
    public static void disConnection() throws SQLException {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }
}
