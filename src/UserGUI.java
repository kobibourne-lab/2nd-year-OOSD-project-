//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: GUI FOR USERS 
//DATE: 5/4/2026
import javax.swing.*; //imports swing things 
import javax.swing.table.DefaultTableModel; //table 
import java.awt.*; // layouts
import java.awt.event.*; // event handling 
import java.util.List; // list

public class UserGUI extends JFrame 
{
    // Component vars declarations
    private JLabel nameLabel, emailLabel, phoneLabel; 
    private JTextField nameField, emailField, phoneField; 
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, backButton, clearButton;
    private UserCRUD userCRUD; //object for crud

    // Constructor
    public UserGUI() 
        {
            // Setup frame
            super("User Management");  // set title
            setSize(600, 500); // set window size
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close this window 
            setLayout(new BorderLayout()); //set layout

        
            userCRUD = new UserCRUD(); // Initialize crud object

            
            JPanel formPanel = new JPanel(); // form panel and layout
            formPanel.setLayout(new GridLayout(3, 2, 5, 5)); // rows cols and spcaing 

            //labels
            nameLabel = new JLabel("Name:"); 
            emailLabel = new JLabel("Email:");
            phoneLabel = new JLabel("Phone:");

            //text fields
            nameField = new JTextField(); 
            emailField = new JTextField();
            phoneField = new JTextField();

            //add labels + fields to form 
            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(emailLabel);
            formPanel.add(emailField);
            formPanel.add(phoneLabel);
            formPanel.add(phoneField);

            
            JPanel buttonPanel = new JPanel(); // button panel 

            //make buttons 
            addButton = new JButton("Add");
            updateButton = new JButton("Update");
            deleteButton = new JButton("Delete");
            backButton = new JButton("Back");
            clearButton = new JButton("Clear");

            //add buttons to panel
            buttonPanel.add(addButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(backButton);
            buttonPanel.add(clearButton);

            // table setup 
            tableModel = new DefaultTableModel(); //makes table
            tableModel.addColumn("ID"); // id col 
            tableModel.addColumn("Name");
            tableModel.addColumn("Email");
            tableModel.addColumn("Phone");

            userTable = new JTable(tableModel); //make table
            JScrollPane scrollPane = new JScrollPane(userTable);  //make scrollable 

            // add panels to frame
            add(formPanel, BorderLayout.NORTH); // aad form to center 
            add(scrollPane, BorderLayout.CENTER); //add table in top 
            add(buttonPanel, BorderLayout.SOUTH); // add button at bottom

            
        
            // make + reg event handlers
            ClickHandler clickHandler = new ClickHandler();
            AddHandler addHandler = new AddHandler();
            UpHandler upHandler = new UpHandler();
            DelHandler delHandler = new DelHandler();
            BackHandler backHandler = new BackHandler();
            ClearHandler clearHandler = new ClearHandler();
            
            // Reg handlers
            userTable.addMouseListener(clickHandler);
            addButton.addActionListener(addHandler);
            updateButton.addActionListener(upHandler);
            deleteButton.addActionListener(delHandler);
            backButton.addActionListener(backHandler);
            clearButton.addActionListener(clearHandler);
            
            displayUserTable(); //load data into table 

            // show frame
            setLocationRelativeTo(null); // center window 
            setVisible(true); //shows

        } // end constructor

    // load users from db into table 
    public void displayUserTable() 
        {
            try 
                {
                    tableModel.setRowCount(0); //clear 
                    List<User> users = userCRUD.displayUsers(); //get users from db 

                    for (int i = 0; i < users.size(); i++) //loop thru users 
                        {
                            User user = users.get(i); //gets each one
                            tableModel.addRow(new Object[]  // add rows
                            {
                                user.getUserID(), //col val
                                user.getName(),
                                user.getEmail(),
                                user.getPhone()
                            });
                        }
                } 
            catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Error loading data");
                }
        }

 
    // event handling classes
    //Table Click Handler
    private class ClickHandler extends MouseAdapter 
        {
            @Override
            public void mouseClicked(MouseEvent event) 
                {
                    int row = userTable.getSelectedRow(); //gets selected row 
                    if (row != -1)  // Check valid row selected
                        {
                            //fill fields 
                            nameField.setText(tableModel.getValueAt(row, 1).toString());
                            emailField.setText(tableModel.getValueAt(row, 2).toString());
                            phoneField.setText(tableModel.getValueAt(row, 3).toString());
                        }
                }
        } // end ckass

