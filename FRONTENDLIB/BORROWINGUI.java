
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import BACKENDUSER.NodeHistory;
import BACKENDUSER.TranascHisotry;
import DSA.BookInfo;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import static DSA.LinkedlistBook.quickSortByTitle;
 //import static DSA.LinkedlistBook.quickSort;
import DSA.NodeBook;
import DSA.NodeTransac;
import DSA.TransactionInfo;
//import static FRONTENDLIB.VIEWLISTUI.bookList;
import FRONTENDUSER.BOOKHISTORYUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import com.toedter.calendar.JDateChooser;
//import static FRONTEND.VIEWLISTUI.bookList;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class BORROWINGUI extends parentComponent {
    
    private JComboBox<String> genreComboBox;
     private static  int MAX_BORROW_DAYS=0;
    
    private JComboBox<String> Role;
    private JPanel bookPanel;
    private Map<String, List<String>> booksByGenre;
    private JLabel a, lb1, lb2, lb3, lb4, b;
    private JTextField isbn;
    private JButton prc, cnl, bbrw, search,ref,sort;
    private JPanel pnl1, pnl2, pnl3, pnl4;
    private JTextField userId, date, days, transac, title, code, authors, avails, genre;
    JPanel  panel2, panel3, panel4, panel5;
    JPanel Mainpanel = new JPanel();
    private JDateChooser dateSpinnerBorrow;
    private JDateChooser dateSpinnerDue;
    private JScrollPane pane;
    private JScrollPane sp;
    public static LinkedlistBook book;
    public LinkedListAccounts acc;
    private static VIEWLISTUI viewListUI;
    public LLhistory hh;
   public static arryList transacsac = new arryList();
    private DefaultTableModel tableModel;
    private JTable table;
    public NodeBook books;
    public UserService users;
    public TranascHisotry his;
    private User user;
    private static RecordPayUI list;
   JPanel panel = new JPanel();
    
    List<NodeBook> selectedBooks = new ArrayList<>();
    
    public static ArrayList<BookInfo> bookArray = new ArrayList<>();
//    ImageIcon searchIMG = new ImageIcon("images\\search.png");
       String[] columnNames = {"Title", "Author", "ISBN", "Genre", "Availability"};
       
         Connection database;
       Statement stmt;
       public static ArrayList<TransactionInfo> transactionList = new ArrayList<>();

    public BORROWINGUI(LinkedlistBook list,RecordPayUI ist,LinkedListAccounts acc, User user,LLhistory hh) {
//        try {
        
            database = DatabaseCon.connectDB();
            BORROWINGUI.book = list;
           BORROWINGUI.list =ist;
           this.acc = acc;
           this.user = user;
           users = UserService.getInstance(); 
           this.his = new TranascHisotry(user);
           this.hh = hh;
     
          
          
           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
               centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  
               
            tableModel = new DefaultTableModel(columnNames, 0);
            table = new JTable(tableModel);
        

            // Initialize main panels and components
          for (int i = 0; i < table.getColumnCount(); i++) {
    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
            populateTable();
          System.out.println("Book array size: " + bookArray.size());
          JTableHeader header = table.getTableHeader();
          header.setFont(new Font("Bebas Neue",Font.BOLD,18));
          header.setBackground(new Color(0xBB9457));
          header.setForeground(new Color(0x6F1D1B));
          header.setBorder(BorderFactory.createRaisedBevelBorder());
          
          table.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,12));
          table.setForeground(new Color(0x6F1D1B));
          
            pnl2 = new JPanel();
            pnl2.setLayout(null);
            pnl2.setBackground(new Color(0xD9D9D9));
            pnl2.setBounds(0, 0, 1530, 86);

        

            lb1 = new JLabel("ISBN");
            lb1.setFont(new Font("Bebas Neue", Font.BOLD, 36));
            lb1.setBounds(405, 50, 100, 43);
            lb1.setForeground(new Color(0x99582A));

            isbn = new JTextField();
            isbn.setBounds(405, 100, 356, 76);
            isbn.setBackground(new Color(0xBB9457));
            isbn.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbn.setForeground(Color.white);
            isbn.setLayout(null);
        //KEYEVENTS
            KeyListener l = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                @Override
                public void keyPressed(KeyEvent e) { }

                @Override
                public void keyReleased(KeyEvent e) {
                    String text = isbn.getText();
                    search(text);
                     populateTable();               
                }
            };
            isbn.addKeyListener(l);
