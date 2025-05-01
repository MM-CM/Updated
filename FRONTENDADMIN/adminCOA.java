/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.UserService;
    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class adminCOA extends parentComponent {
    private JPanel pan5 = new JPanel();
    private JPanel pan6 = new JPanel();
    private JPanel pan7 = new JPanel();
    public JPanel mainPanel = new JPanel();
    private JLabel amlbl = new JLabel("Account Management");
    private JTable accountTable = new JTable();
    private JScrollPane accountTableScp = new JScrollPane(accountTable);
    
    private JTextField searchField = new JTextField();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JTextField contact = new JTextField();
    private JTextField role = new JTextField();
    
    JLabel usern = new JLabel("Username");
    JLabel passw = new JLabel("Password");
    JLabel cont = new JLabel("Contact");
    JLabel rol = new JLabel("Role");
    ImageIcon searchIMG = new ImageIcon("images\\search.png");
    
    private JButton exit = new JButton("Exit");
    private JButton searchbtn = new JButton("Search");
    private JButton addAccount = new JButton("Add");
    private JButton updAccount = new JButton("Update ");
    private JButton deleteAccount = new JButton("Delete");
    private JButton unblockAccount = new JButton("Unblock"); // no function yet
//    private JButton changePassword = new JButton("Update");
    private JButton backbtnCOA = new JButton("Back");
    private JComboBox<String> userCategory = new JComboBox<>(new String[]{"All Accounts", "Librarian Accounts", "Admin Accounts", "Blocked Accounts"});
    
    
    
    private static String[] columnsForAccounts = {"Member ID", "Username", "Password", "Contact", "Role"};
    
    
//    private static DefaultTableModel accountTableModel = new DefaultTableModel(columnsForAccounts, 0);
    DefaultTableModel model = new DefaultTableModel(columnsForAccounts, 0){
                
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };

    
    private static LinkedListAccounts accounts;
    private static UserService userService;
    
    
    private String roles[] = {"Admin", "Librarian"};
    private  JComboBox cb = new JComboBox(roles);

    public adminCOA(UserService userService,LinkedListAccounts accounts) {
        this.userService =  userService;
        this.accounts = accounts;

        // Call to set up the UI components
//        setupUI();

        // Load the accounts initially
       // loadAccounts("All Accounts");
        loadAccountsToTable();
          userService.updateUserTable(accountTable);
          setupUI();
    }

   
        public void setupUI(){
        
        mainPanel.setBackground(new Color(0x99582A));
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1280, 1049));

