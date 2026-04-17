import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ItemCRUD 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";


public static void insertItem(Item item)
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("INSERT INTO items (title, creator, type, genre, price, rental_price, stock) VALUES (?,?,?,?,?,?,?)");
            
                
                pstat.setString(1, item.getTitle());
                pstat.setString(2, item.getCreator());
                pstat.setString(3, item.getType());
                pstat.setString(4, item.getGenre());
                pstat.setDouble(5, item.getPrice());
                pstat.setDouble(6, item.getRental_price());
                pstat.setInt(7, item.getStock());
                
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


    public static void updateItem(Item item)
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("UPDATE items SET title=?, creator=?, type=?, genre=?, price=?, rental_price=?, stock=? WHERE itemID=?");

                
                pstat.setString(1, item.getTitle());
                pstat.setString(2, item.getCreator());
                pstat.setString(3, item.getType());
                pstat.setString(4, item.getGenre());
                pstat.setDouble(5, item.getPrice());
                pstat.setDouble(6, item.getRental_price());
                pstat.setInt(7, item.getStock());
                pstat.setInt(8, item.getItemID());

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
    public static void deleteItem(int itemID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("DELETE FROM items WHERE itemID=?");
                pstat.setInt(1, itemID);

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

        public static void displayItems() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM items");
                resultSet = pstat.executeQuery();

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                // Print column headers
                for (int i = 1; i <= numberOfColumns; i++) 
                    {
                        System.out.print(metaData.getColumnName(i) + "\t");
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
    displayItems();
    }
}



