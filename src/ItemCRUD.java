//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: CRUD FOR ITEM
//DATE: 28/2/2026
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCRUD 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost/librarydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Ellie.1913";


public void insertItem(Item item) throws Exception
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


    public void updateItem(Item item) throws Exception
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
    public void deleteItem(int itemID) throws Exception
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

        public List<Item> displayItems() throws Exception
    {
        List<Item> items = new ArrayList<>(); //list to store items from db 
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        int itemID;
        String title;
        String creator;
        String type;
        String genre;
        double price;
        double rental_price;
        int stock;
        Item item;


        try 
            {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pstat = connection.prepareStatement("SELECT * FROM items");
                resultSet = pstat.executeQuery();
                //title, creator, type, genre, price, rental_price, stock 
                while(resultSet.next())
                    {
                        itemID = resultSet.getInt("itemID");
                        title = resultSet.getString("title");
                        creator = resultSet.getString("creator");
                        type = resultSet.getString("type");
                        genre = resultSet.getString("genre");
                        price = resultSet.getDouble("price");
                        rental_price = resultSet.getDouble("rental_price");
                        stock = resultSet.getInt("stock");


                        //pick object to make using type 
                        if(type.equalsIgnoreCase("Book"))
                            {
                                item = new Book(itemID, title, creator, genre, price, rental_price, stock); //got rid of type as its set in con
                            }

                        else if(type.equalsIgnoreCase("DVD"))
                            {
                                item = new DVD(itemID, title, creator, genre, price, rental_price, stock);
                            }  
                            
                        else if(type.equalsIgnoreCase("Game"))
                            {
                                item = new Game(itemID, title, creator, genre, price, rental_price, stock);
                            }

                        else
                            {
                                item = null; //only valid types added to list 
                            }

                        if (item != null)
                            {
                                items.add(item);
                            }
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
            return items;
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
    //displayItems();
    }
}



