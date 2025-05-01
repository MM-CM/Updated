/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;

import FRONTENDUSER.*;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeAccounts;
import FRONTENDADMIN.dashBoard;
import FRONTENDADMIN.dashBoards;
import FRONTENDLIB.DASHBOARDUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginSignup extends parentcomponent {

    public static arryList sharedTransac = new arryList();
    static LinkedlistBook sharedBookList = new LinkedlistBook();
    static LinkedListAccounts acc = new LinkedListAccounts();
    static LLhistory history = new LLhistory();
    static User user = new User();

    JLabel title = new JLabel();
    JLabel mang = new JLabel();
    JLabel sys = new JLabel();
    JLabel labels = new JLabel();

    JButton usersBTN = new JButton();
    JButton AdminBTN = new JButton();
    JButton libBTN = new JButton();

    public LoginSignup() {

        frame = new JFrame();
        frame.setSize(1100, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(icon);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);

        // Create background panel with image
        JPanel red = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call the superclass's method to ensure proper component rendering
                // Load the image or GIF
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\pc\\Downloads\\LibraryBG.jpg"); // Change path if needed
                Image image = imageIcon.getImage();
                // Draw the image to cover the entire panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        red.setBounds(0, 0, 1100, 800);  // Set the size of the panel
        red.setLayout(null);  // Set 

//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                ImageIcon imageIcon = new ImageIcon("images\\nice.gif"); 
//                Image image = imageIcon.getImage();
//                g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
//            }
//        };
        // Title setup
        title.setText("ALIB");
        title.setFont(new Font("Bebas Neue", Font.BOLD, 140));
        title.setForeground(Color.white);
        title.setBounds(400, -100, 500, 500);

        // Management label setup
        mang.setText("Library Managements System");
        mang.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 38));
        mang.setForeground(Color.white);
        mang.setBounds(300, -20, 600, 500);

        // Adding elements to red panel
        red.add(title);

        // Add the red background panel first
        // Add the login panel next
        red.add(mang);

        JPanel login = loginPanel();
        login.setBounds(180, 280, 719, 440);  // Make sure the login panel has the right size
        // Set it to transparent so the background shows through
        red.add(login);

        frame.add(red);
        
        frame.setVisible(true);

        // Make the frame visible after all components are added
    }

    public JPanel loginPanel() {
        // Login Panel
        JPanel login = new JPanel();
        login.setLayout(null);
        login.setSize(719, 440);

        login.setBackground(new Color(111, 29, 27, 58));
        login.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.gray));

        // Title Label
        JLabel title = new JLabel("Log - In");
        title.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 100));
        title.setForeground(new Color(0xFFFFFF));
        title.setBounds(180, 13, 1000, 140);

        // Username Label
        JLabel us = new JLabel("Username :");
        us.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        us.setForeground(new Color(0xBB9457));
        us.setBounds(34, 174, 500, 37);

        // Password Label
        JLabel pass = new JLabel("Password :");
        pass.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        pass.setForeground(new Color(0x99582A));
        pass.setBounds(34, 284, 500, 37);

        // Username Text Field
        JTextField users = new JTextField();
        users.setBounds(250, 174, 450, 48);
        users.setBackground(new Color(0xBB9457));
        users.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));
        users.setForeground(Color.white);
        users.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        // Password Field
        JPasswordField passField = new JPasswordField();
        passField.setBounds(250, 284, 450, 48);
        passField.setBackground(new Color(0x99582A));
        passField.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));
        passField.setForeground(Color.white);
        passField.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        JButton log = new JButton("Log in");
        log.setBounds(465, 374, 234, 48);
        log.setFont(new Font("Bebas Neue", Font.BOLD, 31));
        log.setForeground(Color.white);
        log.setBackground(new Color(0x6F1D1B));
        log.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        log.addActionListener(e -> {
            String username = users.getText();
            String passwordInput = new String(passField.getPassword());
            UserService userService = UserService.getInstance();

            NodeAccounts loggedInUser = userService.getLinkedListAccounts().linearSearch(username);

            if (loggedInUser != null) {
                // Check if the account is blocked
                if (loggedInUser.isBlocked()) {
                    JOptionPane.showMessageDialog(null, "Account is blocked due to multiple failed login attempts.");
                    return;
                }
            }

            if (username.equals("librarian") && passwordInput.equals("lib123")) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new DASHBOARDUI(sharedBookList, acc, history, user).Desgin();
                frame.dispose();
            } else if (username.equals("admin") && passwordInput.equals("admin123")) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new dashBoard(userService, acc, user, sharedBookList, sharedTransac);
                frame.dispose();
            } else if (userService.validateLogin(username, passwordInput)) {

                if (loggedInUser != null) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    if (loggedInUser.getRole().equalsIgnoreCase("librarian")) {
                        new DASHBOARDUI(sharedBookList, acc, history, user).Desgin();
                        frame.dispose();
                    } else if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        new dashBoard(userService, acc, user, sharedBookList, sharedTransac);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid role.");
                    }
                } else {
                    loggedInUser.incrementFailedAttempts(); // Increment failed attempts
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Attempt "
                            + loggedInUser.getFailedAttempts() + " of 3.");

                    // Block account if failed attempts reach 3
                    if (loggedInUser.getFailedAttempts() >= 3) {
                        loggedInUser.block();
                        loggedInUser.setRole("Blocked");
                        JOptionPane.showMessageDialog(null, "Account is now blocked due to multiple failed login attempts.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        });
        JButton stu = new JButton();
        stu.setText("For Student");
        stu.setBounds(1, 374, 263, 48);
        stu.setFont(new Font("Bebas Neue", Font.ITALIC, 32));
        stu.setForeground(new Color(0xFFFFFF));
        stu.setBackground(new Color(111, 29, 27, 58));
        stu.setBorder(null);
        stu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BROWSEUI his = new BROWSEUI(sharedBookList);
                frame.dispose();
            }
        }
        );

        login.add(title);
        login.add(us);
        login.add(pass);
        login.add(users);
        login.add(passField);
        login.add(log);
        login.add(stu);

        return login;
    }

    public static void main(String[] args) {
        new LoginSignup();
    }

}
