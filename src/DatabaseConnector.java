import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseConnector {

    private final String url =
            "jdbc:mysql://localhost:3306/twitterFinal?useSSL=false&serverTimezone="+ TimeZone.getDefault().getID();
    private final String user= "root";
    private final String password = "Academia2020";

    private static DatabaseConnector connector;

    private DatabaseConnector(){}

    public static DatabaseConnector getInstance(){
        if (connector==null){
            connector= new DatabaseConnector();
        }
        return connector;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

}
