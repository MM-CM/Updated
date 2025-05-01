/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import DSA.BookInfo;
//import DSA.LinkedlistBook;
import   DSA.LinkedlistBook.*;
import DSA.NodeBook;
import FRONTENDLIB.DatabaseCon;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.random;
import java.text.ParseException;
import java.util.Arrays;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.JTableHeader;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class VIEWLISTUI extends parentComponent implements BookBase {
   public   static ArrayList<BookInfo> bookArray = new ArrayList<>();
//    public   static LinkedlistBook bookList = new LinkedlistBook();
   
   
   String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Availability","BookID","Quantity","Book Status","Shelf Number","Year of Publication"};
    DefaultTableModel tableModel;
    JTable bookTable;
    JScrollPane sp;
     JLabel title = new JLabel("Inventory");
    public JPanel panel = new JPanel();
       JPanel pnl2 = new JPanel();
     JLabel a = new JLabel();
     JButton add, delete,update,sort,ref;   
     JLabel isbn,booktitle,author,form,genere,Images,bookID,quant,sortIMG,refIMG, BookStatus;
//     JSpinner yrPubliSpinner; 
     JDateChooser yrPubliSpinner = new JDateChooser();
     JTextField ttFld, codfld, authfld,isbnfld,BookID,Quantity,cell,avail,isbns, BStatus;
     JButton adds,cancel,remove,cancels,updabbt,addGenre;
      private JComboBox<String> genreComboBox;
//       ImageIcon searchIMG = new ImageIcon("images\\search.png");
       
       Connection database;
       Statement stmt;
      

  
    

    // Constructor
  public  VIEWLISTUI() {
     // VIEWLISTUI.bookList = book;
        // Add default books to the bookList
      database = DatabaseCon.connectDB();
               designFrame();
             
  }
  public void table() {
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    JTableHeader header = bookTable.getTableHeader();
    header.setFont(new Font("Bebas Neue", Font.BOLD, 18));
    header.setBackground(new Color(0xBB9457));
    header.setForeground(new Color(0x6F1D1B));
    header.setBorder(BorderFactory.createRaisedBevelBorder());

bookTable.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent evt) {
        try {
            int row = bookTable.getSelectedRow();

            if (row < 0 || row >= bookTable.getRowCount()) {
                JOptionPane.showMessageDialog(null, "Please select a valid row.");
                return;
            }

            // Get BookID from the selected row (column 5 based on your populateTable method)
            int bookId = Integer.parseInt(bookTable.getValueAt(row, 5).toString());

            // Query the database for this book
            PreparedStatement ps = database.prepareStatement("SELECT * FROM BookInventory WHERE BookID = ?");
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ttFld.setText(rs.getString("Title"));
                authfld.setText(rs.getString("Author"));
                isbnfld.setText(String.valueOf(rs.getInt("ISBN")));
                BookID.setText(String.valueOf(rs.getInt("BookID")));
                Quantity.setText(String.valueOf(rs.getInt("Quantity")));
                cell.setText(String.valueOf(rs.getInt("ShelfNum")));
                BStatus.setText(rs.getString("BookStatus"));

                // Set genre in combo box
                String genre = rs.getString("Genre").trim();
                boolean itemFound = false;
                for (int i = 0; i < genreComboBox.getItemCount(); i++) {
                    if (genre.equalsIgnoreCase(genreComboBox.getItemAt(i).toString().trim())) {
                        genreComboBox.setSelectedIndex(i);
                        itemFound = true;
                        break;
                    }
                }
                if (!itemFound) {
                    JOptionPane.showMessageDialog(null, "Genre not found in combo box.");
                }

                // Set publication date
                java.sql.Date pubDate = rs.getDate("YearPublished");
                if (pubDate != null) {
                    yrPubliSpinner.setDate(new java.util.Date(pubDate.getTime()));
                }

            } else {
                JOptionPane.showMessageDialog(null, "No book found with BookID: " + bookId, "Not Found", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading book data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
});
  }
  public void Border(){
      ttFld.setBorder(BorderFactory.createRaisedBevelBorder());
      cell.setBorder(BorderFactory.createRaisedBevelBorder());
      authfld.setBorder(BorderFactory.createRaisedBevelBorder());
      isbnfld.setBorder(BorderFactory.createRaisedBevelBorder());
      Quantity.setBorder(BorderFactory.createRaisedBevelBorder());
      BookID.setBorder(BorderFactory.createRaisedBevelBorder());
       yrPubliSpinner.setBorder(BorderFactory.createRaisedBevelBorder());
        genreComboBox.setBorder(BorderFactory.createRaisedBevelBorder());
  }

    // Method to add default books
  

    
   
  public void designFrame() {
    // Create the table model and the table itself
    tableModel = new DefaultTableModel(columnNames, 0);
    bookTable = new JTable(tableModel);
    sp = new JScrollPane(bookTable);
     

    // Create the panel and set properties
    panel = new JPanel();
    panel.setVisible(true);
    panel.setSize(1300, 1100);
    panel.setLayout(null);  // You're using null layout, so sizes and positions need to be explicitly set
    panel.setBounds(0, 0, 1300, 1100);
    panel.setBackground(new Color(0x6F1D1B));

    // Initialize the input fields and set bounds
    ttFld = new JTextField();
    ttFld.setBounds(60, 149, 300, 47);
    ttFld.setBackground(new Color(0xBB9457));
    ttFld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    ttFld.setForeground(Color.white);

    cell = new JTextField();
    cell.setBounds(60, 350, 300, 47);
    cell.setBackground(new Color(0xBB9457));
    cell.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    cell.setForeground(Color.white);

    authfld = new JTextField();
    authfld.setBounds(500, 149, 300, 47);
    authfld.setBackground(new Color(0xBB9457));
    authfld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    authfld.setForeground(Color.white);

    isbnfld = new JTextField();
    isbnfld.setBounds(60, 247, 300, 47);
    isbnfld.setBackground(new Color(0xBB9457));
    isbnfld.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    isbnfld.setForeground(Color.white);

    BookID = new JTextField();
      BookID.setEnabled(false);
    BookID.setBounds(500, 247, 300, 47);
    BookID.setBackground(new Color(0xBB9457));
    BookID.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    BookID.setForeground(Color.white);

    Quantity = new JTextField();
    Quantity.setBounds(900, 247, 300, 47);
    Quantity.setBackground(new Color(0xBB9457));
    Quantity.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    Quantity.setForeground(Color.white);

    // Initialize year picker (JDateChooser)
    yrPubliSpinner = new JDateChooser();
    yrPubliSpinner.setMaxSelectableDate(new java.util.Date());
    yrPubliSpinner.setBounds(500, 350, 300, 47);
    yrPubliSpinner.setBackground(new Color(0xBB9457));
    yrPubliSpinner.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    yrPubliSpinner.setForeground(Color.white);

   BStatus = new JTextField();
    BStatus.setBounds(900, 350, 300, 47);  // BStatus now directly under the bookstat label
    BStatus.setBackground(new Color(0xBB9457));
    BStatus.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
    BStatus.setForeground(Color.white);

   genreComboBox = new JComboBox<>();
genreComboBox.setBounds(900, 149, 300, 47);
genreComboBox.setBackground(new Color(0xBB9457));
genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
genreComboBox.setForeground(Color.white);

// Add items BEFORE setting selected index
genreComboBox.addItem("Select genre");
// (you'll later load actual genres from database)

genreComboBox.setSelectedIndex(0); // Now safe!

genreComboBox.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
        if (genreComboBox.getItemCount() > 0 && genreComboBox.getItemAt(0).equals("Select genre")) {
            genreComboBox.removeItem("Select genre");
        }
    }
});
        populateGenreComboBox();
  
     
  
        // Add Genre Button
