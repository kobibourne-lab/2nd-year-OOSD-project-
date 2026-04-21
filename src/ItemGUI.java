//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: GUI FOR ITEMS 
//DATE: 5/4/2026
import javax.swing.*; //imports swing things
import javax.swing.table.DefaultTableModel;  //table
import java.awt.*; //layouts
import java.awt.event.*; // event handling
import java.util.List; //list

public class ItemGUI extends JFrame 
{
    // Component vars declarations
    private JLabel titleLabel, creatorLabel, typeLabel, genreLabel, priceLabel, rentalPriceLabel, stockLabel; 
    private JTextField titleField, creatorField, genreField, priceField, rentalPriceField, stockField; 
    private JComboBox<String> typeCombo; //dropdown for type much easier then textfield with validation
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, backButton, clearButton;
    private ItemCRUD itemCRUD;  //object for crud

    public ItemGUI() // Constructor
        {
            //setup frame
            super("Item Management");  //set title 
            setSize(700, 500); // set window size 
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close this window
            setLayout(new BorderLayout()); //set layout
            itemCRUD = new ItemCRUD(); // Initialize crud object

            ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
            setIconImage(icon.getImage());

            JPanel formPanel = new JPanel(); // form panel and layout
            formPanel.setLayout(new GridLayout(7, 2, 5, 5)); // rows cols and spacing 

            //labels
            titleLabel = new JLabel("Title:"); 
            creatorLabel = new JLabel("Creator:");
            typeLabel = new JLabel("Type:");
            genreLabel = new JLabel("Genre:");
            priceLabel = new JLabel("Price:");
            rentalPriceLabel = new JLabel("Rental Price:");
            stockLabel = new JLabel("Stock:");

            //text fields
            titleField = new JTextField(); 
            creatorField = new JTextField();
            typeCombo = new JComboBox<>(new String[]{"Book", "DVD", "Game"});// book = index 0 etc
            genreField = new JTextField();
            priceField = new JTextField();
            rentalPriceField = new JTextField();
            stockField = new JTextField();

            //add labels + fields to form
            formPanel.add(titleLabel);
            formPanel.add(titleField);
            formPanel.add(creatorLabel);
            formPanel.add(creatorField);
            formPanel.add(typeLabel);
            formPanel.add(typeCombo);
            formPanel.add(genreLabel);
            formPanel.add(genreField);
            formPanel.add(priceLabel);
            formPanel.add(priceField);
            formPanel.add(rentalPriceLabel);
            formPanel.add(rentalPriceField);
            formPanel.add(stockLabel);
            formPanel.add(stockField);

            JPanel buttonPanel = new JPanel(); //button panel 
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
            tableModel = new DefaultTableModel(); // makes table
            tableModel.addColumn("Item ID"); //id col 
            tableModel.addColumn("Title"); //title col 
            tableModel.addColumn("Creator");
            tableModel.addColumn("Type");
            tableModel.addColumn("Genre");
            tableModel.addColumn("Price");
            tableModel.addColumn("Rental Price");
            tableModel.addColumn("Stock");

            itemTable = new JTable(tableModel); //make table
            JScrollPane scrollPane = new JScrollPane(itemTable);  //make scrollable

            // add panels to frame
            add(formPanel, BorderLayout.NORTH); //add form to top 
            add(scrollPane, BorderLayout.CENTER); //add table in center 
            add(buttonPanel, BorderLayout.SOUTH); // add button at bottom

            Color bg = new Color(220, 235, 245); // make color 
            formPanel.setBackground(bg); // set form panel colour
            buttonPanel.setBackground(bg); // set button panel colour

            // make + reg event handlers            
            ClickHandler clickHandler = new ClickHandler();
            AddHandler addHandler = new AddHandler();
            UpHandler upHandler = new UpHandler();
            DelHandler delHandler = new DelHandler();
            BackHandler backHandler = new BackHandler();
            ClearHandler clearHandler = new ClearHandler();
            
            // Reg handlers
            itemTable.addMouseListener(clickHandler);
            addButton.addActionListener(addHandler);
            updateButton.addActionListener(upHandler);
            deleteButton.addActionListener(delHandler);
            backButton.addActionListener(backHandler);
            clearButton.addActionListener(clearHandler);
            
            displayItemTable(); //load data into table 
            setLocationRelativeTo(null); //center window 
            setVisible(true); // show window 
        } //end con  

        //clears data
        private void clearFields()//only used in this as code was v long
        {
            titleField.setText("");
            creatorField.setText("");
            typeCombo.setSelectedIndex(0); //resets to default dropdown option , book 
            genreField.setText("");
            priceField.setText("");
            rentalPriceField.setText("");
            stockField.setText("");
        }

