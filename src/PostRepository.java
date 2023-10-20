import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostRepository {

    public ArrayList<Post> readPostsFrom(String username) throws SQLException {
        ArrayList<Post> posts = new ArrayList<>();
        Connection connection = DatabaseConnector.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String template = "SELECT * FROM posts WHERE user_id= '%s'";
        ResultSet rs = statement.executeQuery(String.format(template,username));
        while(rs.next()){
            int id = rs.getInt("id");
            String message = rs.getString("message");
            int year = rs.getInt("post_year");
            int month = rs.getInt("post_month");
            int day = rs.getInt("post_day");
            int hour = rs.getInt("post_hour");
            int minute = rs.getInt("post_minute");
            String userId= rs.getString("user_id");
            Post post = new Post(id, message, LocalDateTime.of(year,month,day,hour,minute), userId);
            posts.add(post);
        }
        return posts;
    }

}
