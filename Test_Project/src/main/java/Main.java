import models.User;
import services.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("*Qwerty*");
        UserService userService = new UserService();
        List<User> userList = userService.findAllUsers();
    }
}
