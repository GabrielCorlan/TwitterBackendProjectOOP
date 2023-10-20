import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepository {




    public ArrayList<User> findAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = DatabaseConnector.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            String username = rs.getString("username");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String password = rs.getString("account_password");
            users.add(new User(username, firstName, lastName, password));
        }
        return users;
    }

    public void deleteByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

        String templateDeltePost = "DELETE FROM posts WHERE user_id= '%s'";
        statement.executeUpdate(String.format(templateDeltePost, username));

        String templateDeleteUser = "DELETE FROM users WHERE username = '%s'";
        int affectedRows = statement.executeUpdate(String.format(templateDeleteUser, username));

        System.out.println(affectedRows == 1 ? "Userul a fost sters." : "Userul cu usernameul " + username + " nu exista.");
    }

    public User findByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String templateUser = "SELECT * FROM users WHERE username ='%s'";
        ResultSet rs = statement.executeQuery(String.format(templateUser, username));
        rs.next();
        User userCitit = null;
        try {
            String firstNameCitit = rs.getString("first_name");
            String lastNameCitit = rs.getString("last_name");
            String passwordCitit = rs.getString("account_password");
            userCitit = new User(username, firstNameCitit, lastNameCitit, passwordCitit);
        } catch (SQLException e) {
            System.out.println("Userul cu username-ul " + username + " nu exista");
            return null;
        }
        return userCitit;
    }

    public void createUser(String username, String firstName, String lastName, String password) throws SQLException {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
        String templateCreateUser = "INSERT INTO users VALUES('%s','%s','%s','%s')";
        try {
            statement.executeUpdate(String.format(templateCreateUser, username, firstName, lastName, password));
            System.out.println("Userul a fost creat.");
        }catch (SQLException e){
            System.out.println("Exista deja usernameul sau ai introdus un field mai lung de 30 de caractere.");
        }

    }
}