addGenre = new JButton("Add Genre");
addGenre.setBounds(1080, 94, 151, 40);
addGenre.setBackground(new Color(0x6F1D1B));
addGenre.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 16));
addGenre.setForeground(Color.white);
addGenre.setBorder(null);
addGenre.addActionListener(e -> addGenre());

// Border method call (assuming you have it elsewhere)
Border();

// Labels
JLabel titlelbl = new JLabel("Title");
JLabel authorlbl = new JLabel("Author");
JLabel genreLbl = new JLabel("Genre");
JLabel isbnLbl = new JLabel("ISBN");
JLabel yrOfPubLbl = new JLabel("Year of publication");
JLabel quantityLbl = new JLabel("Quantity");
JLabel cellLbl = new JLabel("Shelf number");
JLabel bokId = new JLabel("Book ID");
JLabel bookstat = new JLabel("Book Status");

titlelbl.setBounds(60, 113, 375, 30);
titlelbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
titlelbl.setForeground(Color.white);

authorlbl.setBounds(500, 113, 375, 30);
authorlbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
authorlbl.setForeground(Color.white);

genreLbl.setBounds(900, 113, 375, 30);
genreLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
genreLbl.setForeground(Color.white);

isbnLbl.setBounds(60, 208, 375, 30);
isbnLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
isbnLbl.setForeground(Color.white);

