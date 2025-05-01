/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDUSER;
import BACKENDUSER.LLhistory;
import BACKENDUSER.NodeHistory;
import BACKENDUSER.TranascHisotry;
import DSA.LinkedlistBook;
import LogSigBackEnd.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BOOKHISTORYUI extends parentComponent implements ActionListener {
     User user;  // The User object
    LinkedlistBook bookList;
    LLhistory acc;
    JButton back = new JButton();
    
    private String[] headers = {"Title", "Author", "ISBN", "Genre", "Borrowed Date", "Due Date", "Overdue Fine"};
    DefaultTableModel defTab;
    public JTable table;
    JScrollPane sp;
    private JPanel pnl1, pnl2;
    
    private JLabel a, b;

    public BOOKHISTORYUI(User user,LLhistory acc, LinkedlistBook bookList) {
        if (user == null || bookList == null) {
            System.out.println("Error: user or bookList is null.");
            return;
        }

        this.user = user;
        this.acc =acc;
        this.bookList = bookList;
      

        initializeTable();
        initializeUI();
        populateTable();
        //updateTable();
        acc.displayList();// Update table with borrowed books
    }

    private void initializeTable() {
        
        defTab = new DefaultTableModel(headers,0);
        table = new JTable(defTab);
        sp = new JScrollPane(table);
      //  acc.getHistoryData(user);
        
    }

    private void initializeUI() {
        b = new JLabel("BORROWED BOOKS");
        b.setFont(new Font("Bebas Neue", Font.BOLD, 80));
        b.setBounds(300, 150, 1000, 60);
        b.setForeground(new Color(0xBB9457));

        a = new JLabel(user.getUsername());
        a.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        a.setBounds(116, 20, 315, 60);

        pnl2 = new JPanel();
        pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);

        pnl1 = new JPanel();
        pnl1.setLayout(null);
        pnl1.setBackground(new Color(0x6F1D1B));
        pnl1.setBounds(-7, 86, 2150, 875);

        sp.setBounds(33, 240, 1390, 100);

        back = new JButton("Back");
        back.setBounds(1200, 600, 226, 88);
        back.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        back.setForeground(new Color(0x6F1D1B));
        back.setBackground(Color.white);
        back.addActionListener(this);

        pnl2.add(a);
        pnl1.add(b);
        pnl1.add(sp);
        pnl1.add(back);

        this.add(pnl2);
        this.add(pnl1);
        this.setIconImage(icon);
        this.setSize(1476, 896);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Books History");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            dispose();
            new DashboardUser(user, bookList, user.getHistory());
        }
    }

    // This method will update the table with data from the linked list
   public void updateTable() {
  if (user == null || user.getHistory() == null) {
        System.out.println("Error: User or history is null.");
        return;
    }

    // Get the history data for the user
    Object[][] historyData = user.getHistory().getHistoryData(user);

    // Print history data for debugging
    System.out.println("History data:");
    for (Object[] row : historyData) {
        System.out.println(Arrays.toString(row));  // This will print each history entry
    }

    // Clear the existing rows in the table
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);  // Clear existing rows

    if (historyData != null && historyData.length > 0) {
        // Add new rows if there is history data
        for (Object[] row : historyData) {
            model.addRow(row);  // Add each row of history data
        }
    } else {
        System.out.println("No data to display.");
    }
    }
      public  void  populateTable() {
        // Get the current books from the linked list
        Object[][] bookData = acc.getHistoryData(user);

        // Clear any existing rows in the table
        defTab.setRowCount(0);

        // Add each book's data to the table
        for (Object[] book : bookData) {
            defTab.addRow(book);
        }
    }
//  public Object[][] getHistoryData(User user) {
//            Object[][] historyEntries = user.getHistory().getHistoryData(user);  // Get the LinkedList of history entries
//    Object[][] historyData = new Object[historyEntries.()][7];  // Assuming 7 columns
//
//    int index = 0;
//    for (NodeHistory entry : historyEntries) {
//        historyData[index++] = new Object[] {
//            entry.getTitle(),
//            entry.getAuthor(),
//            entry.getCd(),
//            entry.getGenre(),
//            entry.getBorrowDate(),
//            entry.getReturnDate(),
//            entry.getPrice()
//        };
//    }
//
//    return historyData;  // Return the 2D array for displaying in a table
//}
    }