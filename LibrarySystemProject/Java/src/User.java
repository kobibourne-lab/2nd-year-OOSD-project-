public class User 
{
 
    private int userID;
    private String name;
    private String email;
    private String phone;

    public User(int userID,  String name, String email, String phone) 
    {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getUserID() 
    {
        return userID;
    }

    public void setUserID(int userID) 
    {
        this.userID = userID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    
    
    
   

    

}
