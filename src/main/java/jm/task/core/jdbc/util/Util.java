package jm.task.core.jdbc.util;
import java.sql.*;


public class Util {

    private Util() {
    }
    // реализуйте настройку соеденения с БД
    private static final String connectUrl = "jdbc:mysql://localhost/testbase?serverTimezone=Europe/Moscow&useSSL=false";

    private static final String userDB = "hiberUser";
    private static final String pwdDB = "hiberUser";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectUrl, userDB, pwdDB);
    }

}