isbn.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent evt) {
        populateTable();
        isbn.setText("");

        isbn.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(isbn.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 search(isbn.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(isbn.getText());
            }

        });
    }
});
              populateTable();  
            lb2 = new JLabel("Genre");
            lb2.setFont(new Font("Bebas Neue", Font.BOLD, 36));
            lb2.setBounds(33, 50, 200, 43);
            lb2.setForeground(new Color(0xBB9457));
            
            
if (!BookBase.genreList.contains("Select genre")) {
    BookBase.genreList.add(0, "Select genre");
}

           genreComboBox = new JComboBox<>(BookBase.genreList.toArray(new String[0]));
            genreComboBox.setSelectedIndex(0);
            genreComboBox.setBounds(33, 100, 333, 76);
            genreComboBox.setPreferredSize(new Dimension(333, 76));
            genreComboBox.setBackground(new Color(0x99582A));
            genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            genreComboBox.setForeground(Color.white);
            
           genreComboBox.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
        // Remove "Select genre" placeholder if present
        if (genreComboBox.getItemCount() > 0 && genreComboBox.getItemAt(0).equals("Select genre")) {
            genreComboBox.removeItem("Select genre");
        }
    }
});

genreComboBox.addActionListener((ActionEvent e) -> {
    DefaultTableModel model = (DefaultTableModel) tableModel;
    model.setRowCount(0); // Clear the table
    boolean found = false;

    String selectedGenre = (String) genreComboBox.getSelectedItem();

    if (!selectedGenre.equals("All")) {
        // Filter books by genre
        for (BookInfo book : bookArray) {
            if (book.getGenre().equalsIgnoreCase(selectedGenre)) {
                String availability = book.getIsAvailable() ? "Available" : "Not Available";
                model.addRow(new Object[] {
                    book.getTitle(),
                    book.getAuthor(),
                    book.getISBN(),
                    book.getGenre(),
                    availability
                });
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No books found in the genre: " + selectedGenre, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        // If "All" is selected, display all books
        for (BookInfo book : bookArray) {
            String availability = book.getIsAvailable() ? "Available" : "Not Available";
            model.addRow(new Object[] {
                book.getTitle(),
                book.getAuthor(),
                book.getISBN(),
                book.getGenre(),
                availability
            });
        }
    }
    // Call populateTable again to update the display
    populateTable();
});
            // Book panel for displaying books of the selected genre
            bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
            bookPanel.setBounds(33, 250, 728, 300);
            bookPanel.setBackground(new Color(0x6F1D1B));

            pnl1 = new JPanel();
            pnl1.setLayout(null);
            pnl1.setBackground(new Color(0x6F1D1B));
            pnl1.setBounds(-7, 86, 1537, 875);

           

            prc = new JButton("Proceed");
            prc.setBounds(200, 903, 271, 88);
            prc.setFont(new Font("Bebas Neue", Font.BOLD, 50));
            prc.setForeground(new Color(0x6F1D1B));
            prc.setBackground(Color.white);
            prc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(prc)) {
                        Confirm();
                    }
                   // updateBookDisplay(selectedGenre);
                }
            });

            
             code = new JTextField();
         code.setBounds(26, 381, 440, 60);
         code.setBackground(new Color(0x99582A));
         code.setForeground(Color.white);
            code.setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
         code.setBorder(null);
         
           title = new JTextField();
         title.setBounds(26, 81, 440, 60);
         title.setBackground(new Color(0x99582A));
         title.setForeground(Color.white);
         title.setFont(new Font("Plus Jakarta Sans",Font.ITALIC,28));
          title.setBorder(null);
         
           authors = new JTextField();
         authors.setBounds(26, 225, 440, 60);
         authors.setBackground(new Color(0x99582A));
         authors.setForeground(Color.white);
         authors.setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          authors.setBorder(null);
         
          genre = new JTextField();
         genre.setBounds(26, 518 , 200, 60);
         genre.setBackground(new Color(0x99582A));
         genre.setForeground(Color.white);
         genre  .setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          genre.setBorder(null);
         
         avails = new JTextField();
         avails.setBounds(250, 518, 215, 60);
         avails.setBackground(new Color(0x99582A));
         avails.setForeground(Color.white);
         avails  .setFont(new Font("Plus Jakarta Sans", Font.ITALIC,28));
          avails.setBorder(null);
         
          JLabel  codelbl = new JLabel("ISBN");
          codelbl.setFont(new Font("Plus Jakarta Sans", Font.PLAIN,36));
          codelbl.setBounds(26, 323, 200, 59);
          codelbl.setForeground(Color.white);
          
           JLabel  titlelbl = new JLabel("Title");
          titlelbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          titlelbl.setBounds(26, 22, 200, 59);
          titlelbl.setForeground(Color.white);
          
            JLabel  authorlbl = new JLabel("Author");
          authorlbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          authorlbl.setBounds(26, 164, 200, 59);
          authorlbl.setForeground(Color.white);
          
              JLabel  genrelbl = new JLabel("Genre");
          genrelbl.setFont(new Font("Bebas Neue", Font.BOLD,36));
          genrelbl.setBounds(26, 461, 200, 54);
          genrelbl.setForeground(Color.white);   
          
             JLabel  avail = new JLabel("Availability");
          avail.setFont(new Font("Bebas Neue", Font.BOLD,30));
          avail.setBounds(303, 461, 200, 54);
          avail.setForeground(Color.white);   
          
          
          
        
         
         table.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent evt) {
                                Table();
                            }
                            });
         
     
        pnl4 = new JPanel();
        pnl4.setLayout(null);
        pnl4.setBounds(800, 0, 559, 1054);
        pnl4.setBackground(new Color(0xBB9457));
        
        JLabel details = new JLabel("Details");
        details.setBounds(119, 130, 356, 121);
        details.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,70));
        details.setForeground(Color.white);
        
        JPanel pnl5 =new JPanel();
        pnl5.setLayout(null);
        pnl5.setBounds(6, 245, 460, 642);
        pnl5.setBackground(new Color(111,29,27,43));

       
          
                 pnl5.add(code);
        pnl5.add(authors);
        pnl5.add(title);
         pnl5.add(genre);
          pnl5.add(avails);
          pnl5.add(codelbl);
           pnl5.add(authorlbl);
            pnl5.add(titlelbl);
             pnl5.add(genrelbl);
              pnl5.add(avail);
              
              pnl4.add(pnl5);
              pnl4.add(prc);
              pnl4.add(details);

            // More component initializations here...

            sp = new JScrollPane(table);
            sp.setBounds(33, 240, 728, 588);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            updateTable();
          table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get ISBN from the selected row
            String isbnString = tableModel.getValueAt(selectedRow, 2).toString(); // Assuming column 2 = ISBN

            // Find the book from bookArray using ISBN
            for (BookInfo book : bookArray) {
                if (book.getISBN().equals(isbnString)) {
                    // Check if already added (optional)
                    if (!selectedBooks.contains(book)) {
                        selectedBooks.add(book); // Add to selectedBooks list
                        updateSelectedBooksPanel(); // Refresh the panel
                    }
                    break;
                }
            }
        }
    }
});
                
                b = new JLabel("Borrowing");
        b.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 65));
        b.setBounds(31, 20, 826, 100);
        b.setForeground(Color.white);
            // Add panels to the JFrame
