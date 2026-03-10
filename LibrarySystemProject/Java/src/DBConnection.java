import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/librarydb"; // change db name
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ellie.1913";
    

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}




