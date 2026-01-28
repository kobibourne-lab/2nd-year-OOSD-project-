
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OrderCRUD {

    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";

    // Insert an order
    public static void insertOrder(Order order) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("INSERT INTO orders (userID, itemID, orderType, orderDate, returnDate) VALUES (?, ?, ?, ?, ?)");

                pstat.setInt(1, order.getUserID());
                pstat.setInt(2, order.getItemID());
                pstat.setString(3, order.getOrderType());
                pstat.setString(4, order.getOrderDate());
                pstat.setString(5, order.getReturnDate());

                int rowsInserted = pstat.executeUpdate();
                System.out.println(rowsInserted + " record(s) successfully added to the table.");

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

    // Update an order
    public static void updateOrder(Order order) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("UPDATE orders SET userID=?, itemID=?, orderType=?, orderDate=?, returnDate=? WHERE orderID=?");

                pstat.setInt(1, order.getUserID());
                pstat.setInt(2, order.getItemID());
                pstat.setString(3, order.getOrderType());
                pstat.setString(4, order.getOrderDate());
                pstat.setString(5, order.getReturnDate());
                pstat.setInt(6, order.getOrderID());

                int rowsUpdated = pstat.executeUpdate();
                System.out.println(rowsUpdated + " record(s) successfully updated in the table.");

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

    // Delete an order
    public static void deleteOrder(int orderID) 
    {
        Connection connection = null;
        PreparedStatement pstat = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("DELETE FROM orders WHERE orderID=?");
                pstat.setInt(1, orderID);

                int rowsDeleted = pstat.executeUpdate();
                System.out.println(rowsDeleted + " record(s) successfully removed from the table.");

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

    // Display all orders (notes-style)
    public static void displayOrders() 
    {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM orders");
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
        catch (SQLException exception) 
            {
                exception.printStackTrace();
            } 
        finally 
            {
                try 
                    {
                        if (resultSet != null) resultSet.close();
                        if (pstat != null) pstat.close();
                        if (connection != null) connection.close();
                    } 
                catch (Exception exception) 
                    {
                        exception.printStackTrace();
                    }
            }
    }

    // Main method for testing
    public static void main(String[] args) 
    {
        displayOrders();
    }
}
