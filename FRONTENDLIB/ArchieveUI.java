/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.arryList;
import DSA.LLarch;
import DSA.LinkedlistBook;
import DSA.NodeBook;
import DSA.NodeTransac;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ArchieveUI extends parentComponent {
   JPanel panel = new JPanel();
     JPanel mainPanel = new JPanel();
    JPanel pnl2 = new JPanel();
    JLabel a;
    JLabel title = new JLabel("Archieve Book");
    DefaultTableModel tb;
    JTable table;
    JScrollPane sp;
    static arryList list = new arryList();
     ImageIcon searchIMG = new ImageIcon("images\\search.png");
     JButton restore = new JButton();
    static LinkedlistBook bookList;
        public static LLarch arc ;
    private JComboBox<String> option;
    JTextField isbns;

    public ArchieveUI(arryList list, LinkedlistBook bookList, LLarch arc ) {
                String[] columns = {"Title", "Author", "ISBN", "Genre", "Status", "Book ID"};
       tb = new DefaultTableModel(columns, 0);

      
        table = new JTable(tb);
              table.setBorder(BorderFactory.createLineBorder(new Color(0x6F1D1B), 1));
        sp = new JScrollPane(table);
        ArchieveUI.list = list;
        ArchieveUI.bookList = bookList;
        ArchieveUI.arc = arc;
        initializePanel();
    }

    private void initializePanel() {
    
        this.setLayout(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date returnDate = calendar.getTime();

        panel.setBounds(0, 0, 1280, 1049);
        panel.setBackground(new Color(0x6F1D1B));
        panel.setLayout(null);
         Image img = searchIMG.getImage();  // Transform it 
            Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
            searchIMG = new ImageIcon(newImg);

           JButton search = new JButton();
            search.setBounds(266, 5, 81, 53);
            search.setBackground(new Color(0xBB9457));
            search.setBorderPainted(false);
            search.setOpaque(true);
            search.setContentAreaFilled(true);
            search.setIcon(searchIMG);
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int searchISBN = Integer.parseInt(isbns.getText());  // ISBN entered by user
                        NodeBook foundBook = bookList.LinearSeach(searchISBN);  // Call search method

                        if (foundBook != null) {
                            // Book found, display its details in pnl3
                            JOptionPane.showMessageDialog(null, "ISBN Does exist");
                             DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);  // Clear the table

                model.addRow(new Object[] {
                    foundBook.getTitle(), 
                    foundBook.getAuthor(), 
                    foundBook.getISBN(), 
                    foundBook.getGenre(),
                    foundBook.getIsAvailable() ? "Available" : "Not Available", 
                    foundBook.getBookId(), 
                    foundBook.getQuan(),
                    foundBook.getStatus(),
                    foundBook.shelft()
                });

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ISBN Does not exist");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
                    }
                }
            });

            isbns = new JTextField();
            isbns.setBounds(31, 141, 356, 65);
            isbns.setBackground(new Color(0xBB9457));
            isbns.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbns.setForeground(Color.white);
            isbns.setLayout(null);
            isbns.setBorder(null);
            isbns.add(search);
            
            restore.setText("Restore");
            restore.setBounds(940, 141, 305, 65);
            restore.setBackground(new Color(0xBB9457));
            restore.setFont(new Font("Bebas Neue",Font.BOLD,40));
            restore.setForeground(Color.white);
            restore.setBorder(BorderFactory.createRaisedBevelBorder());
            restore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               int row = table.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please choose a row", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the ISBN of the selected book from the table
        int isbn = (int) table.getValueAt(row, 2);  // Assuming ISBN is in the 3rd column (index 2)

        // Retrieve the book data from the archived list based on the ISBN
        NodeBook archivedBook = bookList.getArchivedBookByISBN(isbn);
        if (archivedBook != null) {
            // Restore the book to the active list (you may need to write the logic for this)
            bookList.restoreBook(archivedBook);

            // Remove the book from the archived list
            bookList.removeBookFromArchive(isbn);

            // Update the table to reflect the changes
            populateTable();
            
            JOptionPane.showMessageDialog(null, "Book restored successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Book not found in the archive", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
            });
            
       

        title.setBounds(31, 19, 800, 100);
        title.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 64));
        title.setForeground(Color.white);

        sp.setBounds(45, 250, 1200, 724);

        option = new JComboBox<>(new String[]{"Option1", "Option2"}); // Replace with real options
        option.setBounds(450, 141, 233, 65);
        option.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 16));
        option.setBackground(new Color(0x99582A));
        option.setForeground(Color.white);

        panel.add(title);
        panel.add(sp);
  
        panel.add(isbns);
        panel.add(restore);

                 
        mainPanel = new JPanel();
       
        mainPanel.setSize(1280, 1049);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1280, 1049);
        mainPanel.setBackground(new Color(0x6F1D1B));
        
        mainPanel.add(panel);

        populateTable();
         mainPanel.setVisible(true);
    }
     

    private void populateTable() {
          Object[][] archivedBooks = bookList.getArchivedBookData();  // This method fetches the archived book data

    // Create a DefaultTableModel with column names
    DefaultTableModel tableModel = new DefaultTableModel();
         tableModel.setColumnIdentifiers(new String[]{"Title", "Author", "ISBN", "Genre", "Status", "Book ID"});
    for (Object[] row : archivedBooks) {
        tableModel.addRow(row);
    }

    // Set the table model to the JTable
    table.setModel(tableModel);
    }


    



    
    
    
}
