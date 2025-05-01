/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;
import BACKENDUSER.LLhistory;
import LogSigBackEnd.UserService;
import LogSigBackEnd.Users;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeAccounts;
import FRONTENDADMIN.dashBoard;
import FRONTENDLIB.DASHBOARDUI;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import FRONTENDLIB.DASHBOARDUI;
import LogSigBackEnd.User;
import java.awt.Image;
import javax.swing.JPanel;

public class LOGINUI extends JPanel {
   LinkedlistBook bookList;
    LinkedListAccounts acc;
    LLhistory history;
    User user;

    private JTextField textFld;
    private JPasswordField pass;

    public LOGINUI(LinkedlistBook bookList, LinkedListAccounts acc, LLhistory history) {
        this.bookList = bookList;
        this.acc = acc;
        this.history = history;

      
}
    public void design(){
          setLayout(null); // Use null layout for absolute positioning
        setBounds(0, 0, 500, 200); // Set size and position
        setBackground(Color.GREEN); // Background color of the panel

        JLabel formTitle = new JLabel("Login Form");
        formTitle.setBounds(456, 10, 300, 40);
        formTitle.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        formTitle.setForeground(new Color(0x6F1D1B));
        add(formTitle);

        JLabel nn = new JLabel("Username:");
        JLabel pp = new JLabel("Password:");
        nn.setBounds(39, 60, 200, 37);
        nn.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));
        pp.setBounds(39, 140, 200, 37);
        pp.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));

        textFld = new JTextField();
        textFld.setBounds(170, 60, 493, 48);
        textFld.setBackground(new Color(0xD9D9D9));
        textFld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));

        pass = new JPasswordField();
        pass.setBounds(170, 140, 493, 48);
        pass.setBackground(new Color(0xD9D9D9));
        pass.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 29));

        JButton login = new JButton("Log-In");
        login.setBounds(428, 240, 234, 40);
        login.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        login.setForeground(Color.white);
        login.setBackground(new Color(0xBB9457));
        login.addActionListener(e -> {
            String username = textFld.getText();
            String passwordInput = new String(pass.getPassword());
            UserService userService = UserService.getInstance();
        });
     

        add(nn);
        add(pp);
        add(textFld);
        add(pass);
        add(login);
    
                }
}
    
