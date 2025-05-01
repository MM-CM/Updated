/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LLarch;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import FRONTENDLIB.RecordPayUI;
import LogSigBackEnd.User;
import LoginAndSignUp.LoginSignup;
import javax.swing.*;

public class DASHBOARDUI extends parentComponent implements ActionListener,BookBase {
     LinkedlistBook bookList;
 public static   arryList sharedTransac = new arryList();
      public static LLarch arcs  = new LLarch() ;
 public LinkedListAccounts acc;
 public LLhistory history;
 public User user;
 public  RecordPayUI pay = new RecordPayUI(sharedTransac,bookList);
     
        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JButton list = new JButton();
        JButton brrw = new JButton();
        JButton rtn = new JButton();
        JButton payment = new JButton();
        JButton report = new JButton();
        JLabel lbllist = new JLabel();
        JLabel lbllist1 = new JLabel();
        JLabel lbllist2 = new JLabel();
        JLabel lblbrw = new JLabel();
        JLabel lblrtn = new JLabel();
        JLabel lblpay = new JLabel();
        JLabel lblpay1 = new JLabel();
        JLabel lblrep = new JLabel();
        JLabel lblrep1 = new JLabel();
        JLabel greet = new JLabel();
        JLabel w = new JLabel();
         JLabel a = new JLabel();
          JLabel d = new JLabel();
            JButton exit = new JButton();
            JPanel red;
            
         
            
            
        // LinkedList storage = new LinkedList();
    
   public DASHBOARDUI( LinkedlistBook bookList,LinkedListAccounts acc, LLhistory hisotry,User user)
    {   
        this.acc = acc;
        this.history = hisotry;
        this.bookList = bookList;
        this.user = user;
   
   
           }
   
public void Desgin(){
              this.setSize(1758, 1020);
              this. setResizable(false);
               this.setLocationRelativeTo(null);
               this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               this.setVisible(true);
               this.setIconImage(icon);
               this.setLayout(null);
               this.setTitle("DashBoard");
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
                   bb.setText("Borrowing");
                   bb.setFont(new Font("Bebas Neue",Font.BOLD, 40));
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
        BORROWINGUI borr = new BORROWINGUI(bookList,pay,acc,user,history);
        // Set up the inventory UI
        
        // Add the new content to the red panel
        red.add(borr.Mainpanel); // Add the inventory panel to the red panel
        
        // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
                   });
                 
                   
                         JButton rr = new JButton();
                   rr.setBounds(30,390, 409, 61);
                   rr.setBackground(Color.white);
                   rr.setText("Returning");
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
        ReturnUI ret = new ReturnUI(sharedTransac,pay,bookList);
        // Set up the inventory UI
        
        // Add the new content to the red panel
        red.add(ret.MainPanel); // Add the inventory panel to the red panel
        
        // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
              }
                   );
                    
                   
                   
                         JButton rp = new JButton();
                   rp.setBounds(30,470, 409, 61);
                   rp.setBackground(Color.white);
                   rp.setText("Payment Record");
                   rp.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   rp.setForeground(new Color(0x6F1D1B));
                   rp.setBorder(null);
                   rp.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
                      
                       red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
                      RecordPayUI pay = new RecordPayUI(sharedTransac,bookList);
                      
                       red.add(pay.mainPanel);
                          // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
                   });
                    
                   
                      JButton arc = new JButton();
                   arc.setBounds(30,550, 409, 61);
                   arc.setBackground(Color.white);
                   arc.setText("Archieve Books");
                   arc.setFont(new Font("Bebas Neue",Font.BOLD, 40));
                   arc.setForeground(new Color(0x6F1D1B));
                   arc.setBorder(null);
                   arc.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                       
                   JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(in);
                      
                       red = (JPanel) dashboardFrame.getContentPane().getComponent(1); // assuming red panel is at index 1
        red.removeAll(); // Remove current content in the red panel
        
                      ArchieveUI pay = new ArchieveUI(sharedTransac,bookList,arcs);
                      
                       red.add(pay.mainPanel);
                          // Revalidate and repaint to reflect the changes
        dashboardFrame.revalidate();
        dashboardFrame.repaint();
                  }
                       
                   });
                   
                   
                  
                   
                    
                      JButton log = new JButton();
                   log.setBounds(30,620, 409, 61);
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
                            white.add(arc);
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
         LinkedlistBook bookList = new LinkedlistBook();
     arryList oms = new arryList();
  LinkedListAccounts accs= new LinkedListAccounts();
  LLhistory historys= new LLhistory();
  User users = new User();
        new DASHBOARDUI(bookList,accs,historys,users).Desgin(); 
    }
      

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
