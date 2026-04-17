import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.List; 

public class ItemGUI extends JFrame 
{
    private JLabel titleLabel, creatorLabel, typeLabel, genreLabel, priceLabel, rentalPriceLabel, stockLabel; 
    private JTextField titleField, creatorField, typeField, genreField, priceField, rentalPriceField, stockField; 
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, backButton, clearButton;
    private ItemCRUD itemCRUD; 

    public ItemGUI() 
        {
            super("Item Management");  
            setSize(700, 500); 
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            setLayout(new BorderLayout());

            itemCRUD = new ItemCRUD(); 

            JPanel formPanel = new JPanel(); 
            formPanel.setLayout(new GridLayout(7, 2, 5, 5)); 

            titleLabel = new JLabel("Title:"); 
            creatorLabel = new JLabel("Creator:");
            typeLabel = new JLabel("Type:");
            genreLabel = new JLabel("Genre:");
            priceLabel = new JLabel("Price:");
            rentalPriceLabel = new JLabel("Rental Price:");
            stockLabel = new JLabel("Stock:");

            titleField = new JTextField(); 
            creatorField = new JTextField();
            typeField = new JTextField();
            genreField = new JTextField();
            priceField = new JTextField();
            rentalPriceField = new JTextField();
            stockField = new JTextField();

            formPanel.add(titleLabel);
            formPanel.add(titleField);
            formPanel.add(creatorLabel);
            formPanel.add(creatorField);
            formPanel.add(typeLabel);
            formPanel.add(typeField);
            formPanel.add(genreLabel);
            formPanel.add(genreField);
            formPanel.add(priceLabel);
            formPanel.add(priceField);
            formPanel.add(rentalPriceLabel);
            formPanel.add(rentalPriceField);
            formPanel.add(stockLabel);
            formPanel.add(stockField);

            JPanel buttonPanel = new JPanel(); 

            addButton = new JButton("Add");
            updateButton = new JButton("Update");
            deleteButton = new JButton("Delete");
            backButton = new JButton("Back");
            clearButton = new JButton("Clear");

            buttonPanel.add(addButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(backButton);
            buttonPanel.add(clearButton);

            tableModel = new DefaultTableModel(); 
            tableModel.addColumn("Item ID"); 
            tableModel.addColumn("Title");
            tableModel.addColumn("Creator");
            tableModel.addColumn("Type");
            tableModel.addColumn("Genre");
            tableModel.addColumn("Price");
            tableModel.addColumn("Rental Price");
            tableModel.addColumn("Stock");

            itemTable = new JTable(tableModel); 
            JScrollPane scrollPane = new JScrollPane(itemTable);  

            add(formPanel, BorderLayout.NORTH); 
            add(scrollPane, BorderLayout.CENTER); 
            add(buttonPanel, BorderLayout.SOUTH);

            ClickHandler clickHandler = new ClickHandler();
            AddHandler addHandler = new AddHandler();
            UpHandler upHandler = new UpHandler();
            DelHandler delHandler = new DelHandler();
            BackHandler backHandler = new BackHandler();
            ClearHandler clearHandler = new ClearHandler();
            
            itemTable.addMouseListener(clickHandler);
            addButton.addActionListener(addHandler);
            updateButton.addActionListener(upHandler);
            deleteButton.addActionListener(delHandler);
            backButton.addActionListener(backHandler);
            clearButton.addActionListener(clearHandler);
            
            displayItemTable(); 

            setLocationRelativeTo(null); 
            setVisible(true);

        } 

        private void clearFields()
        {
            titleField.setText("");
            creatorField.setText("");
            typeField.setText("");
            genreField.setText("");
            priceField.setText("");
            rentalPriceField.setText("");
            stockField.setText("");
        }

    public void displayItemTable() 
        {
            try 
                {
                    tableModel.setRowCount(0); 
                    List<Item> items = itemCRUD.displayItems(); 

                    for (int i = 0; i < items.size(); i++) 
                        {
                            Item item = items.get(i); 
                            tableModel.addRow(new Object[] 
                            {
                                item.getItemID(), 
                                item.getTitle(),
                                item.getCreator(),
                                item.getType(),
                                item.getGenre(),
                                item.getPrice(),
                                item.getRental_price(),
                                item.getStock()
                            });
                        }
                } 
            catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        }

    private class ClickHandler extends MouseAdapter 
        {
            @Override
            public void mouseClicked(MouseEvent event) 
                {
                    int row = itemTable.getSelectedRow(); 
                    if (row != -1)  
                        {
                            titleField.setText(tableModel.getValueAt(row, 1).toString());
                            creatorField.setText(tableModel.getValueAt(row, 2).toString());
                            typeField.setText(tableModel.getValueAt(row, 3).toString());
                            genreField.setText(tableModel.getValueAt(row, 4).toString());
                            priceField.setText(tableModel.getValueAt(row, 5).toString());
                            rentalPriceField.setText(tableModel.getValueAt(row, 6).toString());
                            stockField.setText(tableModel.getValueAt(row, 7).toString());
                        }
                }
        } 

    private class AddHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    String title = titleField.getText(); 
                    String creator = creatorField.getText();
                    String type = typeField.getText();
                    String genre = genreField.getText();
                    String price = priceField.getText();
                    String rental = rentalPriceField.getText();
                    String stock = stockField.getText();

                    if (title.isEmpty() || creator.isEmpty() || type.isEmpty() || genre.isEmpty() || price.isEmpty() || rental.isEmpty() || stock.isEmpty()) 
                        {
                            JOptionPane.showMessageDialog(null, "Fill all fields"); 
                            return; 
                        }

                    if (!type.equalsIgnoreCase("Book") && !type.equalsIgnoreCase("DVD") && !type.equalsIgnoreCase("Game")) //if email does not contain @ enter if
                        {
                            JOptionPane.showMessageDialog(null, "Type must be - Book, Game, or DVD");
                            return;
                        }

                    try 
                        {
                            double priceVal = Double.parseDouble(price);
                            double rentalVal = Double.parseDouble(rental);
                            int stockVal = Integer.parseInt(stock);

                            Item item;

                            if (type.equalsIgnoreCase("Book"))
                                {
                                    item = new Book(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equalsIgnoreCase("DVD"))
                                {
                                    item = new DVD(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equalsIgnoreCase("Game"))
                                {
                                    item = new Game(title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else
                                {
                                    JOptionPane.showMessageDialog(null, "Type must be Book, DVD or Game");
                                    return;
                                }

                            itemCRUD.insertItem(item);
                            displayItemTable();
                            JOptionPane.showMessageDialog(null, "Item Added");
                            
                            clearFields();
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
                    try 
                        {
                            int row = itemTable.getSelectedRow(); 

                            if (row == -1) 
                                {
                                    JOptionPane.showMessageDialog(null, "Select item"); 
                                    return; 
                                }
                            
                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); 
                            String title = titleField.getText();
                            String creator = creatorField.getText();
                            String type = typeField.getText();
                            String genre = genreField.getText();
                            double priceVal = Double.parseDouble(priceField.getText());
                            double rentalVal = Double.parseDouble(rentalPriceField.getText());
                            int stockVal = Integer.parseInt(stockField.getText());

                            Item item;

                            if (type.equalsIgnoreCase("Book"))
                                {
                                    item = new Book(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equalsIgnoreCase("DVD"))
                                {
                                    item = new DVD(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else if (type.equalsIgnoreCase("Game"))
                                {
                                    item = new Game(id, title, creator, genre, priceVal, rentalVal, stockVal);
                                }
                            else
                                {
                                    JOptionPane.showMessageDialog(null, "Type must be Book, DVD or Game");
                                    return;
                                }

                            itemCRUD.updateItem(item);
                            displayItemTable();
                            JOptionPane.showMessageDialog(null, "Item Updated");
                            
                            clearFields();
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage());
                        }
                }
        } 

    private class DelHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    try 
                        {
                            int row = itemTable.getSelectedRow(); 

                            if (row == -1) 
                                {
                                    JOptionPane.showMessageDialog(null, "Select item"); 
                                    return; 
                                }

                            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); 

                            itemCRUD.deleteItem(id);
                            displayItemTable();
                            JOptionPane.showMessageDialog(null, "Item Deleted");
                            
                            clearFields();
                        } 
                    catch (Exception err) 
                        {
                            JOptionPane.showMessageDialog(null, err.getMessage());
                        }
                }
        } 

    private class BackHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
                {
                    MainMenu menu = new MainMenu();
                    menu.setVisible(true);
                    dispose();
                }
        } 

    private class ClearHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                clearFields();

                itemTable.clearSelection();
            }
        }

    public static void main(String[] args) 
        {
            new ItemGUI(); 
        }

}