//                ImageIcon icon = new ImageIcon("images\\sort.png");
//       Image imgIcon = icon.getImage();  // Transform it 
//            Image newImgICOn = imgIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
//           ImageIcon icoon = new ImageIcon(newImgICOn);     
            
//      sort = new JButton();
//     
//      sort.setBounds(700, 44, 36, 56);
//      sort.setLayout(null);
//      sort.setBackground(new Color(0x6F1D1B));
//       sort.setIcon(icoon);
//      sort.setBorder(BorderFactory.createEmptyBorder());
//      sort.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                      NodeBook[] booksArray = bookList.toArray();
//                 if (bookList != null && booksArray.length > 1) {
//              
//            // Perform quick sort
//           bookList.sortByTitle();
//            
//            // Update the table or UI component displaying the books
//           updateBookTable();
//            
//        } else {
//            JOptionPane.showMessageDialog(null, "No books to sort!", "Info", JOptionPane.INFORMATION_MESSAGE);
//        }
//            }
//    });
            
  
      
// Remove border for a cleaner look
      
  ImageIcon refresh = new ImageIcon("images\\refresh.png");
       Image refIcon = refresh.getImage();  // Transform it 
            Image reficon = refIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
           ImageIcon refs = new ImageIcon(reficon);     
           
      ref = new JButton();
      ref.setBounds(646, 45, 36, 56);
      ref.setBackground(new Color(0x6F1D1B));
      ref.setBorder(BorderFactory.createEmptyBorder());
      ref.setIcon(refs);
      ref.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              populateTable();
            }
      });
            

            pnl1.add(lb1);
            pnl1.add(lb2);
            pnl1.add(isbn);
            pnl1.add(ref);
