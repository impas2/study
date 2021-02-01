package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW",1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("VAZ",2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("UAZ",3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ZAZ",4)));


      List<User> users = userService.listUsers();
      System.out.println();
      for (User user : users) {
         System.out.println(user);
      }
      System.out.println();

      List<User> usersByCar = userService.getUserByCar("BMW", 1);
      System.out.println();
      for (User user : usersByCar) {
         System.out.println(user);
      }

      context.close();
   }
}
