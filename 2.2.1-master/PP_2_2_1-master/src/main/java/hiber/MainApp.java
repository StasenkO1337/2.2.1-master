package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("s1", 1);
      Car car2 = new Car("s2", 2);
      Car car3 = new Car("s3", 3);

      User user1 =new User("User1", "Lastname1", "user1@mail.ru",car1);
      User user2 =new User("User2", "Lastname2", "user2@mail.ru",car2);
      User user3 =new User("User3", "Lastname3", "user3@mail.ru",car3);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println(user.toString());

      }

      System.out.println(userService.getUserByCar("s1", 1).toString());

      context.close();
   }
}
