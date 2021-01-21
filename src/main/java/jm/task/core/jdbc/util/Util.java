package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    private static SessionFactory factory;

    private static final String connectUrl = "jdbc:mysql://localhost/testbase?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String userDB = "hiberUser";
    private static final String pwdDB = "hiberUser";

    private Util() {
    }
    // реализуйте настройку соеденения с БД

    public static Connection getJDBCConnection() throws SQLException {


        return DriverManager.getConnection(connectUrl, userDB, pwdDB);
    }

    public static Session getSession() {

        if (factory == null) {
            Properties properties = new Properties();
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.USER, userDB);
            properties.setProperty(Environment.PASS, pwdDB);
            properties.setProperty(Environment.URL, connectUrl);

            Configuration cfg = new Configuration();
            cfg.setProperties(properties);
            cfg.addAnnotatedClass(User.class);
            factory = cfg.buildSessionFactory();
        }
    return factory.openSession();
    }



}