//        pan6.setBackground(new Color(0x6F1D1B));
//        pan6.setLayout(null);
//        pan6.setBounds(850, 86, 758, 840);
       
                      
                 
  

        amlbl.setBounds(43, 26, 788, 77);
        amlbl.setFont(new Font("Bebas Neue", Font.BOLD, 65));
        amlbl.setForeground(new Color(0x6F1D1B));

        accountTable.setModel(model);
        accountTableScp.setBounds(43, 521, 1151, 450);
  
        accountTableScp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
               centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  
               
     
        

            // Initialize main panels and components
          for (int i = 0; i < accountTable.getColumnCount(); i++) {
    accountTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
          
          
          
          JTableHeader header = accountTable.getTableHeader();
          header.setFont(new Font("Bebas Neue",Font.BOLD,18));
          header.setBackground(new Color(0x6F1D1B));
          header.setForeground(new Color(0xFFFFFF));
          header.setBorder(BorderFactory.createRaisedBevelBorder());

        searchField.setBounds(43, 372, 322, 54);
        searchField.setBackground(new Color(0xD9D9D9));
        searchField.setLayout(null);
        searchField.setBorder(null);
        searchField.setFont(new Font("Plus Jakarta Sans",Font.BOLD,24));
        
          Image img = searchIMG.getImage();  // Transform it 
            Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
            searchIMG = new ImageIcon(newImg);

             JButton search = new JButton();
            search.setBounds(240, 5, 81, 53);
            search.setBackground(new Color(0xD9D9D9));
            search.setBorderPainted(false);
            search.setOpaque(true);
            search.setContentAreaFilled(true);
            search.setIcon(searchIMG);
            
            
           searchField.add(search);
        
        addAccount.setBounds(381, 372, 180, 54);
        addAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        addAccount.setForeground(new Color(0x6F1D1B));
        addAccount.setBackground(new Color (0xD9D9D9));
           
        updAccount.setBounds(607, 372, 180, 54);
        updAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        updAccount.setForeground(new Color(0x6F1D1B));
        updAccount.setBackground(new Color (0xD9D9D9));
        
        deleteAccount.setBounds(833, 372, 180, 54);
        deleteAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        deleteAccount.setForeground(new Color(0x6F1D1B));
        deleteAccount.setBackground(new Color (0xD9D9D9));
        
        unblockAccount.setBounds(1053, 372, 150, 54);
          unblockAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        unblockAccount.setForeground(new Color(0x6F1D1B));
        unblockAccount.setBackground(new Color (0xD9D9D9));
        
      
        usern.setBounds(43  , 112, 295, 50);
        usern.setForeground(Color.WHITE);
        usern.setFont(new Font("Plus Jakarta Sans", Font.CENTER_BASELINE,20));
        
        userCategory.setBounds(940, 447, 250, 54);
      userCategory.setFont(new Font("Plus Jakarta Sans", Font.CENTER_BASELINE,25));
      userCategory.setBackground(new Color(0xBB9457));
      userCategory.setForeground(Color.white);
      
      
          mainPanel.add(usern);
        username.setBounds(43, 158, 538, 54);
        username.setText("Enter username");
        username.setFont(new Font("Futura", Font.PLAIN,25));
        username.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     username.setText("");
                 }
            });
     
        passw.setBounds(663, 112, 150, 50);
        passw.setForeground(Color.WHITE);
        passw.setFont(new Font("Plus Jakarta Sans", Font.CENTER_BASELINE,20));
           mainPanel.add(passw);
           
        password.setBounds(663  , 158, 538, 54);
        password.setText("Enter Password");
        password.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,25));
        password.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     password.setText("");
                 }
            });
        
      
        cont.setBounds(43, 216, 500, 50);
        cont.setForeground(Color.WHITE);
        cont.setFont(new Font("Futura", Font.CENTER_BASELINE,25));
          mainPanel.add(cont);
        
        contact.setBounds(43, 265, 538, 50);
        contact.setText("Enter Contact Number");
        contact.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,25));
        contact.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     contact.setText("");
                 }
            });
            
         
          rol.setBounds(663, 216, 500, 50);
          rol.setForeground(Color.WHITE);
          rol.setFont(new Font("Futura", Font.CENTER_BASELINE,25));
           mainPanel.add(rol);
          
         mainPanel. add(cb);
          cb.setBounds(663, 265, 538, 54);
          cb.setFont(new Font("Futura", Font.PLAIN,25));
 
        addAccount.addActionListener(e -> addAccount());
        updAccount.addActionListener(e -> updateAccount());
        deleteAccount.addActionListener(e -> deleteAccountt());
         unblockAccount.addActionListener(e -> unblockSelectedAccount());
//        deleteAccount.addActionListener(e -> {
//           int selectedRow = accountTable.getSelectedRow();
//    if (selectedRow == -1) {
//        JOptionPane.showMessageDialog(null, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Retrieve the userID from the selected row (column index may vary, here assumed to be 0)
//    int userID = (int) accountTable.getValueAt(selectedRow, 0);
//
//    // Perform deletion using userID
//    boolean deleted = accounts.deleteById(userID);
//
//    if (deleted) {
//        JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//    } else {
//        JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    // Reload the table
//    loadAccounts("All Accounts");
//        });
//
        userCategory.addActionListener(e -> filterAccounts());
        search.addActionListener(e -> searchAccounts());
   
