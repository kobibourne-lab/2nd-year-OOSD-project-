import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";


public void insertUser(User user) throws SQLException
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


    public void updateUser(User user) throws SQLException //was static , changed for gui
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
    public void deleteUser(int userID) throws Exception
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

    public List<User> displayUsers() throws Exception
    {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        int id;
        String name;
        String email;
        String phone;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM users");
                resultSet = pstat.executeQuery();

                while (resultSet.next())
                {
                    id = resultSet.getInt("userID");
                    name = resultSet.getString("name");
                    email = resultSet.getString("email");
                    phone = resultSet.getString("phone");

                    User user = new User(id, name, email, phone);
                    users.add(user);
                }

                /*ResultSetMetaData metaData = resultSet.getMetaData();
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
                    }*/

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
            return users;
    }

    // Main method for testing
    public void main(String[] args) 
    {
        

    //User newUser = new User();
    //UserCRUD.insertUser(newUser);

    // UPDATE
    //User updatedUser = new User(name, email, phone);
    //USERCRUD.updateUser(updatedUser);

    // DELETE
    //UserCRUD.deleteUser(1);
    //displayUsers();
    }
    
}
