
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderCRUD {

    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";

    // Insert an order
    public void insertOrder(Order order) throws SQLException
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

    // Update an order
    public void updateOrder(Order order) throws SQLException
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

    // Delete an order
    public void deleteOrder(int orderID) throws SQLException
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

    // Display all orders
    public List<Order> displayOrders() throws SQLException
    {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        int userId;
        int itemId;
        String orderType;
        String orderDate; //never used date idk 
        String returnDate;

        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM orders");
                resultSet = pstat.executeQuery();

                
                while(resultSet.next())
                    {
                        userId = resultSet.getInt("userID");
                        itemId = resultSet.getInt("itemID");
                        orderType = resultSet.getString("orderType");
                        orderDate = resultSet.getString("orderDate");
                        returnDate = resultSet.getString("returnDate");

                        Order order = new Order(userId, itemId, orderType, orderDate, returnDate);
                        orders.add(order);
                    }
                    
                
                /*ResultSetMetaData metaData = resultSet.getMetaData();
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
            return orders;
    }

    // Main method for testing
    /*public static void main(String[] args) throws Exception
    {
        OrderCRUD orderCRUD = new OrderCRUD();

        // INSERT
        Order newOrder = new Order(3, 1003, "BORROW", "2026-02-22", "2026-03-22");
        orderCRUD.insertOrder(newOrder);

        // UPDATE
        Order updatedOrder = new Order(2002, 2, 1001, "RETURN", "2026-02-22", "2026-02-25");
        orderCRUD.updateOrder(updatedOrder);

        // DELETE
        // orderCRUD.deleteOrder(2003);

        // DISPLAY
        orderCRUD.displayOrders();
    }*/

}
