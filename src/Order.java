//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: OBJECT CLASS - ORDER  
//DATE: 28/2/2026
public class Order 
{

    private int orderID;
    private int userID;
    private int itemID;
    private String orderType;
    private String orderDate;
    private String returnDate;
    private String userName;  //for inner join display
    private String itemTitle;

    public Order(int userID, int itemID, String orderType, String orderDate, String returnDate) 
    {
        this.userID = userID;
        this.itemID = itemID;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
    }

    public Order(int orderID, int userID, int itemID, String orderType, String orderDate, String returnDate) 
    {
        this.orderID = orderID;
        this.userID = userID;
        this.itemID = itemID;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
    }

    
    public Order(int orderID, int userID, String userName, int itemID, String itemTitle,
                 String orderType, String orderDate, String returnDate) 
    {
        this.orderID = orderID;
        this.userID = userID;
        this.userName = userName;    // 
        this.itemID = itemID;
        this.itemTitle = itemTitle;  //
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
    }

    public int getOrderID() 
    {
        return orderID;
    }

    public void setOrderID(int orderID) 
    {
        this.orderID = orderID;
    }

    public int getUserID() 
    {
        return userID;
    }

    public void setUserID(int userID) 
    {
        this.userID = userID;
    }

    public int getItemID() 
    {
        return itemID;
    }

    public void setItemID(int itemID) 
    {
        this.itemID = itemID;
    }

    public String getOrderType() 
    {
        return orderType;
    }

    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate( String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getReturnDate() 
    {
        return returnDate;
    }

    public void setReturnDate(String returnDate)
    {
        this.returnDate = returnDate;
    }

    public String getUserName() 
    { 
        return userName; 
    }
    public String getItemTitle() 
    { 
        return itemTitle; 
    }


    @Override
    public String toString() 
    {
        return "Order [orderID=" + orderID + ", userID=" + userID + ", itemID=" + itemID + ", orderType=" + orderType
        + ", returnDate=" + returnDate + "]";
    }

}
