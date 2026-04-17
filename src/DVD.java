public class DVD extends Item
{
    //vars
   
    
    //constructor
    public DVD(String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
        super(title, creator, "DVD", genre, price, rental_price, stock );
        
    }

   public DVD(int itemID, String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
        super(itemID, title, creator, "DVD", genre, price, rental_price, stock );
        
    }

}