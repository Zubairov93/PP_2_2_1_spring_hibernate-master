package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User[] userArray = new User[3];
      userArray[0] = new User("Jacque ","Fresco","afterLifeMailBox@gmail.com");
      userArray[1] = new User("Dr", "Livesey", "dr.livesey@gmail.com");
      userArray[2] = new User("Hasbulla", "Magomedov","theBiggest@gmail.com");

      Car car1 = new Car("BMW", 7);
      Car car2 = new Car("VAZ", 21140);
      Car car3 = new Car("Mercedes-Benz S-class", 223);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      int i = 0;
      for (Car car : carService.listCars()) {
         userArray[i].setCar(car);
         userService.add(userArray[i++]);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW",7));

      context.close();
   }
}
