package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "MArkin",  (byte)33);
        userService.saveUser("Sergej", "Formin",  (byte)34);
        userService.saveUser("Nail", "Sad",  (byte)35);
        userService.saveUser("Dimi", "Weseloski",  (byte)40);
        List<User> list = userService.getAllUsers();
        for(User user : list) {
            System.out.println(user);
        }
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
