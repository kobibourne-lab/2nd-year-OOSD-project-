public class Book extends Item
{
    //vars
   
    
    //constructor
    public Book(int itemID, String title, String creator, String genre, double price, double rentalPrice, int stock) 
    {
        super(itemID, title, creator,"Book", genre, price, rentalPrice, stock );
        
    }

    public Book(String title, String creator, String genre, double price, double rental_price, int stock) 
    {
        super(title, creator, "Book", genre, price, rental_price, stock );
        
    }

}