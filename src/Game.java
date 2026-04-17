public class Game extends Item
{
    //vars
   
    
    //constructor
    public Game( String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
        super(title, creator,"Game", genre, price, rental_price, stock );
        
    }

   public Game(int itemID,String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
        super(itemID, "Game", title, creator, genre, price, rental_price, stock );
        
    }

}