//                 pnl1.add(sort);
          
           //pnl1.add(search);
            pnl1.add(sp);
            pnl1.add(genreComboBox);
            pnl1.add(bookPanel);
           // pnl3.add(b);
           
           Mainpanel.add(b);
            Mainpanel.add(pnl4); 
            Mainpanel.add(pnl1);
        
            

            // JFrame settings
            Mainpanel.setBackground(new Color(0x6F1D1B));
            Mainpanel.setSize(1280, 1049);
            Mainpanel.setBounds(0, 0, 1351, 1049);
            Mainpanel.setVisible(true);
            Mainpanel.setLayout(null);


//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "An error occurred" + e.getMessage());
//            System.out.println(e.getStackTrace());
//          
//        }
    }
    

   private void populateTable() {
    // Clear any existing rows from the table
    tableModel.setRowCount(0);

    // SQL query to retrieve book information
    String query = "SELECT Title, Author, ISBN, Genre, Availability FROM BookInventory";

    try (PreparedStatement ps = database.prepareStatement(query)) {
        // Execute the query and get the results
        ResultSet rs = ps.executeQuery();

        // Check if the result set is empty
        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "No books found in the database.");
        }

        // Iterate through the result set and populate the table
        while (rs.next()) {
            String title = rs.getString("Title");
            String author = rs.getString("Author");
            long isbn = rs.getLong("ISBN");  // Assuming ISBN is stored as BIGINT
            String genre = rs.getString("Genre");
            boolean isAvailable = rs.getBoolean("Availability");

            // Add a row to the table for each book
            tableModel.addRow(new Object[] {
                title,
                author,
                isbn,
                genre,
                isAvailable ? "Available" : "Not Available"
            });
        }

        // Optionally, revalidate/repaint the table after updating
        table.revalidate();
        table.repaint();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading books from the database.");
    }
}
    private void Confirm() {
    try {
        JFrame frm = new JFrame();
        JLabel rol = new JLabel("Borrower Role");
        JLabel label = new JLabel("Student Number/Faculty Number");
        JLabel label1 = new JLabel("Borrow Date(DD/MM/YY)");
        JLabel label2 = new JLabel("Period(Days)");
        JLabel label3 = new JLabel("Transaction ID");

        label.setBounds(127, 184, 400, 43);
        label1.setBounds(127, 307, 400, 43);
        label2.setBounds(590, 307, 400, 43);
        label3.setBounds(127, 427, 400, 43);
        rol.setBounds(127, 44, 400, 43);

        label.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label1.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label2.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        label3.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        rol.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        

        label.setForeground(new Color(0x6F1D1B));
        label1.setForeground(new Color(0x6F1D1B));
        label2.setForeground(new Color(0x6F1D1B));
        label3.setForeground(new Color(0x6F1D1B));
        rol.setForeground(new Color(0x6F1D1B));
        
        Role = new JComboBox<>(BookBase.choice);
         Role.setBounds(128, 91, 613, 76);
         Role.setPreferredSize(new Dimension(613,76));
         Role.setBackground(new Color(0xD9D9D9));
         Role.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,16));
         Role.setForeground(new Color(0x6F1D1B));
         


        dateSpinnerBorrow = new JDateChooser();
        Date borrowDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dateSpinnerBorrow.getDate()));
        dateSpinnerBorrow.setBounds(127, 347, 302, 76);
        dateSpinnerBorrow.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        dateSpinnerBorrow.setForeground(new Color(0x6F1D1B));
        dateSpinnerBorrow.setBackground(new Color(0xD9D9D9));
        frm.add(dateSpinnerBorrow); 

        // Due Date Picker using JSpinner
        dateSpinnerDue = new JDateChooser();
        Date dueDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dateSpinnerDue.getDate()));
        dateSpinnerDue.setBounds(459, 347, 281, 76);
        dateSpinnerDue.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 24));
        dateSpinnerDue.setForeground(new Color(0x6F1D1B));
        dateSpinnerDue.setBackground(new Color(0xD9D9D9));
        frm.add(dateSpinnerDue);
        
        
        
       
        userId = new JTextField();
        userId.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));
        
         String id = Integer.toString(acc.generateRandomID());
        transac = new JTextField();
        transac.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 24));

        userId.setBounds(128, 231, 613, 76);
        transac.setBounds(127, 474, 613, 76);

        userId.setBackground(new Color(0xD9D9D9));
        transac.setBackground(new Color(0xD9D9D9));
        transac.setText(id);

        bbrw = new JButton();

        bbrw.setBounds(279, 555, 310, 88);
        bbrw.setBackground(new Color(0xD9D9D9));
        bbrw.setText("Confirm");
        bbrw.setForeground(new Color(0x6F1D1B));
        bbrw.setFont(new Font("Bebas Neue", Font.BOLD, 64));
        bbrw.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Determine max borrow days based on selected role
            int in = Role.getSelectedIndex();
            if (in == 0) {
                MAX_BORROW_DAYS = 5;
            } else if (in == 1) {
                MAX_BORROW_DAYS = 3;
            }

            String userid = userId.getText().trim();
            String transacs = transac.getText().trim();
            String selectedRole = (String) Role.getSelectedItem();

            if (userid.isEmpty() || transacs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                return;
            }

            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row from the table.");
                return;
            }

            String title = tableModel.getValueAt(row, 0).toString();
            String author = tableModel.getValueAt(row, 1).toString();
            String genre = tableModel.getValueAt(row, 3).toString();
            int isbn = (int) table.getValueAt(row, 2);
            Object value = table.getValueAt(row, 4);

            boolean isAvailable = false;
            if (value instanceof String) {
                isAvailable = "Available".equalsIgnoreCase((String) value);
            } else if (value instanceof Boolean) {
                isAvailable = (Boolean) value;
            } else {
                JOptionPane.showMessageDialog(null, "Unexpected value in the availability column.");
                return;
            }

            if (!isAvailable) {
                JOptionPane.showMessageDialog(null, "This book is not available for borrowing.");
                return;
            }

             dateSpinnerBorrow = new JDateChooser();
                Date borrowDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dateSpinnerBorrow.getDate()));
                dateSpinnerDue = new JDateChooser();
                Date dueDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(dateSpinnerDue.getDate()));

            if (borrowDate == null || dueDate == null) {
                JOptionPane.showMessageDialog(null, "Please select both borrow and due dates.");
                return;
            }

            LocalDate borrowLocalDate = borrowDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dueLocalDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (!canBorrow(borrowLocalDate, dueLocalDate)) {
                JOptionPane.showMessageDialog(null, "The due date cannot exceed the maximum borrowing period.");
                return;
            }

            if (borrowDate.after(dueDate)) {
                JOptionPane.showMessageDialog(null, "Due date must be after the borrow date.");
                return;
            }

            if (arryList.TransacId.contains(transacs)) {
                JOptionPane.showMessageDialog(null, "Transaction ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a new history entry
            String studentNum = userId.getText();
            if (studentNum != null) {
                NodeHistory newHistory = new NodeHistory(
                    title,
                    author,
                    user.getUserID(),
                    genre,
                    borrowDate,
                    dueDate,
                    0.0
                );
                hh.addNode(newHistory);

                updateTable();

                // Add transaction
                TransactionInfo transaction = new TransactionInfo(transacs, isbn, userid, borrowDate, dueDate, 0.0, genre);
                transactionList.add(transaction);

                book.borrowBook(isbn);

                tableModel.addRow(new Object[]{transacs, isbn, userid, borrowDate, dueDate, 0.0, "Borrowed"});

                // Format borrow and due date columns
                DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
                    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

                    @Override
                    protected void setValue(Object value) {
                        if (value instanceof Date) {
                            setText(df.format((Date) value));
                        } else {
                            super.setValue(value);
                        }
                    }
                };
                table.getColumnModel().getColumn(3).setCellRenderer(dateRenderer);
                table.getColumnModel().getColumn(4).setCellRenderer(dateRenderer);

                // Set book as unavailable
                book.updateAvailability(isbn, false);
                tableModel.setValueAt(false, row, 4);
                updateTable();

                JOptionPane.showMessageDialog(null, "Book has been borrowed!");
                frm.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "The user ID does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});

        frm.add(label);
        frm.add(label1);
        frm.add(label2);
        frm.add(label3);
        frm.add(Role);
        
        frm.add(rol);
        frm.add(userId);
        frm.add(transac);

        frm.add(dateSpinnerBorrow); // Add the Borrow Date spinner
        frm.add(dateSpinnerDue);

        frm.add(bbrw);
        frm.setResizable(false);
        frm.setLayout(null);
        frm.setSize(868, 689);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        frm.setTitle("Confirm transaction");
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred");
    }
}
public static boolean canBorrow(LocalDate borrowDate, LocalDate dueDate) {
    // Calculate the maximum allowed due date from the borrow date
    LocalDate maxDueDate = borrowDate.plusDays(MAX_BORROW_DAYS);  // Add max allowed days to borrow date

    // Debugging: Print max allowed due date
    System.out.println("Max Due Date: " + maxDueDate);
    System.out.println("Due Date: " + dueDate);

    // Check if the due date is before or on the maximum allowed due date
    return !dueDate.isAfter(maxDueDate); // If dueDate is after maxDueDate, return false
}

