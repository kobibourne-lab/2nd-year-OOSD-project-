//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: GUI FOR ORDERS 
//DATE: 5/4/2026
import javax.swing.*; //imports swing things 
import javax.swing.table.DefaultTableModel; //table 
import java.awt.*; // layouts
import java.awt.event.*; // event handling 
import java.util.List; // list

public class OrderGUI extends JFrame 
{
    // Component vars declarations
    private JLabel userIDLabel, itemIDLabel, orderTypeLabel, orderDateLabel, returnDateLabel; 
    private JTextField userIDField, itemIDField, orderTypeField, orderDateField, returnDateField; 
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, backButton, clearButton;
    private OrderCRUD orderCRUD; //object for crud

    // Constructor
    public OrderGUI() 
        {
            // Setup frame
            super("Order Management");  // set title
            setSize(700, 500); // set window size
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close this window 
            setLayout(new BorderLayout()); //set layout

            ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
            setIconImage(icon.getImage());

            orderCRUD = new OrderCRUD(); // Initialize crud object

            
            JPanel formPanel = new JPanel(); // form panel and layout
            formPanel.setLayout(new GridLayout(5, 2, 5, 5)); // rows cols and spacing 

            //labels
            userIDLabel = new JLabel("User ID:"); 
            itemIDLabel = new JLabel("Item ID:");
            orderTypeLabel = new JLabel("Order Type:");
            orderDateLabel = new JLabel("Order Date:");
            returnDateLabel = new JLabel("Return Date:");

            //text fields
            userIDField = new JTextField(); 
            itemIDField = new JTextField();
            orderTypeField = new JTextField();
            orderDateField = new JTextField();
            returnDateField = new JTextField();

            //add labels + fields to form 
            formPanel.add(userIDLabel);
            formPanel.add(userIDField);
            formPanel.add(itemIDLabel);
            formPanel.add(itemIDField);
            formPanel.add(orderTypeLabel);
            formPanel.add(orderTypeField);
            formPanel.add(orderDateLabel);
            formPanel.add(orderDateField);
            formPanel.add(returnDateLabel);
            formPanel.add(returnDateField);

            
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
            tableModel.addColumn("Order ID"); // id col 
            tableModel.addColumn("User ID");
            tableModel.addColumn("Item ID");
            tableModel.addColumn("Order Type");
            tableModel.addColumn("Order Date");
            tableModel.addColumn("Return Date");

            orderTable = new JTable(tableModel); //make table
            JScrollPane scrollPane = new JScrollPane(orderTable);  //make scrollable 

            // add panels to frame
            add(formPanel, BorderLayout.NORTH); // add form to top 
            add(scrollPane, BorderLayout.CENTER); //add table in center 
            add(buttonPanel, BorderLayout.SOUTH); // add button at bottom

            
        
            // make + reg event handlers
            ClickHandler clickHandler = new ClickHandler();
            AddHandler addHandler = new AddHandler();
            UpHandler upHandler = new UpHandler();
            DelHandler delHandler = new DelHandler();
            BackHandler backHandler = new BackHandler();
            ClearHandler clearHandler = new ClearHandler();
            
            // Reg handlers
            orderTable.addMouseListener(clickHandler);
            addButton.addActionListener(addHandler);
            updateButton.addActionListener(upHandler);
            deleteButton.addActionListener(delHandler);
            backButton.addActionListener(backHandler);
            clearButton.addActionListener(clearHandler);
            
            displayOrderTable(); //load data into table 
            setLocationRelativeTo(null); // center window 
            setVisible(true); //shows
        } // end constructor

    // load orders from db into table 
    public void displayOrderTable() 
        {
            try 
                {
                    tableModel.setRowCount(0); //clear 
                    List<Order> orders = orderCRUD.displayOrders(); //get orders from db 

                    for (int i = 0; i < orders.size(); i++) //loop thru orders 
                        {
                            Order order = orders.get(i); //gets each one
                            tableModel.addRow(new Object[]  // add rows
                            {   //col val
                                order.getOrderID(), order.getUserID(), order.getItemID(),
                                order.getOrderType(), order.getOrderDate(), order.getReturnDate()
                            });
                        }
                } 
            catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        }

 
    // event handling classes
    //Table Click Handler
    private class ClickHandler extends MouseAdapter 
        {
            @Override
            public void mouseClicked(MouseEvent event) 
                {
                    int row = orderTable.getSelectedRow(); //gets selected row 
                    if (row != -1)  // Check valid row selected
                        {
                            //fill fields 
                            userIDField.setText(tableModel.getValueAt(row, 1).toString());
                            itemIDField.setText(tableModel.getValueAt(row, 2).toString());
                            orderTypeField.setText(tableModel.getValueAt(row, 3).toString());
                            orderDateField.setText(tableModel.getValueAt(row, 4).toString());
                            //returnDateField.setText(tableModel.getValueAt(row, 5).toString());
                            returnDateField.setText(String.valueOf(tableModel.getValueAt(row, 5)));
                            
                        }
                }
        } // end class

