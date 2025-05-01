/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

import BACKENDLIB.arryList;
import DSA.LL;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeTransac;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ReturnReport extends parentComponent {
 public JPanel mainPanel = new JPanel();
            JPanel pan3 = new JPanel();
            JPanel pan4 = new JPanel();
            JPanel pan4_5 = new JPanel();
            JLabel genrep = new JLabel("Book Return Reports");
            JLabel datelbl = new JLabel("Report Date");
            JLabel mostborrlbl = new JLabel("Total OverDue Books");
            JLabel totalborrlbl = new JLabel("Total Returned Books");
            JLabel totalfinelbl = new JLabel("Total Fine");
            JLabel cat = new JLabel("Category");
            JTable table = new JTable();
            JScrollPane tablescp = new JScrollPane(table);
            JTextField datef = new JTextField();
            JTextField totalborrf = new JTextField();
            JTextField mostborrf = new JTextField();
            JTextField totalfinef = new JTextField();
            JButton button = new JButton("Sort");
            JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Weekly", "Monthly", "Yearly"});
            JButton updatebtn = new JButton("Update Report");
            JButton viewbtn = new JButton("View Report");
            JButton backbtnReports = new JButton("Back");
            
            private LinkedlistBook book;
            private LinkedListAccounts accounts;
            private UserService userService;
            private User user;

            final int maxWeek = 104; // equi to 2 years
            final int maxMonth = 60; // 3 years
            final int maxYear = 15; // 15 years

            //static String col[] = {"DATE", "MOST BORROWED BOOK", "TOTAL BORROWED BOOKS", "TOTAL FINE"};
            static DefaultTableModel reporttab = new DefaultTableModel(new String[]{"Transaction ID", "ISBN", "User ID", "Due Date", "Borrow Date", "Fine", "Status"}, 0);
            JScrollPane scpreports = new JScrollPane(table);

            private String date, mostBorrowed;
            private int totalBooksBorrowed;
            private double totalFine;
             static arryList list;
                       private LL feeList = new LL();

            public ReturnReport(String date, String mostBorrowedBook, int totalBooksBorrowed, double totalFineCollected) {
                this.date = date;
                this.mostBorrowed = mostBorrowedBook;
                this.totalBooksBorrowed = totalBooksBorrowed;
                this.totalFine = totalFineCollected;
            }
              public JTable getTable() {
                return table;
            }
                 private void AddedTable() {
                reporttab.setRowCount(0); // Clear existing rows

                for (NodeTransac transaction : list.getTransactionRecords()) {
                    Object[] rowData = {
                        transaction.getID(),
                        transaction.code(),
                        transaction.getUserId(),
                        transaction.getDueDate(),
                        transaction.borrowDate(),
                        transaction.getPrice(),
                        transaction.getstatus()
                    };
                    reporttab.addRow(rowData);
                }
            }

            public ReturnReport( LinkedListAccounts accounts, UserService userService,User user,LinkedlistBook book,arryList arr ) {
                this.accounts = accounts;
                this.userService = userService;
                this.user = user;
                this.book = book;
                this.list = arr;

                mainPanel.setSize(1280,1049);
                mainPanel.setLayout(null);
                mainPanel.setBackground(new Color(0x6F1D1B));

                pan3.setBackground(new Color(0x6F1D1B));
                pan3.setLayout(null);
                pan3.setPreferredSize(new Dimension(800, 875));
                
                button.setBounds(1028, 85, 150, 54);
                button.setBackground(Color.white);
                button.setFont(new Font("Plus Jakarta Sans",Font.BOLD,24));
                button.addActionListener(e->AddedTables());


                pan4_5.setLayout(null);
                pan4_5.setBackground(new Color(0xD9D9D9));
                pan4_5.setBounds(0, 0, 1530, 86);

                genrep.setBounds(43, 26, 788, 77);
                genrep.setFont(new Font("Bebas Neue", Font.BOLD, 64));
                genrep.setForeground(new Color(0xFFFFFF));

                table.setModel(reporttab);
                scpreports.setBounds(43, 287, 1151, 571);
                table.addMouseListener(new MouseAdapter() {
                       @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        int row = table.getSelectedRow();
                        datef.setText(reporttab.getValueAt(row, 0).toString());
                        mostborrf.setText(reporttab.getValueAt(row, 1).toString());
                        totalborrf.setText(reporttab.getValueAt(row, 2).toString());
                        totalfinef.setText(reporttab.getValueAt(row, 3).toString());
                    }
                });


                totalfinef.setBounds(43, 158, 480, 54);
                totalfinef.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 32));
                totalfinef.setForeground(new Color(0x6F1D1B));
                
        

                totalborrlbl.setBounds(663, 112, 381, 54);
                totalborrlbl.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 30));
                totalborrlbl.setForeground(new Color(0xFFFFFF));


                
                
                


                totalfinelbl.setBounds(43, 112, 480, 30);
                totalfinelbl.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 30));
                totalfinelbl.setForeground(new Color(0xFFFFFF));

             double totalFine = list.calculateTotalPayments();
                System.out.println(totalFine);

    // Update the totalFinef text field with the calculated total fine
            totalfinef.setText(String.format("%.2f", totalFine));

              
                
             



                    totalborrf.setBounds(663, 158, 381, 54);
                totalborrf.setFont(new Font("Bebas Neue", Font.BOLD, 24));
                 totalborrf.setBorder(null); 
                 totalborrf.setLayout(new FlowLayout());

                 int total = list.getTransactionCount(); // Make sure this method is correct
        try {
            String yup = String.valueOf(total); // Convert total to string
            totalborrf.setText(yup);            // Set text field
            System.out.println("Total transactions: " + total); // Debug log
        } catch (Exception e) {
            System.out.println("Error in admin reports: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "An error occurred", "Message", JOptionPane.ERROR_MESSAGE);
        }


       mainPanel.add(pan4);
      //  mainPanel.add(pan4_5);
        mainPanel.add(scpreports);
      mainPanel.add(totalfinelbl);
      mainPanel.add(button);
  
        
        mainPanel.add(genrep);
        mainPanel.add(totalfinef);
  
 
        mainPanel.add(totalborrlbl);


    
        mainPanel.add(totalborrf);

        
         AddedTable();
         
        mainPanel.add(pan3);
        mainPanel.setVisible(true);
    }
             private void AddedTables() {
                     updateTableWithSortedBooks(reporttab);
                reporttab.setRowCount(0); // Clear existing rows
                for (NodeTransac transaction : list.getTransactionRecords()) {
                    Object[] rowData = {
                        transaction.getID(),
                        transaction.code(),
                        transaction.getUserId(),
                        transaction.getDueDate(),
                        transaction.borrowDate(),
                        transaction.getPrice(),
                        transaction.getstatus()
                    };
                    reporttab.addRow(rowData);
                }
            }
             private void updateTableWithSortedBooks(DefaultTableModel reporttab) {
    if (list != null && list.getTransactionCount() > 1) {
        System.out.println("Before sorting: " + list.getTransactionRecords());
        
        // Perform sorting
        list.quickSortTransactionsById();
        
        System.out.println("After sorting: " + list.getTransactionRecords());

        // Update JTable with sorted data
        reporttab.setRowCount(0); // Clear existing rows
        for (NodeTransac transaction : list.getTransactionRecords()) {
            reporttab.addRow(new Object[]{
                transaction.getID(),
                transaction.code(),
                transaction.getUserId(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
            });
        }
    } else {
        JOptionPane.showMessageDialog(null, "No transactions to sort!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}

            private double calculateTotalFine() {
    double totalFine = 0.0;
    Date returnDate = new Date();
                System.out.println(returnDate);
    // Loop through each transaction and calculate the fine
    for (NodeTransac transaction :arryList .getTransactionRecords()) {
        // Assuming each transaction has a genre, borrow date, due date, and return date
        String genre = transaction.genre();
         System.out.println("Genre: " + genre); // Get the genre of the book
        Date borrowDate = transaction.borrowDate(); // Get the borrow date
        Date dueDate = transaction.getDueDate(); // Get the due date
         // Get the return date

        // Calculate fine for this transaction
        double fineForThisTransaction = caculateFine(genre, borrowDate, dueDate, returnDate);

        // Add the fine to the total fine
        totalFine += fineForThisTransaction;
    }

    return totalFine;
}
                public double  caculateFine(String genre, Date brrwDate, Date DueDate, Date returnDate){
        System.out.println("Borrow Date: " + brrwDate);
    System.out.println("Due Date: " + DueDate);
    System.out.println("Return Date: " + returnDate);

    long diffInMillis = returnDate.getTime() - DueDate.getTime();
    long overdue = TimeUnit.MILLISECONDS.toDays(diffInMillis);
    System.out.println("Overdue: " + overdue);
    
       if (returnDate == null) {
        System.out.println("Return date is null. No fine can be calculated.");
        return 0.0;  // If the return date is null, return 0 fine
    }
       
    if (overdue <= 0) {
        return 0.0; // No fine if the book is not overdue
    }

    // Fine rates based on genre
    double fineFee = 0.0;
                    System.out.println("ito gebre mo" + genre);
    if (genre.equals("Fiction")) {
        fineFee = 8.0;
        System.out.println("Genre: Fiction");
    } else if (genre.equals("Non-Fiction")) {
        fineFee = 6.0;
        System.out.println("Genre: Non-Fiction");
    } else if (genre.equals("Education")) {
        fineFee = 10.0;
        System.out.println("Genre: Education");
    } else {
        return 5.0; // Return default fine if genre is invalid
    }

    // Calculate the fine based on overdue days and genre-specific rate
    double fineAmount = overdue * fineFee;
    
    // Add any other fees from feeList (if applicable)
    double totalFine = fineAmount + feeList.calculateTotalFee();
    
                    System.out.println("ito total fee mo " +feeList.calculateTotalFee());// Assuming calculateTotalFee() exists in feeList
    
    list.Payment.add(fineFee);
    // Print the calculated fine (for debugging purposes)
    System.out.println("Fine: " + fineAmount);
    System.out.println("Total Fine (including additional fees): " + totalFine);

    // Return the total fine
    return totalFine;

    }
      

    private void updateReport()  { //enqueue
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String updatedDate = datef.getText();
            String updatedMostBorr = mostborrf.getText();
            int updatedTotalBorr = Integer.parseInt(totalborrf.getText());
            double updatedTotalFine = Double.parseDouble(totalfinef.getText());

            reporttab.setValueAt(updatedDate, selectedRow, 0);
            reporttab.setValueAt(updatedMostBorr, selectedRow, 1);
            reporttab.setValueAt(updatedTotalBorr, selectedRow, 2);
            reporttab.setValueAt(updatedTotalFine, selectedRow, 3);

            String category = (String) categoryBox.getSelectedItem();
            switch (category) {
                case "Weekly":
//                    weekly.get(selectedRow).date = updatedDate;
//                    weekly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    weekly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    weekly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                case "Monthly":
//                    monthly.get(selectedRow).date = updatedDate;
//                    monthly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    monthly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    monthly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                case "Yearly":
//                    yearly.get(selectedRow).date = updatedDate;
//                    yearly.get(selectedRow).mostBorrowed = updatedMostBorr;
//                    yearly.get(selectedRow).totalBooksBorrowed = updatedTotalBorr;
//                    yearly.get(selectedRow).totalFine = updatedTotalFine;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid category selected!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            JOptionPane.showMessageDialog(null, "Report updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter proper values!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewReport() { //dequeue
        reporttab.setRowCount(0);

        LinkedList<adminReports> selectedList;
        switch (categoryBox.getSelectedItem().toString()) {
            case "Weekly":
//                selectedList = weekly;
//                break;
            case "Monthly":
//                selectedList = monthly;
//                break;
            case "Yearly":
//                selectedList = yearly;
//                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid category!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

////        for (adminReports report : selectedList) {
////            reporttab.addRow(new Object[]{report.date, report.mostBorrowed, report.totalBooksBorrowed, report.totalFine});
////        }
//
//        JOptionPane.showMessageDialog(null, "Reports loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
    }
}
