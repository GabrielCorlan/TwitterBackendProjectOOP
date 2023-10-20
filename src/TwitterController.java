import java.sql.SQLException;
import java.util.Scanner;

public class TwitterController {

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerNumbers = new Scanner(System.in);

    static UserService userService = new UserService();

    public static void main(String[] args) throws SQLException {

        while(true){
            chooseBranch();
        }

    }

    public static void chooseBranch() throws SQLException {
        System.out.println("Alegeti flowul:");
        String flow = scannerText.nextLine();
        switch (flow){
            case "users":
                startUsersFlow();
                break;
            case "posts":
                startPostsFlow();
                break;
            default:
                System.out.println("Only 'users' / 'posts' accepted.");
        }
    }

    public static void startUsersFlow() throws SQLException {
        System.out.println("Alegeti operatia :  readAll / readById / create / update / delete");
        String crudOperation = scannerText.nextLine();
        switch (crudOperation){
            case "readAll":
                 userService.readAll();
                break;
            case "readById":
                userService.readByUsername();
                break;
            case "create":
                userService.createUser();
                break;
            case "update":

                break;
            case "delete":
                 userService.deleteUserByUsername();
                break;
            default:
                System.out.println("Unsupported operation");
        }
    }

    public static void startPostsFlow(){
        System.out.println("Alegeti operatia :  readAll / readById / create / update / delete");

    }

}