    //Add Button Handler
    private class AddHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    //get inputs
                    String userID = userIDField.getText(); 
                    String itemID = itemIDField.getText();
                    String orderType = orderTypeField.getText();
                    String orderDate = orderDateField.getText();
                    String returnDate = returnDateField.getText();

                    // input validation
                    if (userID.isEmpty() || itemID.isEmpty() || orderType.isEmpty() || orderDate.isEmpty()) //if any empty 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); //print
                            return; // back to gui screen
                        }

                    try 
                        {
                            int userIDInt = Integer.parseInt(userID); //convert to int
                            int itemIDInt = Integer.parseInt(itemID); //convert to int
                            Order order = new Order(userIDInt, itemIDInt, orderType, orderDate, returnDate); //create order object
                            
                            orderCRUD.insertOrder(order); // add to db
                            displayOrderTable(); //load table again
                            JOptionPane.showMessageDialog(null, "Order Added"); //print if works 
                            
                            // Clear fields
                            userIDField.setText("");
                            itemIDField.setText("");
                            orderTypeField.setText("");
                            orderDateField.setText("");
                            returnDateField.setText("");
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
                    String userID = userIDField.getText();
                    String itemID = itemIDField.getText();
                    String orderType = orderTypeField.getText();
                    String orderDate = orderDateField.getText();
                    String returnDate = returnDateField.getText();
                   
                    // input validation
                    if (userID.isEmpty() || itemID.isEmpty() || orderType.isEmpty() || orderDate.isEmpty()) //if any empty 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); //print
                            return; // back to gui screen
                        }
                    try 
                        {
                            int row = orderTable.getSelectedRow(); //sel row 

                            if (row == -1) //if not row selected  
                                {
                                    JOptionPane.showMessageDialog(null, "Select an order"); //print 
                                    return; //stop
                                }
                            
                            //get order data 
                            int orderID = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); 
                            int userIDInt = Integer.parseInt(userID); //convert to int
                            int itemIDInt = Integer.parseInt(itemID); //convert to int
                            Order order = new Order(orderID, userIDInt, itemIDInt, orderType, orderDate, returnDate); //make updated order
                            
                            orderCRUD.updateOrder(order); //update db using crud 
                            displayOrderTable(); //reload table
                            JOptionPane.showMessageDialog(null, "Order Updated"); // if works print 
                            
                            // Clear fields
                            userIDField.setText("");
                            itemIDField.setText("");
                            orderTypeField.setText("");
                            orderDateField.setText("");
                            returnDateField.setText("");
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage()); //if error print 
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
                            int row = orderTable.getSelectedRow(); // sel row 

                            if (row == -1) //if no row selected 
                                {
                                    JOptionPane.showMessageDialog(null, "Select an order"); // print 
                                    return; // stop (back to selecting)
                                }

                            int orderID = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); //get id 
                            
                            // Confirmation dialog
                                int confirm = JOptionPane.showConfirmDialog(null, 
                                "Are you sure you want to delete this order?", //message
                                "Confirm Delete", //title 
                                JOptionPane.YES_NO_OPTION //buttons
                                );
                            
                            if (confirm == JOptionPane.YES_OPTION) //if yes 
                                {
                                    orderCRUD.deleteOrder(orderID);//delete order from db
                                    displayOrderTable(); // reload table 
                                    JOptionPane.showMessageDialog(null, "Order Deleted"); // print 
                                    
                                    // Clear fields
                                    userIDField.setText("");
                                    itemIDField.setText("");
                                    orderTypeField.setText("");
                                    orderDateField.setText("");
                                    returnDateField.setText("");
                                }
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage());
                        }
                }
        } // end class 

    //Back Button Handler
    private class BackHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    MainMenu menu = new MainMenu(); //new menu object
                    menu.setVisible(true);  //show menu
                    dispose(); //close item gui 
                }
        } // end class 

    
    private class ClearHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                userIDField.setText("");
                itemIDField.setText("");
                orderTypeField.setText(""); //clears fields
                orderDateField.setText("");
                returnDateField.setText("");

                orderTable.clearSelection(); // unclicks row
            }
        }


    // main method - run gui from this class
    public static void main(String[] args) 
        {
            new OrderGUI(); //run gui - for testing 
        }

} // end class