//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: CRUD FOR USER
//DATE: 28/2/2026
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

     // Delete a user
    public void deleteUser(int userID) 
    {
        try 
            {
                Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

                // delete order first
                PreparedStatement pstat1 = connection.prepareStatement("DELETE FROM orders WHERE userID=?");
                pstat1.setInt(1, userID);
                pstat1.executeUpdate();

                //delete user after orders using userID 
                PreparedStatement pstat2 = connection.prepareStatement( "DELETE FROM users WHERE userID=?");
                pstat2.setInt(1, userID);
                pstat2.executeUpdate();

            } 
        catch (Exception e) 
            {
                e.printStackTrace();
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

                while(resultSet.next())
                {
                    id = resultSet.getInt("userID");
                    name = resultSet.getString("name");
                    email = resultSet.getString("email");
                    phone = resultSet.getString("phone");

                    User user = new User(id, name, email, phone);
                    users.add(user);
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
        return users;
    }


    public boolean isDupUser(String email) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
                pstat.setString(1, email);
                resultSet = pstat.executeQuery();

                if (resultSet.next()) 
                    {
                        return resultSet.getInt(1) > 0;
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
        return false;
    }
    // Main method for testing
    public void main(String[] args) 
    {
    //User newUser = new User();
    //UserCRUD.insertUser(newUser);
    //User updatedUser = new User(name, email, phone);
    //USERCRUD.updateUser(updatedUser);
    //UserCRUD.deleteUser(1);
    //displayUsers();
    }
    
}
