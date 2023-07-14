package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {

    Long id;
    String firstName;
    String lastName;
    String phone;
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Type the command: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE -> {
                    firstName = prompt("Name: ");
                    lastName = prompt("Surname: ");
                    phone = prompt("Phone number: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                }
                case READ -> {
                    id = Long.parseLong(prompt("User id: "));
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case LIST -> {
                    try {
                        List<User> users = userController.readAllUsers();
                        System.out.println(users);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case UPDATE -> {
                    try {
                        id = Long.parseLong(prompt("User id: "));
                        firstName = prompt("Name: ");
                        lastName = prompt("Surname: ");
                        phone = prompt("Phone number: ");
                        User updateUser = new User(firstName, lastName, phone);
                        userController.updateUser(id, updateUser);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case DELETE -> {
                    try {
                        id = Long.parseLong(prompt("User id: "));
                        List<User> users = userController.readAllUsers();
                        userController.deleteUser(users, id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case FIND_ID -> {
                    id = Long.parseLong(prompt("User id: "));
                    List<User> users = userController.readAllUsers();
                    userController.findById(users, id);
                }
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
