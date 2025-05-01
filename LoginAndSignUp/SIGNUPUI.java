/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import LogSigBackEnd.Users;
import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import FRONTENDADMIN.adminCOA;
import java.awt.Color;
import java.awt.Font;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class SIGNUPUI extends parentcomponent{
   private final UserService userServices;
        // LinkedListAccounts accounts;
      
      // Create an instance of Users
      
    public SIGNUPUI(UserService userService) {
      this.userServices = userService;
      //this.accounts = listAcc;
      // Pass UserService to this UI
        
        // Frame properties
         frame = new JFrame();
       setTitle("Sign-up Form");
      setSize(719, 584);
     setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);
      setLayout(null);

        // Title Label
        JLabel formTitle = new JLabel("Sign-up Form");
        formTitle.setBounds(370, 10, 500, 70);
        formTitle.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        formTitle.setForeground(new Color(0x6F1D1B));
        add(formTitle);

        // Field Labels
        JLabel nn = new JLabel("Username:");
        JLabel pp = new JLabel("Password:");
        JLabel cp = new JLabel("Confirm Password:");
        JLabel contact = new JLabel("Contact Number:");
        JLabel email = new JLabel("Email:");
        configureLabel(nn, 90, 116);
        configureLabel(pp, 90, 192);
        configureLabel(cp, 15, 262);
        configureLabel(contact, 20, 332);
        configureLabel(email, 90, 400);

        // Input Fields
        JTextField ssField = new JTextField();
        JPasswordField ppsField = new JPasswordField();
        JPasswordField cpsField = new JPasswordField();
        JTextField contactField = new JTextField();
        JTextField emailsFld = new JTextField();
        
        ssField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        ppsField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        cpsField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        contactField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        emailsFld.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        
        configureField(ssField, 204, 116);
        configureField(ppsField, 204, 192);
        configureField(cpsField, 204, 262);
        configureField(contactField, 204, 332);
        configureField(emailsFld, 204, 400);

        // Buttons
        JButton cancel = createButton("Cancel", 456, 480);
        cancel.addActionListener(e -> dispose()); // Close the form
        
        JButton sign = createButton("Sign Up", 204, 480);
        sign.addActionListener(e -> {
            String username = ssField.getText().trim();
            String password = new String(ppsField.getPassword());
            String confirmPassword = new String(cpsField.getPassword());
            String contactNumber = contactField.getText().trim();
            String emails = emailsFld.getText().trim();
            String role = "User";

            // Validate inputs
            if (userService.userExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidContact(contactNumber)) {
                JOptionPane.showMessageDialog(this, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidEmail(emails)) {
                JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
               // int id =  generateRandomID();
                
               //userService.addUser(username, password, contactNumber, emails, role);
                 int userID = userService.addUser(username, password, contactNumber, emails, role);
                 
                    userService.syncUsersWithLinkedList();
        // Show a success message with the generated userID
                     JOptionPane.showMessageDialog(null, "Sign-up successful! Your User ID is: " + userID, "Success", JOptionPane.INFORMATION_MESSAGE);
                            // Pass the accounts to adminCOA
       // adminScreen.setVisible(true);
                         dispose(); //
            }
        });

        // Add Components to Frame
        add(nn);
        add(pp);
        add(cp);
        add(contact);
        add(email);
        add(ssField);
        add(ppsField);
        add(cpsField);
        add(contactField);
        add(emailsFld);
        add(sign);
        add(cancel);
         this.setIconImage(icon);

        // Background color
        getContentPane().setBackground(new Color(0xBB9457));
        setVisible(true);
    }
   
    // Configure label properties
    private void configureLabel(JLabel label, int x, int y) {
        label.setBounds(x, y, 200, 37);
        label.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 20));
    }

    // Configure field properties
    private void configureField(JTextField field, int x, int y) {
        field.setBounds(x, y, 493, 48);
    }

    // Create a button with default styling
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 234, 48);
        button.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        button.setBackground(new Color(0x6F1D1B));
        button.setForeground(new Color(0xBB9457));
        return button;
    }

    // Validate contact number (basic numeric check)
    private boolean isValidContact(String contactNumber) {
        return contactNumber.matches("\\d{12}"); // Validates a 10-digit number
    }

    // Validate email address format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    // Update JTable with user data
 
}

