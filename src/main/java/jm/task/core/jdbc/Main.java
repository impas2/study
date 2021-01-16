package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        String testName = "Ivan";
        String testLastName = "Ivanov";
        byte testAge = 5;

        // реализуйте алгоритм здесь
        UserServiceImpl uS = new UserServiceImpl();
        uS.dropUsersTable();
        uS.createUsersTable();

        for(int counter = 0; counter < 4; counter++) {
            String uName = testName + counter;
            String uLastName = testLastName + counter;
            byte uAge = (byte)(testAge + counter);
            uS.saveUser(uName, uLastName, uAge);
            System.out.println(String.format("User с именем –  %s добавлен в базу данных", uName));

        }

        List<User> list = uS.getAllUsers();

        for(User u : list) {
            System.out.println(u);
        }

        uS.cleanUsersTable();
        uS.dropUsersTable();

    }
}