bokId.setBounds(500, 208, 375, 30);
bokId.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
bokId.setForeground(Color.white);

quantityLbl.setBounds(900, 208, 375, 30); // Adjusted to avoid overlap
quantityLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
quantityLbl.setForeground(Color.white);

cellLbl.setBounds(60, 311, 375, 30);
cellLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
cellLbl.setForeground(Color.white);

yrOfPubLbl.setBounds(500, 311, 375, 30);
yrOfPubLbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
yrOfPubLbl.setForeground(Color.white);


bookstat.setBounds(900, 311, 375, 30);
bookstat.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
bookstat.setForeground(Color.white);

// Add Button
add = new JButton("Add");
add.setBounds(550, 467, 187, 56);
add.setFont(new Font("Bebas Neue", Font.BOLD, 23));
add.setBackground(new Color(0xBB9457));
add.setForeground(Color.white);
add.addActionListener(e -> addFrame());

// Search Button and Image
//Image img = searchIMG.getImage();  // Transform it 
//Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
//searchIMG = new ImageIcon(newImg);

//JButton search = new JButton();
//search.setBounds(266, 5, 81, 53);
//search.setBackground(new Color(0xBB9457));
//search.setBorderPainted(false);
//search.setOpaque(true);
//search.setContentAreaFilled(true);
//search.setIcon(searchIMG);
//search.addActionListener(new ActionListener() {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            int searchISBN = Integer.parseInt(isbns.getText());  // ISBN entered by user
////            NodeBook foundBook = bookList.LinearSeach(searchISBN);  // Call search method
//
//            if (foundBook != null) {
//                // Book found, display its details in pnl3
//                JOptionPane.showMessageDialog(null, "ISBN Exists");
//                DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
//                model.setRowCount(0);  // Clear the table
//                model.addRow(new Object[]{
//                    foundBook.getTitle(),
//                    foundBook.getAuthor(),
//                    foundBook.getISBN(),
//                    foundBook.getGenre(),
//                    foundBook.getIsAvailable() ? "Available" : "Not Available",
//                    foundBook.getBookId(),
//                    foundBook.getQuan(),
//                    foundBook.getStatus(),
//                    foundBook.shelft(),
//                    foundBook.getYr(),
//                });
//            } else {
//                JOptionPane.showMessageDialog(null, "ISBN Does not exist");
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
//        }
//    }
//});


// SEARCH
isbns = new JTextField();
  //KEYEVENTS
            KeyListener l = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                @Override
                public void keyPressed(KeyEvent e) { }

                @Override
                public void keyReleased(KeyEvent e) {
                    String text = isbns.getText();
                    search(text);
                     populateTable();               
                }
            };
            isbns.addKeyListener(l);
isbns.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent evt) {
        populateTable();
        isbns.setText("");

        isbns.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(isbns.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 search(isbns.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(isbns.getText());
            }

        });
    }
});
                