public void search(String text){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + Pattern.quote(text));
        rowSorter.setRowFilter(rf);
        
        table.setRowSorter(rowSorter);
        
//        rowSorter.setRowFilter(RowFilter.regexFilter(text));
}
//    public void updateUserHistoryTable(User user) {
//          LLhistory userHistory = user.getHistory();
//    // Fetch the user's history
//  Object[][] historyData = userHistory.getHistoryData();  // Get data from user's history
//
//        // Update the table model with the new data
//        DefaultTableModel model = (DefaultTableModel) nook.table.getModel();
//        model.setRowCount(0);  // Clear the table
//        for (Object[] row : historyData) {
//            model.addRow(row);  // Add new rows
//        }
//
//        // Optional: refresh/revalidate the table
////        historyTable.revalidate();
////        historyTable.repaint();
//}


private void updateTable() {
    // Clear any existing rows in the table
    tableModel.setRowCount(0);

    // Iterate through the bookArray (ArrayList of BookInfo objects)
    for (BookInfo book : bookArray) {
        // Retrieve the necessary data from the BookInfo object
        String title = book.getTitle();
        String author = book.getAuthor();
        long isbn = book.getISBN();
        String genre = book.getGenre();
        boolean isAvailable = book.getIsAvailable();

        // Add a new row to the table with the retrieved data
        tableModel.addRow(new Object[]{
            title,                      // Title
            author,                     // Author
            isbn,                       // ISBN
            genre,                      // Genre
            isAvailable ? "Available" : "Not Available"  // Availability (boolean as string)
        });
    }
}
         


   
//    public void actionPerformed(ActionEvent e) {
//        // Handle action events
//    }
  public void updateBookTable() {
    // Assuming bookArray is your ArrayList<BookInfo>
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    // Clear the table
    model.setRowCount(0);

    // Add books from the array list to the table (only Title, Author, ISBN, Genre, and Availability)
    for (BookInfo book : bookArray) {
        model.addRow(new Object[]{
            book.getTitle(),            // Title
            book.getAuthor(),           // Author
            book.getISBN(),             // ISBN
            book.getGenre(),            // Genre
            book.getIsAvailable() ? "Available" : "Not Available" // Availability
        });
    }
}
//
//    Object[][] historyData = user.getHistory().getHistoryData(); // Get user history as 2D array
//
//    // Debugging output
//    System.out.println("Updating table with the following data:");
//    for (Object[] row : historyData) {
//        System.out.println(Arrays.toString(row));
//    }
//
//    // Clear existing rows in the table
//    tableModel.setRowCount(0);
//
//    // Add each history record to the table
//    for (Object[] record : historyData) {
//        tableModel.addRow(record);
//    }
//}
//     private void updateTableWithSortedBooks(DefaultTableModel defTab) {
//    // Get the sorted books data
//    Object[][] sortedBooks = bookList.sortByTitle(); // Calls quickSortByTitle() inside sortByTitle()
//
//    // Clear the table model
//    defTab.setRowCount(0);
//
//    // Add the sorted books to the table model
//    for (Object[] book : sortedBooks) {
//        defTab.addRow(book);
//    }
//}
                
    public void resetTableData() {

    tableModel = (DefaultTableModel) table.getModel();

    // Clear the table
    tableModel.setRowCount(0);

    // Iterate over bookArray and add data to the table
    for (BookInfo book : bookArray) {
        String availability = book.getIsAvailable() ? "Available" : "Not Available"; // Get availability
        tableModel.addRow(new Object[]{
            book.getTitle(),   // Title
            book.getAuthor(),  // Author
            book.getISBN(),    // ISBN
            book.getGenre(),   // Genre
            availability       // Availability
        });
    }
}

