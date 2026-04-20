//STUDENT NAME: KOBI BOURNE
//STUDENT NUM: C00249676
//CLASS: MAIN MENU GUI 
//DATE: 5/4/2026
import javax.swing.*; //for buttons frames and labels
import java.awt.*; // for layouts - grid , flow
import java.awt.event.*; // for actionListener 

public class MainMenu extends JFrame //window
{

    private JButton itemButton; //button declarations
    private JButton userButton;
    private JButton orderButton;
    //private JButton exitButton;

    public MainMenu() //constructor
    {
        //window details - text shown, size of window, layout
        super("Library System Menu");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit when click the x 
        setLayout(new GridLayout(3, 1, 20, 5)); // used instead of flow so they would stack
        //JFrame.DISPOSE_ON_CLOSE - closes only window ur on
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
        setIconImage(icon.getImage());
        
        //JLabel logo = new JLabel(new ImageIcon("logo.png"));
        //add(logo, BorderLayout.NORTH);
        

        // make buttons
        itemButton = new JButton("Manage Items"); //button + text on button
        userButton = new JButton("Manage Users");
        orderButton = new JButton("Manage Orders");
        //exitButton = new JButton("Exit");

        // Add buttons to frame
        add(itemButton); //adds the buttoin to the window 
        add(userButton); // if skipped button would nopt appear 
        add(orderButton);
        //add(exitButton); - redudant with close on click of x 

        // make + reg event handlers
        ItemHandler itemHandler = new ItemHandler();
        UserHandler userHandler = new UserHandler();
        OrderHandler orderHandler = new OrderHandler();

        // Reg handlers
        itemButton.addActionListener(itemHandler);
        userButton.addActionListener(userHandler);
        orderButton.addActionListener(orderHandler);
        
    } //end con


     // Event handling
     //item button
        private class ItemHandler implements ActionListener 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ItemGUI gui = new ItemGUI();
                gui.setVisible(true); //makes + shows window
                dispose(); //closes menu , item gui opens
                //JOptionPane.showMessageDialog(null, "Go to Item GUI");
            }
        }

        private class UserHandler implements ActionListener 
        {
            public void actionPerformed(ActionEvent e) 
            {
                UserGUI gui = new UserGUI();
                gui.setVisible(true); //makes + shows window
                dispose(); //closes menu , user gui opens
                //JOptionPane.showMessageDialog(null, "Go to User GUI");
            }
        }

         //Order Button 
        private class OrderHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent e) 
            {
                OrderGUI gui = new OrderGUI();
                gui.setVisible(true); //makes + shows window
                dispose(); //closes menu , order gui opens
                //JOptionPane.showMessageDialog(null, "Go to Order GUI");
            }
        }

         /*exitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0); // 
            }
        });*/
    

    public static void main(String[] args) //put here while coding for testing, might make its own class 
    {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }
}