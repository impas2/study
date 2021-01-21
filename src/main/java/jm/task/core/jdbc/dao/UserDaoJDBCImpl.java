package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String CREATE_TABLE;
    private final String DROP_TABLE;
    private final String ADD_USER;
    private final String DEL_USER;
    private final String SELECT_ALL;

    {
        CREATE_TABLE = "CREATE TABLE testbase.usertable (id BIGINT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(255)," +
                " lastName VARCHAR(255)," +
                " age TINYINT," +
                " PRIMARY KEY (id))";
        DROP_TABLE = "DROP TABLE testbase.usertable";
        ADD_USER = "INSERT INTO testbase.usertable (name , lastname, age) VALUES (?, ?, ?)";
        DEL_USER = "DELETE FROM testbase.usertable WHERE id = ?";
        SELECT_ALL = "SELECT * FROM testbase.usertable";
    }

    public void createUsersTable() {
        try (Connection connectDB = Util.getJDBCConnection();
             PreparedStatement pS = connectDB.prepareStatement(CREATE_TABLE)) {
            pS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connectDB = Util.getJDBCConnection();
             PreparedStatement pS = connectDB.prepareStatement(DROP_TABLE)) {
            pS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connectDB = Util.getJDBCConnection();
             PreparedStatement pS = connectDB.prepareStatement(ADD_USER)) {
            pS.setString(1,name);
            pS.setString(2, lastName);
            pS.setByte(3,age);
            pS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connectDB = Util.getJDBCConnection();
             PreparedStatement pS = connectDB.prepareStatement(DEL_USER)) {
            pS.setLong(1, id);
            pS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try (Connection connectDB = Util.getJDBCConnection();
             PreparedStatement pS = connectDB.prepareStatement(SELECT_ALL)) {
            ResultSet rS = pS.executeQuery();

            while (rS.next()) {
                User user = new User();
                user.setId(rS.getLong("id"));
                user.setName(rS.getString("name"));
                user.setLastName(rS.getString("lastname"));
                user.setAge(rS.getByte("age"));
                result.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }
}