    //Add Button Handler
    private class AddHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    //get inputs
                    String name = nameField.getText(); 
                    String email = emailField.getText();
                    String phone = phoneField.getText();

                    // input validation
                    if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) //if any empty 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); //print
                            return; // back to gui screen
                        }
                        
                    if (!email.contains("@")) //if email does not contain @ enter if
                        {
                            JOptionPane.showMessageDialog(null, "Email must contain @");
                            return;
                        }

                    try 
                        {
                            User user = new User(name, email, phone); //create user object
                            userCRUD.insertUser(user); // add to db
                            displayUserTable(); //load table again
                            JOptionPane.showMessageDialog(null, "User Added"); //print if works 
                            
                            // Clear fields
                            nameField.setText("");
                            emailField.setText("");
                            phoneField.setText("");
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, "Error: " + err.getMessage());
                        }
                }
        } // end class add

        //Update Button Handler
    private class UpHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    try 
                        {
                            int row = userTable.getSelectedRow(); //sel row 

                            if (row == -1) //if not row selected  
                                {
                                    JOptionPane.showMessageDialog(null, "Select a user"); //print 
                                    return; //stop
                                }
                            
                            //get user data 
                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); 
                            String name = nameField.getText();
                            String email = emailField.getText();
                            String phone = phoneField.getText();

                            User user = new User(id, name, email, phone); //make updated user
                            userCRUD.updateUser(user); //update db using crud 
                            displayUserTable(); //relaod table
                            JOptionPane.showMessageDialog(null, "User Updated"); // if works print 
                            
                            // Clear fields
                            nameField.setText("");
                            emailField.setText("");
                            phoneField.setText("");
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, "Error: " + err.getMessage()); //if error print 
                        }
                }
        } // end class 

    //Delete Button Handler
    private class DelHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    try 
                        {
                            int row = userTable.getSelectedRow(); // sel row 

                            if (row == -1) //if now row selected 
                                {
                                    JOptionPane.showMessageDialog(null, "Select a user"); // print 
                                    return; // stop (back to selecting )
                                }

                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); //get id 
                            
                            // Confirmation dialog
                            int confirm = JOptionPane.showConfirmDialog(
                                null, 
                                "Are you sure you want to delete this user?", //messgae
                                "Confirm Delete", //title 
                                JOptionPane.YES_NO_OPTION //buttons
                            );
                            
                            if (confirm == JOptionPane.YES_OPTION) //if yes 
                                {
                                    userCRUD.deleteUser(id);//delete user from db
                                    displayUserTable(); // reload table 
                                    JOptionPane.showMessageDialog(null, "User Deleted"); // print 
                                    
                                    // Clear fields
                                    nameField.setText("");
                                    emailField.setText("");
                                    phoneField.setText("");
                                }
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, "Error: " + err.getMessage());
                        }
                }
        } // end class 

    //Back Button Handler
    private class BackHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    MainMenu menu = new MainMenu();
                    menu.setVisible(true);
                    dispose();
                    //new MainMenu(); //if back clicked back to main menu 
                    //dispose(); //close just current window 
                }
        } // end class 

    
    private class ClearHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");

                userTable.clearSelection();
            }
        }


    // main method - run gui from this class
    public static void main(String[] args) 
        {
            new UserGUI(); //run gui - for testing 
        }

} // end class