private void updateSelectedBooksPanel() {
    JPanel selectedBooksPanel = new JPanel();
    selectedBooksPanel.removeAll(); // Clear previous selections
    for (BookInfo book : bookArray) {
        JLabel label = new JLabel(book.getTitle() + " (ISBN: " + book.getISBN() + ")");
        selectedBooksPanel.add(label);
    }
    selectedBooksPanel.revalidate();
    selectedBooksPanel.repaint();
}

public void Table() {
    if (table.getSelectedRow() != -1) {
        int row = table.getSelectedRow();
        title.setText(tableModel.getValueAt(row, 0).toString());
        code.setText(tableModel.getValueAt(row, 2).toString());
        authors.setText(tableModel.getValueAt(row, 1).toString());
        genre.setText(tableModel.getValueAt(row, 3).toString());
        avails.setText(tableModel.getValueAt(row, 4).toString());
    }
}

//public void updateBookTable() {
//    // Assuming bookArray is an ArrayList<BookInfo>
//    DefaultTableModel model = (DefaultTableModel) table.getModel();
//    
//    // Clear the table
//    model.setRowCount(0);
//    
//    // Iterate through the bookArray and add rows to the table
//    for (BookInfo book : bookArray) {
//        model.addRow(new Object[]{
//            book.getTitle(),                  // Title
//            book.getAuthor(),                 // Author
//            book.getISBN(),                   // ISBN
//            book.getGenre(),                  // Genre
//            book.getIsAvailable() ? "Available" : "Not Available", // Availability
//            book.getBookId(),                 // Book ID
//            book.getQuantity(),               // Quantity
//            book.getStatus(),                 // Status
//            book.getShelfNum(),               // Shelf Number
//            book.getYrPublished()             // Year Published
//        });
//    }

     public static void main (String [] args){
   
     }
     }
