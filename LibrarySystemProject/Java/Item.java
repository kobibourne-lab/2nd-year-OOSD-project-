public abstract class Item 
{
 
    private int itemID;
    private String type;
    private String title;
    private String creator;
    private String genre;
    private double price;
    private double rentalPrice;
    private int stock;

    public Item(int itemID, String type, String title, String creator, String genre, double price, double rentalPrice, int stock) 
    {
        this.itemID = itemID;
        this.type = type;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.price = price;
        this.rentalPrice = rentalPrice;
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

    public int getID() 
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


    public double getRentalPrice() 
    {
        return rentalPrice;
    }


    public void setRentalPrice(double rentalPrice) 
    {
        this.rentalPrice = rentalPrice;
    }


    public int getStock() 
    {
        return stock;
    }


    public void setStock(int stock) 
    {
        this.stock = stock;
    }


    @Override
    public String toString() 
    {
        return "Item [itemID=" + itemID + ", type=" + type + ", title=" + title + ", creator=" + creator + ", genre="
        + genre + ", price=" + price + ", rentalPrice=" + rentalPrice + ", stock=" + stock + "]";
    }

    

}
