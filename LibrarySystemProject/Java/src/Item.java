public abstract class Item 
{
 
    private int itemID;
    private String type;
    private String title;
    private String creator;
    private String genre;
    private double price;
    private double rental_price;
    private int stock;

    public Item(String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
    
        this.title = title;
        this.creator = creator;
        this.type = type;
        this.genre = genre;
        this.price = price;
        this.rental_price = rental_price;
        this.stock = stock;
    }

    public Item(int itemID,String title, String creator, String type, String genre, double price, double rental_price, int stock) 
    {
        this.itemID = itemID;
        this.title = title;
        this.creator = creator;
        this.type = type;
        this.genre = genre;
        this.price = price;
        this.rental_price = rental_price;
        this.stock = stock;
    }


    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getItemID() 
    {
        return itemID;
    }

    public void setItemID(int itemID) 
    {
        this.itemID = itemID;
    }


    public String getCreator() 
    {
        return creator;
    }


    public void setCreator(String creator) 
    {
        this.creator = creator;
    }


    public String getGenre() 
    {
        return genre;
    }


    public void setGenre(String genre) 
    {
        this.genre = genre;
    }


    public double getPrice() 
    {
        return price;
    }


    public void setPrice(double price) 
    {
        this.price = price;
    }


    public double getRental_price() 
    {
        return rental_price;
    }


    public void setRental_price(double rental_price) 
    {
        this.rental_price = rental_price;
    }


    public int getStock() 
    {
        return stock;
    }


    public void setStock(int stock) 
    {
        this.stock = stock;
    }

    

    public String getTitle() 
    {
        return title;
    }


    public void setTitle(String title) 
    {
        this.title = title;
    }


    @Override
    public String toString() 
    {
        return "Item [itemID=" + itemID + ", creator=" + creator + ", title=" + title + ", type=" + type + ", genre="
        + genre + ", price=" + price + ", rentalPrice=" + rental_price + ", stock=" + stock + "]";
    }

    

}
