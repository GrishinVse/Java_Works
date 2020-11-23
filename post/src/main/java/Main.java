import models.Auto;
import models.User;
import services.AutoService;
import services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        UserService userService = new UserService();
        User user = new User("Misha", 26);
        userService.saveUser(user);
        System.out.println("Save user = " + user);

        User user1 = new User("Vitya", 28);
        userService.saveUser(user1);
        System.out.println("Save user = " + user1);

        User user2 = new User("Olya", 25);
        userService.saveUser(user2);
        System.out.println("Save user = " + user2);

        List<User> userList = userService.findAllUsers();
        for (User userFor:userList) {
            System.out.println(userFor);
        }

        // Создание автомобиля без привзки к владельцу
        AutoService autoService = new AutoService();
        Auto auto = new Auto("Mercedes-Benz","black");
        autoService.saveAuto(auto);
        //auto.setUser(user);
        System.out.println("Save auto = " + auto);
        //autoService.updateAuto(auto);

        Auto auto1 = new Auto("BMW", "blue");
        autoService.saveAuto(auto1);
        System.out.println("Save auto = " + auto1);

        Auto auto2 = new Auto("Opel", "green");
        autoService.saveAuto(auto2);
        System.out.println("Save auto = " + auto2);

        Auto auto3 = new Auto("Ford", "yellow");
        autoService.saveAuto(auto3);
        System.out.println("Save auto = " + auto3);

        Auto auto4 = new Auto("Toyota", "silver");
        autoService.saveAuto(auto4);
        System.out.println("Save auto = " + auto4);

        AutoService autoService1 = new AutoService();
        System.out.println("*********");
        List<Auto> list_of_autos = autoService1.findAllAutos();
        for (Auto autoFor:list_of_autos) {
            System.out.println(autoFor);
        }

        // User
        user.addAuto(auto);
        auto.setUser(user);
        userService.updateUser(user);
        autoService.updateAuto(auto);

        // User 1
        List<Auto> list_of_autos1 = new ArrayList<>();
        list_of_autos1.addAll(Arrays.asList(auto2, auto4));
        user1.setAutoList(list_of_autos1);

        auto2.setUser(user1);
        autoService.updateAuto(auto2);
        auto4.setUser(user1);
        autoService.updateAuto(auto4);
        userService.updateUser(user1);

        // User 2
        List<Auto> list_of_autos2 = new ArrayList<>();
        list_of_autos1.addAll(Arrays.asList(auto1, auto3));
        user2.setAutoList(list_of_autos2);

        auto1.setUser(user2);
        autoService.updateAuto(auto1);
        auto3.setUser(user2);
        autoService.updateAuto(auto3);
        userService.updateUser(user2);
    }
}
