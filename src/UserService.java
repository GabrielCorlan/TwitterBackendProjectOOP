import javafx.geometry.Pos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();

    private Scanner cititorText = new Scanner(System.in);

    public void readAll() throws SQLException {
        ArrayList<User> users = userRepository.findAll();
        for (User user : users) {
            ArrayList<Post> postariCitite = postRepository.readPostsFrom(user.getUsername()); //todo read from database
            user.setPosts(postariCitite);
        }
        printAllUsers(users);
    }


    public void deleteUserByUsername() throws SQLException {
        System.out.println("Introduceti username-ul:");
        String username = cititorText.nextLine();
        userRepository.deleteByUsername(username);
    }


    public void readByUsername() throws SQLException {
        System.out.println("Introduceti username-ul:");
        String username = cititorText.nextLine();
        User userCitit = userRepository.findByUsername(username);
        if (userCitit != null) {
            ArrayList<Post> postarileUserului = postRepository.readPostsFrom(userCitit.getUsername());
            userCitit.setPosts(postarileUserului);
            System.out.println(userCitit);
        }
    }

    private void printAllUsers(ArrayList<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void createUser() throws SQLException {
        System.out.println("Introduceti un username:");
        String username = cititorText.nextLine();
        System.out.println("Introduceti first name-ul:");
        String firstName = cititorText.nextLine();
        System.out.println("Introduceti last name-ul:");
        String lastName = cititorText.nextLine();
        System.out.println("Introduceti parola:");
        String password = cititorText.nextLine();
        userRepository.createUser(username,firstName,lastName,password);
    }
}
