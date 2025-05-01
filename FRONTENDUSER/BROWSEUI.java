/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDUSER;
import BACKENDUSER.LLhistory;
import DSA.LinkedlistBook;
import DSA.NodeBook;
//import static FRONTENDLIB.VIEWLISTUI.bookList;
import LogSigBackEnd.User;
import LoginAndSignUp.LoginSignup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class BROWSEUI extends parentComponent implements ActionListener, ItemListener{
 private LinkedlistBook bookList = new LinkedlistBook();// contains list of books
//  LLhistory bookHistory; //stores borrowing hist
    private JComboBox<String> genreComboBox;
    private JPanel bookPanel;
    
    private JLabel a, lb1, lb2, lb3, lb4, b;
    private JTextField isbn;
    private JButton borrow, back, bbrw, search, exit;
    private JPanel pnl1, pnl2, pnl3, pnl4;
     private String[] headers = {"Title", "Author", "ISBN", "Genre", "BookID", "Quantity", "Availability"};
   
    DefaultTableModel defTab = new DefaultTableModel(this.bookList.getBookData(), headers);
        JTable table = new JTable(defTab);
        JScrollPane sp = new JScrollPane(table);
     
     public BROWSEUI(LinkedlistBook bookList){
               this.bookList = bookList;
       
        // When the this frame display, the sorted books displayed in d table without performng an action
        updateTableWithSortedBooks(defTab);
            
        pnl2 = new JPanel();
        pnl2.setLayout(null);
        pnl2.setBackground(new Color(0xD9D9D9));
        pnl2.setBounds(0, 0, 1530, 86);
        
        
        search = new JButton();
        ImageIcon icon = new ImageIcon("Images\\search.png");
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        search.setIcon(new ImageIcon(image2));
        search.setBounds(215, 11, 81, 53);
        search.setBorder(null);
        search.setHorizontalTextPosition(JButton.LEFT);
        search.setFont(new Font("Bebas Neue", Font.BOLD, 30));
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(0xBB9457));
        search.addActionListener(e -> {
            String inputISBN = isbn.getText(); // Get the input ISBN as a string
            String selectedGenre = (String) genreComboBox.getSelectedItem(); // Get selected genre

            if (!inputISBN.isEmpty()) {
                try {
                    int isbnNumber = Integer.parseInt(inputISBN); // Convert input string to int
                    NodeBook book = bookList.getBookByIsbn(isbnNumber); // Search for the book in the linked list

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear the table

                    // Check if the book is found
                    if (book != null) {
                        if (selectedGenre.equals("All") || book.getGenre().equalsIgnoreCase(selectedGenre)) {
                            // If the genre matches or "All" is selected, display the book
                            model.addRow(new Object[]{
                                book.getTitle(), book.getAuthor(), book.getISBN(), book.getGenre(),
                                book.getBookId(), book.getQuan(), book.getIsAvailable() ? "Available" : "Checked Out"
                            });
                        } else {
                            // ISBN found but genre does not match
                            JOptionPane.showMessageDialog(
                                this, "No book found with ISBN " + isbnNumber + " in the genre: " + selectedGenre,
                                "Error", JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        // ISBN not found in the selected genre
                        JOptionPane.showMessageDialog(
                            this, "No book found with ISBN " + isbnNumber + " in the genre: " + selectedGenre,
                            "Error", JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    // Handle invalid number input
                    JOptionPane.showMessageDialog(this, "Invalid ISBN. Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // If input is empty
                JOptionPane.showMessageDialog(this, "Please enter an ISBN to search.", "Error", JOptionPane.ERROR_MESSAGE);
                resetTableData(); // Reset the table data if no ISBN is entered
            }
        });


        lb1 = new JLabel("ISBN");
        lb1.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb1.setBounds(1000, 100, 100, 43);
        lb1.setForeground(new Color(0xBB9457));

        isbn = new JTextField();
        isbn.setBounds(1000, 144, 300, 76);
        isbn.setBackground(new Color(0xBB9457));
        isbn.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        isbn.setForeground(Color.white);
        isbn.add(search);

        lb2 = new JLabel("Genre");
        lb2.setFont(new Font("Bebas Neue", Font.BOLD, 36));
        lb2.setBounds(80, 100, 200, 43);
        lb2.setForeground(new Color(0xBB9457));

        genreComboBox = new JComboBox<>(new String[]{"All", "Fiction", "Non-Fiction", "Education"});
        genreComboBox.setSelectedIndex(0);
        genreComboBox.setBounds(80, 144, 320, 76);
        genreComboBox.setPreferredSize(new Dimension(333, 76));
        genreComboBox.setBackground(new Color(0x99582A));
        genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
        genreComboBox.setForeground(Color.white);
         genreComboBox.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        // If "Select genre" is present, remove it when the JComboBox is clicked
        if (genreComboBox.getItemCount() > 0 && genreComboBox.getItemAt(0).equals("Select genre")) {
            genreComboBox.removeItem("Select genre");
        }
    }
});    

        genreComboBox.addActionListener(e -> {

           // quickSort(BookBase.genre, 0, BookBase.genre.length - 1);
           
                 DefaultTableModel model = (DefaultTableModel) defTab;
        model.setRowCount(0); 
         boolean found = false;
                 String selectedGenre = (String) genreComboBox.getSelectedItem();
             if (!selectedGenre.equals("All")) {
            Object[][] getBookData = bookList.getBookData(); // Get the book data

            // Loop the books t check the genre
            for (Object[] book : getBookData) {
                String bookGenre = (String) book[3];
                if (bookGenre.equalsIgnoreCase(selectedGenre)) {
                    String availability = (String) book[4]; 
                    model.addRow(new Object[]{book[0], book[1], book[2], book[3], book[5],book[6],book[4]});
                    found = true;
                }
            }

            // If no books were found for slected gen
            if (!found) {
                JOptionPane.showMessageDialog(this, "No books found in the genre: " + selectedGenre, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //display sorted books "All"
            updateTableWithSortedBooks(defTab); 
        }  // Assuming populateTable can accept the sorted array
});
        // Book panel for displaying books of the selected genre
        bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBounds(33, 250, 728, 300);
        bookPanel.setBackground(new Color(0x6F1D1B));
        
        exit = new JButton();
        exit.setBounds(800, 15, 187, 56);
        exit.setText("Exit");
        exit.setBackground(new Color(0xBB9457));
        exit.setFont(new Font("Bebas Neue",Font.BOLD,23));
        exit.setForeground(Color.white);
        exit.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent e) {
                    log();
                     LoginSignup ad = new LoginSignup();
                   }
        });
        
        pnl1 = new JPanel();
        pnl1.setLayout(null);
        pnl1.setBackground(new Color(0x6F1D1B));
        pnl1.setBounds(-7, 86, 1500, 875);
        
 

        pnl4 = new JPanel();
        pnl4.setLayout(null);
        pnl4.setBounds(33, 240, 1390, 450);
        pnl4.setBackground(new Color(0xBB9457));

        b = new JLabel("Borrowing");
        b.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        b.setBounds(270, 0, 826, 100);
        b.setForeground(new Color(0x6F1D1B));
        sp.setBounds(33, 20, 1320, 400);
        table.setFont(new Font("Bebas Neue", Font.PLAIN, 15));
        
        
//        pnl2.add(a);   
  pnl2.add(exit);
        pnl1.add(lb1);
        pnl1.add(lb2);
        pnl1.add(isbn);
        pnl1.add(sp);       
        pnl1.add(genreComboBox);
        pnl1.add(bookPanel);
        pnl4.add(sp);


//        this.add(borrow);
        this.add(pnl4);
        this.add(pnl2);
        this.add(pnl1);
      
        this.setSize(1476, 896);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("Borrowing");
    
                }
     private void updateTableWithSortedBooks(DefaultTableModel defTab) {
    // Get the sorted books data
    Object[][] sortedBooks = bookList.sortByTitle(); // Calls quickSortByTitle() inside sortByTitle()

    // Clear the table model
    defTab.setRowCount(0);

    // Add the sorted books to the table model
    for (Object[] book : sortedBooks) {
        defTab.addRow(new Object[]{book[0], book[1], book[2], book[3], book[5],book[6],book[4]});
    }
}
     public void log(){
         this.dispose();
     }
                
    public void resetTableData() {
     DefaultTableModel model = (DefaultTableModel) defTab;
    Object[][] getBookData = bookList.getBookData(); // Get all book data
    model.setRowCount(0); // Clear the table
    
    for (Object[] book : getBookData) {
        String availability = (boolean) book[6] ? "Available" : "Checked Out";
         model.addRow(new Object[]{book[0], book[1], book[2], book[3], book[5],book[6],book[4]});
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
 
        } 
    @Override
    public void itemStateChanged(ItemEvent e) {  
    }

    public static void main (String[] args){
        LinkedlistBook bookList = new LinkedlistBook();
        new BROWSEUI(bookList);
       
    }
}
