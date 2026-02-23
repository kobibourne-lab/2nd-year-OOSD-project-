import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserCRUD 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";


public static void insertUser(User user)
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("INSERT INTO users (name, email, phone) VALUES (?,?,?)");
            
                
                pstat.setString(1, user.getName());
                pstat.setString(2, user.getEmail());
                pstat.setString(3, user.getPhone());
                
                int rowsInserted = pstat.executeUpdate();
                System.out.println(rowsInserted + " record(s) added");
            }
        catch (SQLException exception) 
            {
                exception.printStackTrace();
            } 
        finally 
            {
                try 
                    {
                        if (pstat != null) pstat.close();
                        if (connection != null) connection.close();
                    } 
                catch (Exception exception) 
                    {
                        exception.printStackTrace();
                    }
            }
    }


    public static void updateUser(User user)
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("UPDATE users SET name=?, email=?, phone=? WHERE userID=?");

                
                pstat.setString(1, user.getName());
                pstat.setString(2, user.getEmail());
                pstat.setString(3, user.getPhone());
                pstat.setInt(4, user.getUserID());

                int rowsUpdated = pstat.executeUpdate();
                System.out.println(rowsUpdated + "records updated");

            }

        catch (SQLException e) 
            {
                e.printStackTrace();
            } 
        finally 
        {
            try 
                {
                    if (pstat != null) pstat.close();
                    if (connection != null) connection.close();
                } 
            catch (Exception e) 
                {
                   e.printStackTrace();
                }
        }

    }

     // Delete an item
    public static void deleteUser(int userID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("DELETE FROM users WHERE userID=?");
                pstat.setInt(1, userID);

                int rowsDeleted = pstat.executeUpdate();
                System.out.println(rowsDeleted + " record(s) successfully removed from the table.");

            } 
        catch (SQLException e) 
            {
                e.printStackTrace();
            } 
        finally 
        {
            try 
                {
                    if (pstat != null) pstat.close();
                    if (connection != null) connection.close();
                } 
            catch (Exception e) 
                {
                   e.printStackTrace();
                }
        }
    }

    public static void displayUsers() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM users");
                resultSet = pstat.executeQuery();

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                // Print column headers
                for (int i = 1; i <= numberOfColumns; i++) 
                    {
                        System.out.print(metaData.getColumnName(i) + "  \t  ");
                    }
                System.out.println();

                // Print rows
                while (resultSet.next()) 
                    {
                        for (int i = 1; i <= numberOfColumns; i++) 
                            {
                                System.out.print(resultSet.getObject(i) + "\t");
                            }
                        System.out.println();
                    }

            } 
        catch (SQLException e) 
            {
                e.printStackTrace();
            } 
        finally 
            {
                try 
                    {
                        if (resultSet != null) resultSet.close();
                        if (pstat != null) pstat.close();
                        if (connection != null) connection.close();
                    } 
                catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
            }
    }

    // Main method for testing
    public static void main(String[] args) 
    {
        

    //DVD newDVD = new DVD("8mile", "eminem", "DVD", "drama", 12, 4, 3);
    //ItemCRUD.insertItem(newDVD);

    // UPDATE
    //DVD updatedDVD = new DVD(1001, "Star Wars", "George.L", "DVD", "Sifi", 20, 12, 4);
    //ItemCRUD.updateItem(updatedDVD);

    // DELETE
    //OrderCRUD.deleteOrder(2003);
    displayUsers();
    }
    
}
