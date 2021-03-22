package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car5 = new Car("s", 6);
      User user5 = new User("User1", "Lastname1", "user1@mail.ru", car5);
      userService.add(user5);

      Car car6 = new Car("bad", 99);
      User user6 = new User("User1", "Lastname1", "user1@mail.ru", car6);
      userService.add(user6);

      Car car7 = new Car("cool", 1);
      User user7 = new User("User1", "Lastname1", "user1@mail.ru", car7);
      userService.add(user7);

      Car car8 = new Car("best", 2);
      User user8 = new User("User1", "Lastname1", "user1@mail.ru", car8);
      userService.add(user8);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());

         System.out.println();
      }

      List<Car> cars = userService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println("Car user = "+car.getUser());

         System.out.println();
      }

      List<User> usersWithCars = userService.getUserByCar("best", 2);
      for (User user : usersWithCars) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car ID = "+user.getCar().getId());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println("Car model = "+user.getCar().getModel());

         System.out.println();
      }

      context.close();
   }
}