    public void displayItemTable() // load orders from db into table 
        {
            //Item item; cant be here?
            try 
                {
                    tableModel.setRowCount(0); //clear
                    List<Item> items = itemCRUD.displayItems(); //get items from db

                    for (int i = 0; i < items.size(); i++) //loop thru items
                        {
                            Item item = items.get(i); //get each one
                            tableModel.addRow(new Object[] //add rows 
                            {   //col values
                                item.getItemID(), item.getTitle(), item.getCreator(), item.getType(),
                                item.getGenre(), item.getPrice(), item.getRental_price(), item.getStock()
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
            @Override //dont think i need but safer 
            public void mouseClicked(MouseEvent event) 
                {
                    int row = itemTable.getSelectedRow(); //get row 

                    if (row != -1)  // check row valid 
                        {   //sets fields 
                            titleField.setText(tableModel.getValueAt(row, 1).toString());//sets
                            creatorField.setText(tableModel.getValueAt(row, 2).toString());
                            typeCombo.setSelectedItem(tableModel.getValueAt(row, 3).toString()); //get val from table col 3, convert to string , sets dropdown 
                            genreField.setText(tableModel.getValueAt(row, 4).toString());
                            priceField.setText(tableModel.getValueAt(row, 5).toString());
                            rentalPriceField.setText(tableModel.getValueAt(row, 6).toString());
                            stockField.setText(tableModel.getValueAt(row, 7).toString());
                        }
                }
        } 

    //Add Button Handler
    private class AddHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {   //get inputs
                    String title = titleField.getText(); 
                    String creator = creatorField.getText();
                    String type = typeCombo.getSelectedItem().toString(); //get selected type from dropdown and converts to string 
                    String genre = genreField.getText();
                    String price = priceField.getText();
                    String rental = rentalPriceField.getText();
                    String stock = stockField.getText();

                    //inpiut validation - if any field empty enter if
                    if (title.isEmpty() || creator.isEmpty() || type.isEmpty() || genre.isEmpty() || price.isEmpty() || rental.isEmpty() || stock.isEmpty()) 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); 
                            return; //back to gui 
                        }

                    if (!price.matches(".*\\d.*")) //only digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        }
                    if (!rental.matches(".*\\d.*")) //only digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        } 
                    if (!stock.matches(".*\\d.*")) //noly digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        } 
                    if (itemCRUD.isDupItem(title)) 
                        {
                            JOptionPane.showMessageDialog(null,"An item with this title already exists!", 
                            "Duplicate Item", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    
                    try 
                        {
                            double priceVal = Double.parseDouble(price); //convert to double 
                            double rentalVal = Double.parseDouble(rental); //convert to doube 
                            int stockVal = Integer.parseInt(stock); // convert to int 
                            Item item;//declare new item object

                            if (type.equals("Book"))
                                {
                                    item = new Book(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equals("DVD"))
                                {
                                    item = new DVD(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else  //(type == "Game")
                                {
                                    item = new Game(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
      
                            itemCRUD.insertItem(item); //add to db 
                            displayItemTable(); // relaod table 
                            JOptionPane.showMessageDialog(null, "Item Added");
                            clearFields(); // clear fields 
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage());
                        }
                }
        } 

    private class UpHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {

                    //get inputs
                    String title = titleField.getText(); 
                    String creator = creatorField.getText();
                    String type = typeCombo.getSelectedItem().toString(); //get selected type from dropdown and converts to string 
                    String genre = genreField.getText();
                    String price = priceField.getText();
                    String rental = rentalPriceField.getText();
                    String stock = stockField.getText();

                    //inpiut validation - if any field empty enter if
                    if (title.isEmpty() || creator.isEmpty() || type.isEmpty() || genre.isEmpty() || price.isEmpty() || rental.isEmpty() || stock.isEmpty()) 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); 
                            return; //back to gui 
                        }

                    if (!price.matches(".*\\d.*")) //only digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        }
                    if (!rental.matches(".*\\d.*")) //only digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        } 
                    if (!stock.matches(".*\\d.*")) //noly digits from 0-9
                        {
                            JOptionPane.showMessageDialog(null, "phone can't contain letters");
                            return;
                        } 
                    if (itemCRUD.isDupItem(title)) 
                        {
                            JOptionPane.showMessageDialog(null,"An item with this title already exists!", 
                            "Duplicate Item", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    try 
                        {
                            int row = itemTable.getSelectedRow(); 

                            if (row == -1) 
                                {
                                    JOptionPane.showMessageDialog(null, "Select item"); 
                                    return; 
                                }
                            
                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); 
                            double priceVal = Double.parseDouble(price); //convert to double 
                            double rentalVal = Double.parseDouble(rental); //convert to doube 
                            int stockVal = Integer.parseInt(stock); // convert to int 
                            Item item;//declare new item object 

                            if (type.equals("Book")) // if type = book 
                                {
                                    //make new item = book 
                                    item = new Book(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equals("DVD")) // if type = dvd
                                {
                                    item = new DVD(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else //(type.equalsIgnoreCase("Game")) // if type = game 
                                {
                                    item = new Game(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
   
                            itemCRUD.updateItem(item);//add to db 
                            displayItemTable();//reload table 
                            JOptionPane.showMessageDialog(null, "Item Updated");
                            clearFields();//clear 
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage());
                        }
                }
        } 

    //Delete Button Handler class 
    private class DelHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    try 
                        {
                            int row = itemTable.getSelectedRow(); //selected row 

                            if (row == -1) //if no row selected 
                                {
                                    JOptionPane.showMessageDialog(null, "Select item"); //print
                                    return;  //back to selecting
                                }

                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); //get id

                            // Confirm deletion
                            int confirm = JOptionPane.showConfirmDialog(null, 
                            "Are you sure you want to delete this item?", //message
                            "Confirm Delete", //title 
                            JOptionPane.YES_NO_OPTION); //buttons

                            if (confirm == JOptionPane.YES_OPTION) //if yes 
                                {    
                                    itemCRUD.deleteItem(id);
                                    displayItemTable();
                                    JOptionPane.showMessageDialog(null, "Item Deleted");
                                    clearFields();
                                }
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage()); //if error print 
                        }
                }
        } 

    //back button handler 
    private class BackHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    MainMenu menu = new MainMenu(); //new menu object
                    menu.setVisible(true);  //show menu
                    dispose(); //close item gui 
                }
        } 

    private class ClearHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                clearFields(); //clears fields

                itemTable.clearSelection();// unclicks row
            }
        }

    
    

    // main method - run gui from this class
    public static void main(String[] args) 
        {
            new ItemGUI(); //run gui 
        }
}