isbns.setBounds(160, 467, 350, 56);
isbns.setBackground(new Color(0xBB9457));
isbns.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
isbns.setForeground(Color.white);
isbns.setLayout(null);
isbns.setBorder(null);

      


// Sort Button with Image
ImageIcon icon = new ImageIcon("images\\sort.png");
Image imgIcon = icon.getImage();  // Transform it 
Image newImgICOn = imgIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
ImageIcon icoon = new ImageIcon(newImgICOn);

sort = new JButton();
sort.setBounds(100, 467, 36, 56);
sort.setLayout(null);
sort.setBackground(new Color(0x6F1D1B));
sort.setIcon(icoon);
sort.setBorder(BorderFactory.createEmptyBorder());
//sort.addActionListener(new ActionListener() {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        NodeBook[] booksArray = bookList.toArray();
//        if (booksArray != null && booksArray.length > 1) {
//            // Perform quick sort
//            bookList.sortByTitle();
//            // Update the table or UI component displaying the books
//            updateBookTable();
//        } else {
//            JOptionPane.showMessageDialog(null, "No books to sort!", "Info", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//});
            
  
      
// Remove border for a cleaner look
      
//DOWNLOAD REFRESH ICON
  ImageIcon refresh = new ImageIcon("images\\refresh.png");
       Image refIcon = refresh.getImage();  // Transform it 
            Image reficon = refIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
           ImageIcon refs = new ImageIcon(reficon);     
           
      ref = new JButton();
      ref.setBounds(50, 467, 36, 56);
      ref.setBackground(new Color(0x6F1D1B));
      ref.setBorder(BorderFactory.createEmptyBorder());
      ref.setIcon(refs);
      ref.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              populateTable();
            }
      });
     
      
      
       
        update = new JButton("Update");
        update.setBounds(761, 467, 187, 56);
        update.setFont(new Font ("Bebas Neue", Font.BOLD, 23));
        update.setBackground(new Color(0xBB9457));
        update.setForeground(Color.white);
       update.addActionListener(e ->updateFrame());
        
          delete = new JButton("Delete");
        delete.setBounds(1000, 467, 187, 56);
        delete.setFont(new Font ("Bebas Neue", Font.BOLD, 23));
        delete.setBackground(new Color(0x99582A));
        delete.setForeground(Color.white);
        delete.addActionListener(e->deleteFrame());

        
        title.setBounds(31, 19, 500, 77);
        title.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 64));
        title.setForeground(Color.white);
        
        sp.setBounds(40, 560, 1190, 419);
        bookTable.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,12));
        bookTable.setForeground(new Color(0x6F1D1B));
        

        panel.add(title);
        panel.add(update);
        panel.add(sp);
        panel.add(add);
        panel.add(delete);
        panel.add(ttFld);
         panel.add(cell);
         panel.add(authfld);
         panel.add(isbnfld);
         panel.add(BookID);
         panel.add(Quantity);
         panel.add(yrPubliSpinner);
         panel.add(BStatus);
         panel.add(bookstat);
         panel.add(titlelbl);
           panel.add(authorlbl);
             panel.add(genreLbl);
               panel.add(isbnLbl);
                panel.add(bokId);
                 panel.add(quantityLbl);
                  panel.add(cellLbl);
                        panel.add(yrOfPubLbl);
                        panel.add(genreComboBox);
                        panel.add(isbns);
                        panel.add(sort);
                        panel.add(ref);
                        panel.add(addGenre);
                        panel.add(yrPubliSpinner);
                        
                            
         

        
                table();
    
   
        
        populateTable();
         panel.setVisible(true);
        
     
    }
  public void addGenreToDatabase(String genre) {
    PreparedStatement ps = null;

    try {
        // Prepare the SQL statement for inserting the genre into the Genres table
        ps = database.prepareStatement("INSERT INTO Genres (genreName) VALUES (?)");
        ps.setString(1, genre);
        ps.executeUpdate();  // Execute the update

        // Log to confirm insertion
        System.out.println("Successfully inserted genre: " + genre);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving genre to database.");
    } finally {
        try {
            if (ps != null) ps.close(); // Close PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public void populateGenreComboBox() {
    genreComboBox.removeAllItems(); // Clear existing items

    // Add default "Select genre" item
    genreComboBox.addItem("Select genre");

    try {
        String query = "SELECT genreName FROM Genres";  // Query to fetch genres from the database
        PreparedStatement ps = database.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        // Add each genre to the combo box
        while (rs.next()) {
            String genre = rs.getString("genreName");
            genreComboBox.addItem(genre);
        }

        // Close the result set and statement
        rs.close();
        ps.close();
        
        // After populating the combo box, set the selected index
        if (genreComboBox.getItemCount() > 0) {
            genreComboBox.setSelectedIndex(0);  // Select the first item ("Select genre")
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading genres from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
 public void addGenre() {
    JFrame fr = new JFrame("Genre");
    fr.setSize(407, 194);
    fr.setLayout(null);
    fr.setLocationRelativeTo(null);

    JLabel header = new JLabel("Add Genre");
    header.setBounds(7, 0, 500, 60);
    header.setFont(new Font("Bebas Neue", Font.BOLD, 50));
    header.setForeground(Color.black);

    JTextField addGenres = new JTextField();
    addGenres.setBounds(21, 63, 367, 25);
    addGenres.setBackground(new Color(0xBB9457));
    addGenres.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 18));
    addGenres.setForeground(Color.white);

    JButton cancel = new JButton("Cancel");
    cancel.setBounds(285, 120, 100, 25);
    cancel.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 18));
    cancel.setBackground(new Color(0x6F1D1B));
    cancel.setForeground(Color.white);
    cancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fr.dispose();
        }
    });

    JButton confirm = new JButton("Confirm");
    confirm.setBounds(175, 120, 101, 25);
    confirm.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 12));
    confirm.setBackground(new Color(0x6F1D1B));
    confirm.setForeground(Color.white);
    confirm.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String genre = addGenres.getText().trim();

            if (genre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Genre name cannot be empty.");
                return;
            }

            // Check if genre already exists in the ComboBox
            for (int i = 0; i < genreComboBox.getItemCount(); i++) {
                if (genreComboBox.getItemAt(i).equalsIgnoreCase(genre)) {
                    JOptionPane.showMessageDialog(null, "This genre already exists.");
                    return;
                }
            }

            // Add genre to the database
            addGenreToDatabase(genre);  // Add to database

            // Log to check database insertion
            System.out.println("Genre \"" + genre + "\" added to the database.");

            // Refresh the genre dropdown by clearing and repopulating it
            genreComboBox.removeAllItems();  // Clear existing items
            populateGenreComboBox();  // Re-populate the combo box with updated genres

            JOptionPane.showMessageDialog(null, "The genre \"" + genre + "\" was successfully added.");
            fr.dispose();  // Close the window
        }
    });

    fr.add(header);
    fr.add(addGenres);
    fr.add(cancel);
    fr.add(confirm);
    fr.setVisible(true);
}
  