//
//        
        
        
        mainPanel.add(amlbl);
        mainPanel.add(pan7);
        mainPanel.add(accountTableScp);
        mainPanel.add(searchField);
        mainPanel.add(searchbtn);
        mainPanel.add(userCategory);
        mainPanel.add(username);
        mainPanel.add(password);
        mainPanel.add(contact);
        mainPanel.add(role);
        mainPanel.add(addAccount);
        mainPanel.add(updAccount);
        mainPanel.add(deleteAccount);
        mainPanel.add(unblockAccount);
        
        loadAccounts("All Accounts");

  
          mainPanel.setBounds(0, 0, 1351, 1049);
        mainPanel.setVisible(true);
    }
        private void deleteAccountt(){
          int selectedRow = accountTable.getSelectedRow();

    // Check if a row is selected
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            null, 
            "Please select a row to delete an account.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // Retrieve the userID from the selected row
    int userID = (int) accountTable.getValueAt(selectedRow, 0);

    // Prompt admin to input their password
    JPasswordField passwordField = new JPasswordField();
    int confirm = JOptionPane.showConfirmDialog(
        null, 
        passwordField, 
        "Enter Admin Password", 
        JOptionPane.OK_CANCEL_OPTION, 
        JOptionPane.PLAIN_MESSAGE
    );

    // If the admin cancels the prompt, stop the deletion
    if (confirm != JOptionPane.OK_OPTION) {
        return;
    }

    // Retrieve the entered password
    String enteredPassword = new String(passwordField.getPassword());

    // Validate the entered password with the admin's password
    if (userService.validatePassword(enteredPassword, enteredPassword)) {
        JOptionPane.showMessageDialog(
            null, 
            "Incorrect password. Account deletion aborted.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // Perform deletion using userID
    boolean deleted = accounts.deleteById(userID);

    // Show success or failure message
    if (deleted) {
        JOptionPane.showMessageDialog(
            null, 
            "Account deleted successfully.", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
            null, 
            "Account not found.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Reload the table
    loadAccounts("All Accounts");
         
        }
        private void updateAccount(){
                try{
                        // Get the selected row in the table
                int row = accountTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the new values to update
                String userN = username.getText().trim();
                String passW = new String(password.getPassword()).trim(); // Convert password to string
                String conT = contact.getText().trim();
                String rol = cb.getSelectedItem().toString();

                // Update specific fields only if they are not empty or unchanged
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();

                // Update the username if it has changed and is not empty
                if (!userN.isEmpty() && !userN.equals(model.getValueAt(row, 1))) {
                    model.setValueAt(userN, row, 1); // Update username (column 1)
                }

                // Update the password if it has changed
                if (!passW.isEmpty() && !passW.equals(model.getValueAt(row, 2))) {
                    model.setValueAt(passW, row, 2); // Update password (column 2)
                }

                // Update the contact number if it has changed
                if (!conT.isEmpty() && !conT.equals(model.getValueAt(row, 3))) {
                    model.setValueAt(conT, row, 3); // Update contact (column 3)
                }

                // Update the role if it has changed
                if (!rol.equals(model.getValueAt(row, 4))) {
                    model.setValueAt(rol, row, 4); // Update role (column 4)
                }

                JOptionPane.showMessageDialog(null, "Successfully Updated", "Account Management", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "An error occurred. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
           
    private void addAccount() {
        // Retrieve input from the text fields and combo box
    int id = accounts.generateRandomID(); // Generate a random ID
    String userN = username.getText().trim();
    String passW = new String(password.getPassword()).trim(); // Convert password to string
    String conT = contact.getText().trim();
    String roL = cb.getSelectedItem().toString();
    boolean exist = true;

    // Validate inputs - Check if any field is empty
    if (userN.isEmpty() || passW.isEmpty() || conT.isEmpty()) {
        JOptionPane.showMessageDialog(null, "All fields must have a value.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate password length (at least 13 characters)
    if (passW.length() < 13) {
        JOptionPane.showMessageDialog(null, "Password must be at least 13 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate contact number length (exactly 11 characters)
    if (conT.length() != 11 || !conT.matches("[0-9]+")) {
        JOptionPane.showMessageDialog(null, "Contact number must be exactly 11 digits long and contain only numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    //to avoid duplicates
    for (NodeAccounts account : accounts.getAllAccounts()) { // getAllAccounts()from LinkedListAcc retrieves all accounts
    if (account.getUserName().equals(userN)) {
        JOptionPane.showMessageDialog(
            null, 
            "Username already exists. Please choose a different username.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
    
    if (account.getPassword().equals(passW)) {
        JOptionPane.showMessageDialog(
            null, 
            "Password already exists. Please choose a different password.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
    
    if (account.getContactNum().equals(conT)) {
        JOptionPane.showMessageDialog(
            null, 
            "Contact number already exists. Please choose a different contact number.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
}

    try {
        // Add user to the LinkedList (accounts object)
        accounts.createAccount(id, userN, passW, conT, roL);
        System.out.println("The acc has been added");
        
        // Update the user table in the UI (accountTable)
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.addRow(new Object[]{id, userN, conT, roL});
//            userService.addUser(id, userN, passW, conT, roL);
        // Show success message
        JOptionPane.showMessageDialog(null, "Account added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Optionally, reload the accounts (if necessary) to refresh the UI
        loadAccounts("All Accounts");

        // Clear input fields after successful addition
        username.setText("");
        password.setText("");
        contact.setText("");
        cb.setSelectedIndex(0);

    } catch (Exception e) {
        // Handle any exceptions (e.g., database errors)
        JOptionPane.showMessageDialog(null, "An error occurred while adding the account: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

      public void populateTable() {
          System.out.println("Andito kana sa populatetable");
     DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
    model.setRowCount(0);  // Clear the table

    NodeAccounts current = accounts.getHead();  // Assuming you have a head pointer in the LinkedList
    while (current != null) {
         System.out.println("Adding account: " + current.getUserName());
        model.addRow(new Object[] {
            
            current.getMemberID(), 
            current.getUserName(), 
            current.getPassword(),
            current.getContactNum(), 
            current.getRole()
        });
       
        current = current.next;  // Traverse the linked list
    }
   }
     private void filterAccounts() {
        String selectedCategory = (String) userCategory.getSelectedItem();
        loadAccounts(selectedCategory);
    }

    private void searchAccounts() {
        if (accounts.getHead() == null) {
    JOptionPane.showMessageDialog(this, "No accounts are available to search.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Field must not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }   
        
        

       NodeAccounts acc =accounts.linearSearch(searchText);
        if (acc != null) {
                            // Book found, display its details in pnl3
                            
                             JOptionPane.showMessageDialog(null, "User Does exist");
                             DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
      // Clear the ta
        model.setRowCount(0);

    model.addRow(new Object[]{
        acc.getMemberID(),
        acc.getUserName(),
        acc.getPassword(),
        acc.getContactNum(),
        acc.getRole()
    });
}

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No results found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void loadAccountsToTable() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.setRowCount(0); // Clear existing rows
        
        // Iterate through the LinkedListAccounts and load data into the table
        NodeAccounts current = accounts.getHead();
        while (current != null) {
            Object[] row = new Object[5];
            row[0] = current.getMemberID();
            row[1] = current.getUserName();
            row[2] = current.getPassword();
            row[3] = current.getContactNum();
            row[4] = current.getRole();
            model.addRow(row);
            current = current.next;
        }
    }
    private void saveChanges() {
    // Here, we ensure all changes made to the accounts in the table are saved back to the LinkedList
    String searchText = searchField.getText().trim();
    if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Field must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (accounts.getHead() == null) {
        JOptionPane.showMessageDialog(this, "No accounts are available to search.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Clear the table before searching
    model.setRowCount(0);

    // Perform the search
    String searchTextLower = searchText.toLowerCase();
    NodeAccounts current = accounts.getHead();
    boolean found = false;

    while (current != null) {
        if (String.valueOf(current.getMemberID()).toLowerCase().contains(searchTextLower) ||
            current.getUserName().toLowerCase().contains(searchTextLower) ||
            current.getRole().toLowerCase().contains(searchTextLower)) {

            // Add matching account to the table
            model.addRow(new Object[]{
                current.getMemberID(),
                current.getUserName(),
                current.getPassword(),
                current.getContactNum(),
                current.getRole()
            });
            found = true;
        }
        current = current.next;
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No results found.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    }

    private void loadAccounts(String filterRole) {
    model.setRowCount(0); // Clear the table

    NodeAccounts current = accounts.getHead();
    while (current != null) {
        boolean addRow = false;

        // Determine if the row should be added based on the filter
        if (filterRole.equals("All Accounts")) {
            addRow = true; // Show all accounts
        } else if (filterRole.equals("Librarian Accounts") && current.getRole().equals("Librarian")) {
            addRow = true;
        } else if (filterRole.equals("Admin Accounts") && current.getRole().equals("Admin")) {
            addRow = true;
//        } else if (filterRole.equals("User Accounts") && current.getRole().equals("user")) {
//            addRow = true;
        } else if (filterRole.equals("Blocked Accounts") && current.getRole().equals("Blocked")) {
            addRow = true;
        }

        // Add matching rows to the table
        if (addRow) {
            model.addRow(new Object[]{
                current.getMemberID(),
        current.getUserName(),
        current.getPassword(),
        current.getContactNum(),
        current.getRole()
            });
        }

        current = current.next;
    }
}
    private void unblockSelectedAccount() {
    // Get the selected row from the table
    int selectedRow = accountTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a row to unblock an account.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve the Member ID (Primary Key) from the selected row
    int memberID = (int) accountTable.getValueAt(selectedRow, 0);  // Assuming Member ID is in column 0

    // Find the account in the LinkedList
    NodeAccounts userToUnblock = accounts.linearSearchByID(memberID);

    if (userToUnblock != null && userToUnblock.isBlocked()) {
        // Unblock the user
        userToUnblock.unblock();

        // Update the role if necessary (optional: e.g., change "blocked" to the original role)
        if (userToUnblock.getRole().equalsIgnoreCase("blocked")) {
            userToUnblock.setRole("user");  // Default role after unblocking
        }

        // Refresh the table to reflect the unblocked user
        loadAccounts("Blocked Accounts");

        JOptionPane.showMessageDialog(null, "User has been successfully unblocked!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else if (userToUnblock == null) {
        JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "The selected account is not blocked.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}


    public static void main(String[] args) {
     //   adminCOA create = new adminCOA();
     //   create.setVisible(true);
    }

       
}
