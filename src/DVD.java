//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: OBJECT CLASS - DVD  
//DATE: 28/2/2026
public class DVD extends Item
{
    //vars
   
    
    //constructor
    public DVD(String title, String creator, String genre, double price, double rental_price, int stock) 
    {
        super(title, creator, "DVD", genre, price, rental_price, stock );
        
    }

   public DVD(int itemID, String title, String creator, String genre, double price, double rental_price, int stock) 
    {
        super(itemID, title, creator, "DVD", genre, price, rental_price, stock );
        
    }

}