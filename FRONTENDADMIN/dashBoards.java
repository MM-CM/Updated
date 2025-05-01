/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import FRONTENDLIB.ArchieveUI;
import FRONTENDLIB.BORROWINGUI;
import static FRONTENDLIB.DASHBOARDUI.arcs;
import static FRONTENDLIB.DASHBOARDUI.sharedTransac;
import FRONTENDLIB.RecordPayUI;
import FRONTENDLIB.ReturnUI;
import FRONTENDLIB.VIEWLISTUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import LoginAndSignUp.LoginSignup;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class dashBoards extends parentComponent implements ActionListener {
  LinkedListAccounts acc;
       UserService userService;
    JPanel userPan = new JPanel();
    JPanel pan1 = new JPanel();
    JButton generateReports = new JButton("Generate Reports");
    JButton accountManagement = new JButton("Account Management");
    JLabel w = new JLabel("W");
    JLabel welcome = new JLabel("elcome to your dashboard, ");
    User user;
    JPanel red;
    LinkedlistBook book;
    arryList arr;
             
    public dashBoards(UserService userService, LinkedListAccounts acc,User user,LinkedlistBook book,arryList arr) {
        
      this.  acc = acc;
      this.userService = userService;
      this.user = user;
      this.book = book;
      this.arr = arr;
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                JFrame frame = new JFrame("Dashboard");
                frame.setSize(screenSize);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                JLayeredPane layeredPane = new JLayeredPane();
                    layeredPane.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
                    layeredPane.setLayout(new CardLayout());
               //panels
               JPanel white = new JPanel();
               white.setBounds(-4, -14, 410 , 1101);
               white.setBackground(Color.white);
               white.setLayout(null);

             red = new JPanel();
               red.setBounds(467, -14, 1351 , 1049);
               red.setBackground(new Color(0x6F1D1B));
                red.setLayout(null);
                
                
                //labels
                    label = new JLabel();
                    label.setText(user.getUsername());
                    label.setBounds(148, 80, 248, 67);
                    label.setFont(new Font("Bebas Neue", Font.BOLD,40));
                    label.setForeground(Color.black);
                    
                      JLabel lab = new JLabel ("Welcome to your DashBoard");
                     lab.setFont(new Font("Bebas Neue",Font.BOLD, 50));
                     lab.setForeground(Color.white);
                     lab.setBounds(300, 279, 879, 115);
                     lab.setVisible(true);
                     red.add(lab);
                     
             //buttons
                   JButton butt = new JButton();
                   butt.setBounds(30,160, 409, 61);
                   butt.setBackground(Color.white);
                   butt.setText("Home");
                   butt.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   butt.setForeground(new Color(0x6F1D1B));
                   butt.setBorder(null);
                   butt.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                   lab.setVisible(true);
                   switchContent("Home");
                   red.add(lab);
                  }
              }
                   );
                   
                   
             
                   
                    JButton in = new JButton();
                   in.setBounds(30,236, 409, 61);
                   in.setBackground(Color.white);
                   in.setText("Inventory");
                   in.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   in.setForeground(new Color(0x6F1D1B));
                   in.setBorder(null);
                     in.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
               JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
        
        // Remove all components from the red panel (where the main content is displayed)
        red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
        // Create and add the new inventory content (VIEWLISTUI)
        VIEWLISTUI viewListUI = new VIEWLISTUI();
        viewListUI.designFrame(); // Set up the inventory UI
        
        // Add the new content to the red panel
        red.add(viewListUI.panel); // Add the inventory panel to the red panel
        
        // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();// Add the new panel

                  }
              }
                   );
                  
                   
                         JButton bb = new JButton();
                   bb.setBounds(30,315, 409, 61);
                    bb.setBackground(Color.white);
                   bb.setText("Account Management");
                   bb.setFont(new Font("Bebas Neue",Font.BOLD, 32));
                   bb.setForeground(new Color(0x6F1D1B));
                   bb.setBorder(null);
                   bb.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        
               JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
        
        // Remove all components from the red panel (where the main content is displayed)
        red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
        // Create and add the new inventory content (VIEWLISTUI)
        adminCOA borr = new adminCOA(userService,acc);
        // Set up the inventory UI
        
        // Add the new content to the red panel
        red.add(borr.mainPanel); // Add the inventory panel to the red panel
        
        // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
                   });
                 
                   
                         JButton rr = new JButton();
                   rr.setBounds(30,390, 409, 61);
                   rr.setBackground(Color.white);
                   rr.setText("Borrowed Reports");
                   rr.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   rr.setForeground(new Color(0x6F1D1B));
                   rr.setBorder(null);
                   rr.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                                                  
               JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
        
        // Remove all components from the red panel (where the main content is displayed)
        red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
        // Create and add the new inventory content (VIEWLISTUI)
       adminReports ret = new adminReports(acc,userService,user,book,arr);
        // Set up the inventory UI
        
        // Add the new content to the red panel
        red.add(ret.mainPanel); // Add the inventory panel to the red panel
        
        // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
              }
                   );
                    
                   
                   
                         JButton rp = new JButton();
                   rp.setBounds(30,470, 409, 61);
                   rp.setBackground(Color.white);
                   rp.setText("Book Return Reports");
                   rp.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   rp.setForeground(new Color(0x6F1D1B));
                   rp.setBorder(null);
                   rp.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
                      
                       red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
                      ReturnReport pay = new ReturnReport(acc,userService,user,book,arr);
                      
                       red.add(pay.mainPanel);
                          // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
                   });
                    
              
                   
                   
                  
                   
                    
                      JButton log = new JButton();
                   log.setBounds(30,550, 409, 61);
                   log.setBackground(Color.white);
                   log.setText("Log-out");
                   log.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   log.setForeground(new Color(0x6F1D1B));
                   log.setBorder(null);
                         log.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
               log();
               LoginSignup ne = new LoginSignup();
          }
                         });
                      
                    
                    
                    
                    
                    white.add(label);
                    white.add(butt);
                    white.add(in);
                    white.add(bb);
                    white.add(rr);
                            white.add(rp);
                           
                            white.add(log);
                    
               
              
               this.add(white);
               this.add(red);
               
               this.setVisible(true);
}
    public void log(){
         this.dispose();
    }
private void switchContent(String viewType) {
    // Clear the red panel and add new content based on the viewType
    red.removeAll();  // Remove any current content in the red panel
    
    if ("Inventory".equals(viewType)) {
        VIEWLISTUI viewListUI = new VIEWLISTUI();
        viewListUI.designFrame();  // Call the designFrame method for the inventory view
        red.add(viewListUI.panel);  // Add the inventory panel to the red area
    } else {
        JLabel homeLabel = new JLabel("Welcome to your DashBoard");
        homeLabel.setFont(new Font("Bebas Neue",Font.BOLD, 50));
        homeLabel.setForeground(Color.white);
        red.add(homeLabel);  // Add the home label back when "Home" button is pressed
    }

    // Revalidate and repaint the red panel to update the view
    red.revalidate();
    red.repaint();
}


       
      public static void main(String[] args) {
     UserService service = new UserService();
     LinkedListAccounts acc = new LinkedListAccounts();
     User user = new User();
     LinkedlistBook book = new LinkedlistBook();
     arryList arr = new arryList();
        new dashBoards(service,acc,user,book,arr); 
    }
      

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
