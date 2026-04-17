import java.sql.Connection;

public class TestConnection 
{
    public static void main(String[] args) 
    {
        try
            {
                Connection conn = DBConnection.getConnection();
                System.out.println("Connected to database!");
                conn.close();
            } 
        catch (Exception e) 
            {
                e.printStackTrace();
            }
    }
}