public void populateTable() {
    DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
    model.setRowCount(0);  // Clear the table

    bookArray.clear(); // Clear the existing in-memory list to avoid duplication

    try {
        String query = "SELECT * FROM BookInventory";
        PreparedStatement ps = database.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int bookId = rs.getInt("BookID");
            String title = rs.getString("Title");
            String author = rs.getString("Author");
            String isbn = rs.getString("ISBN");
            String genre = rs.getString("Genre");
            boolean isAvailable = rs.getBoolean("Availability");
            int quantity = rs.getInt("Quantity");
            String status = rs.getString("BookStatus");
            int shelfNum = rs.getInt("ShelfNum");
            Date yearPublished = rs.getDate("YearPublished");

            // Add to table
            model.addRow(new Object[] {
                title, author, isbn, genre,
                isAvailable ? "Available" : "Not Available",
                bookId, quantity, status, shelfNum, yearPublished
            });

            // Update the bookArray
            BookInfo book = new BookInfo(bookId, title, author, isbn, genre, quantity,
                                         isAvailable, yearPublished, shelfNum, status);
            bookArray.add(book);
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading books from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    public void addFrame() {
    Random rand = new Random();
    int auto;
    boolean bookIdExists;

    // Generate a unique BookID
    do {
        auto = rand.nextInt(1000000);
        bookIdExists = false;
        try {
            PreparedStatement checkStmt = database.prepareStatement("SELECT BookID FROM BookInventory WHERE BookID = ?");
            checkStmt.setInt(1, auto);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                bookIdExists = true; // BookID already exists
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking for duplicate BookID.");
            return;
        }
    } while (bookIdExists);

    String BookIDs = String.valueOf(auto);
    this.BookID.setText(BookIDs);
    BookID.setEnabled(false);

    String title = ttFld.getText();
    String author = authfld.getText();
    String isbnText = isbnfld.getText().trim();
    String quan = Quantity.getText();
    String shelf = cell.getText();
    String bookstatus = BStatus.getText();
    String genre = genreComboBox.getSelectedItem().toString();

    // Validate Quantity
    int quantity;
    int shelfNum;
    try {
        quantity = Integer.parseInt(Quantity.getText());
        shelfNum = Integer.parseInt(cell.getText());
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
        return;
    }

// Normalize: remove all whitespace, and replace any dash-like characters with regular hyphen
isbnText = isbnText.replaceAll("[\\s\u2013\u2014\u2212]", "-"); // handles en dash, em dash, minus

// Validate: only digits and hyphens
if (!isbnText.matches("^[\\d-]+$")) {
    JOptionPane.showMessageDialog(null, "Please enter a valid ISBN (digits and dashes only).");
    return;
}

// Remove hyphens for length validation
String isbnCleaned = isbnText.replaceAll("-", "");

// Validate length: 10 or 13 digits
if (!(isbnCleaned.length() == 10 || isbnCleaned.length() == 13)) {
    JOptionPane.showMessageDialog(null, "ISBN must be exactly 10 or 13 digits (excluding dashes).");
    return;
}

    // Check if ISBN already exists
    try {
        PreparedStatement isbnCheckStmt = database.prepareStatement("SELECT ISBN FROM BookInventory WHERE ISBN = ?");
        isbnCheckStmt.setString(1, isbnText);
        ResultSet rsIsbn = isbnCheckStmt.executeQuery();
        if (rsIsbn.next()) {
            JOptionPane.showMessageDialog(null, "A book with this ISBN already exists. ISBN must be unique.");
            return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error checking for duplicate ISBN.");
        return;
    }

    Date selectedDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(yrPubliSpinner.getDate()));

    if (selectedDate != null) {
        java.sql.Date publicationYear = new java.sql.Date(selectedDate.getTime());

        System.out.println("Adding Book - Title: " + title + ", Author: " + author +
                ", ISBN: " + ISBN + ", Genre: " + genre +
                ", BookID: " + auto + ", Quantity: " + quantity + 
                ", Year Published: " + publicationYear + ", Shelf: " + shelfNum);

        try {
            PreparedStatement ps = database.prepareStatement(
                "INSERT INTO BookInventory(BookID, Title, Author, ISBN, Genre, Availability, Quantity, BookStatus, ShelfNum, YearPublished) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)"
            );
            ps.setInt(1, auto);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, isbnText);
            ps.setString(5, genre);
            ps.setBoolean(6, true); // Assuming the book is available
            ps.setInt(7, quantity);
            ps.setString(8, bookstatus);
            ps.setInt(9, shelfNum);
            ps.setDate(10, publicationYear);

            int result = ps.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "New book has been added.");
            }

            // Add to list
            BookInfo newBook = new BookInfo(auto, title, author, isbnText, genre, quantity, true, publicationYear, shelfNum, bookstatus);
            bookArray.add(newBook);
            populateTable();
        } catch (SQLException v) {
            v.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting the book into the database.");
        }

        // Clear input fields
        ttFld.setText("");
        authfld.setText("");
        BookID.setText("");
        genreComboBox.setSelectedIndex(0);
        Quantity.setText("");
        isbnfld.setText("");
        cell.setText("");
        BookID.setText(BookIDs);
        BStatus.setText(bookstatus);
    }
}


 public void updateFrame() {
    int row = bookTable.getSelectedRow(); // Get the selected row from the JTable

    if (row != -1) {
        String title = ttFld.getText().trim();
        String author = authfld.getText().trim();
        String bookIdStr = BookID.getText().trim();
        String quantityStr = Quantity.getText().trim();
        String isbnStr = isbnfld.getText().trim();
        String genre = genreComboBox.getSelectedItem().toString();
        String shelfStr = cell.getText().trim();
        String status = BStatus.getText().trim();

        if (title.isEmpty() || bookIdStr.isEmpty() || quantityStr.isEmpty() || isbnStr.isEmpty() || shelfStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must have a value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);
            int quantity = Integer.parseInt(quantityStr);
            int isbn = Integer.parseInt(isbnStr);
            int shelf = Integer.parseInt(shelfStr);
            java.sql.Date publicationYear = new java.sql.Date(yrPubliSpinner.getDate().getTime());

            // Find and update the book in the ArrayList
            boolean found = false;
            for (BookInfo book : bookArray) {
                if (book.getBookId() == bookId) {
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setISBN(isbnStr);
                    book.setGenre(genre);
                    book.setQuantity(quantity);
                    book.setIsAvailable(true);
                    book.setShelfNum(shelf);
                    book.setYrPublished(publicationYear);
                    book.setStatus(status);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Book with ID " + bookId + " not found in list.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the book in the database
            PreparedStatement ps = database.prepareStatement(
                "UPDATE BookInventory SET Title=?, Author=?, ISBN=?, Genre=?, Availability=?, Quantity=?, BookStatus=?, ShelfNum=?, YearPublished=? WHERE BookID=?"
            );
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, isbn);
            ps.setString(4, genre);
            ps.setBoolean(5, true);
            ps.setInt(6, quantity);
            ps.setString(7, status);
            ps.setInt(8, shelf);
            ps.setDate(9, publicationYear);
            ps.setInt(10, bookId);

            int result = ps.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Book updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update book in database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            populateTable(); // Refresh the JTable

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity, ISBN, and Shelf must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a book from the table to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
    }
}
  
public void updateBookTable() {
    // Assuming bookArray is your ArrayList<BookInfo>
    DefaultTableModel model = (DefaultTableModel) bookTable.getModel();

    // Clear the table
    model.setRowCount(0);

    // Add books from the array list to the table
    for (BookInfo book : bookArray) {
        model.addRow(new Object[]{
            book.getTitle(),
            book.getAuthor(),
            book.getISBN(),
            book.getGenre(),
            book.getIsAvailable() ? "Available" : "Not Available",
            book.getBookId(),
            book.getQuantity(),
            book.getStatus(),
            book.getShelfNum(),
            book.getYrPublished()
        });
    }
} // <-- Properly close updateBookTable()

public void deleteFrame() {
    int selectedRow = bookTable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a book to delete.");
        return;
    }

    // Get the BookID of the selected row
    int bookID = (int) bookTable.getValueAt(selectedRow, 5); // Column 5 = BookID

    // Remove from the database
    try {
        PreparedStatement ps = database.prepareStatement("DELETE FROM BookInventory WHERE BookID = ?");
        ps.setInt(1, bookID);
        int result = ps.executeUpdate();
        if (result == 1) {
            JOptionPane.showMessageDialog(null, "Book deleted from database.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
    }

    // Remove from ArrayList
    bookArray.removeIf(book -> book.getBookId() == bookID);

    // Refresh the table
    updateBookTable();
}
public void search(String text){
        DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();
        
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + Pattern.quote(text));
        rowSorter.setRowFilter(rf);
        
        bookTable.setRowSorter(rowSorter);
        
//        rowSorter.setRowFilter(RowFilter.regexFilter(text));
}

   
    public static void main (String[] args){
        ArrayList<BookInfo> book = new ArrayList<>();
       new VIEWLISTUI().setVisible(true);
    }

    
      
   }

