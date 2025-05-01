/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDUSER;
import BACKENDUSER.LLhistory;
import DSA.LinkedlistBook;
import LogSigBackEnd.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class DashboardUser  extends parentComponent implements ActionListener {
     LinkedlistBook bookList;
           User user;
            LLhistory bookHistory;
         private String username;
         
        
        JButton exit = new JButton();
        JLabel greet = new JLabel();
        JLabel w = new JLabel();
        JLabel a = new JLabel();
        JLabel d = new JLabel();
    
        JButton historyB = new JButton();
        JLabel h1 = new JLabel();
        JLabel h2 = new JLabel();
        JLabel h3 = new JLabel();

        JButton browseB = new JButton();
        JLabel b1 = new JLabel();
        JLabel b2 = new JLabel();
        
        JButton changePass = new JButton();
        JLabel c1 = new JLabel();
        JLabel c2 = new JLabel();
        
//            JTextField textF = new JTextField();
//            JPasswordField passF = new JPasswordField();
//        

        public DashboardUser(User user,LinkedlistBook bookList,LLhistory bookHistory){
           this.user = user;
        this.bookList = bookList;
        this.bookHistory = bookHistory;
        
        
        panel = new JPanel();
        panel2 = new JPanel();
        // Dashboard Panels
        add(panel);
        add(panel2);

        panel.setLayout(null);
        panel.setBackground(new Color(0x6F1D1B));
        panel.setBounds(-7, 86, 1537, 875);

        panel2.setLayout(null);
        panel2.setBackground(new Color(0xD9D9D9));
        panel2.setBounds(0, 0, 1530, 86);

        // Welcome Message
        w.setText("W");
        w.setFont(new Font("Bebas Neue", Font.BOLD, 128));
        w.setBounds(65, 100, 250, 110);
        w.setForeground(Color.white);

        greet.setText("elcome To your Dashboard");
        greet.setFont(new Font("Bebas Neue", Font.PLAIN, 64));
        greet.setBounds(175, 140, 826, 77);
        greet.setForeground(Color.white);

        panel.add(w);
        panel.add(greet);

        // History Button
        historyB.setBounds(300, 250, 400, 300);
        historyB.setBackground(new Color(0xBB9457));
        historyB.setLayout(null);
        JLabel h1 = new JLabel("Borrowing");
        JLabel h2 = new JLabel("History");
        h1.setBounds(10, 120, 400, 100);
        h2.setBounds(10, 220, 300, 60);
        h1.setForeground(Color.white);
        h2.setForeground(Color.white);
        h1.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        h2.setFont(new Font("Bebas Neue", Font.BOLD, 65));
        historyB.add(h1);
        historyB.add(h2);

        historyB.addActionListener(e -> {
            System.out.println("History button clicked");
            new BOOKHISTORYUI(user,bookHistory,bookList);
            dispose();
        });
        panel.add(historyB);

        // Browse Button
        browseB.setBounds(750, 250, 400, 300);
        browseB.setBackground(new Color(0xBB9457));
        browseB.setLayout(null);
        JLabel b1 = new JLabel("Search");
        JLabel b2 = new JLabel("Books");
        b1.setBounds(10, 120, 280, 90);
        b2.setBounds(10, 220, 270, 60);
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        b1.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        b2.setFont(new Font("Bebas Neue", Font.BOLD, 65));
        browseB.add(b1);
        browseB.add(b2);

        browseB.addActionListener(e -> {
            System.out.println("Browse button clicked");
           // new BROWSEUI(user, bookList,bookHistory);
            dispose();
        });
  

              panel.add(browseB);
              
                  a = new JLabel(user.getUsername());
        a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        a.setBounds(116, 20, 315, 60);

        // Exit Button
        exit.setBounds(1259, 15, 187, 56);
        exit.setBackground(new Color(0xBB9457));
        exit.setLayout(null);
        JLabel d = new JLabel("Exit");
        d.setBounds(50, 10, 150, 47);
        d.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        d.setForeground(Color.white);
        exit.add(d);

        exit.addActionListener(e -> {
            System.out.println("Exit button clicked");
            ((JFrame) SwingUtilities.getWindowAncestor(exit)).dispose();
        });
        panel2.add(exit);
        panel2.add(a);

        // Frame Settings
        this.setSize(1476, 896);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        this.setIconImage(icon);
        this.setVisible(true);
        setTitle("Dashboard");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty, since buttons now use lambdas
    }

    public static void main(String[] args) {
        // Example usage
        //User users = new User();
       // new DashboardUser("JohnDoe", new LinkedlistBook(),new LLhistory());
    }
}

