/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginAndSignUp;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import LogSigBackEnd.UserService;
import DSA.LinkedlistBook;
import FRONTENDUSER.DashboardUser;
import LogSigBackEnd.User;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class userLogin extends parentcomponent{
     private JTextField nnField;
    private JPasswordField ppField;
     LinkedlistBook bookList;
        LinkedListAccounts acc;
        LLhistory history;
        User user;
 
    
    public userLogin( LinkedlistBook bookList, LinkedListAccounts acc,LLhistory history,User user) {
        this.bookList = bookList;
        this.acc = acc;
        this.history = history;
        this.user = user;
        UserService nyes = UserService.getInstance();
        frame = new JFrame();// Get the singleton instance
        setTitle("Log-in Form");
        setSize(719, 358);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setIconImage(icon);
        setLocationRelativeTo(null);
        setLayout(null);
        
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
        textFld.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));
        
        pass = new JPasswordField();
        pass.setBounds(170, 140, 493, 48);
        pass.setBackground(new Color(0xD9D9D9));
        pass.setFont(new Font("Plus Jakarta Sans",Font.PLAIN, 29));

        JButton login = new JButton("Log-In");
        login.setBounds(170, 240, 234, 48);
        login.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        login.setForeground(Color.white);
        login.setBackground(new Color(0xBB9457));
        login.addActionListener(e -> {
            String username = textFld.getText();
            String passwordInput = new String(pass.getPassword());
           
        

          if (nyes.validateLogin(username, passwordInput)) {
    // If validation is successful, get the user object
    this.user = nyes.getUser(username); 

    // Show success message and navigate to the dashboard
    JOptionPane.showMessageDialog(null, "Login successful");

    // Create a new dashboard for the user
    DashboardUser dash = new DashboardUser(user, bookList, history);
    this.dispose();
} else {
    // If validation fails, show error message
    JOptionPane.showMessageDialog(null, "Invalid username or password.");
}
        });

        JButton signup = new JButton("Sign-Up");
        signup.setBounds(428, 240, 234, 48);
        signup.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        signup.setForeground(Color.white);
        signup.setBackground(new Color(0x99582A));
        signup.addActionListener(e -> new SIGNUPUI(nyes));

        add(nn);
        add(pp);
        add(textFld);
        add(pass);
        add(login);
        add(signup);
        

        setVisible(true);
    }
    
}
