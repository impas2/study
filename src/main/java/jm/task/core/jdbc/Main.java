package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String testName = "Ivan";
        String testLastName = "Ivanov";
        byte testAge = 5;

        // реализуйте алгоритм здесь
        UserService userServiceInterface = new UserServiceImpl();
        userServiceInterface.dropUsersTable();
        userServiceInterface.createUsersTable();

        for(int counter = 0; counter < 4; counter++) {
            String uName = testName + counter;
            String uLastName = testLastName + counter;
            byte uAge = (byte)(testAge + counter);
            userServiceInterface.saveUser(uName, uLastName, uAge);
            System.out.println(String.format("User с именем –  %s добавлен в базу данных", uName));
        }

        List<User> list = userServiceInterface.getAllUsers();

        for(User u : list) {
            System.out.println(u);
        }
        userServiceInterface.cleanUsersTable();
        userServiceInterface.dropUsersTable();

    }
}
