    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;
import BACKENDLIB.arryList;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import DSA.NodeTransac;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class adminReports extends parentComponent {
   public JPanel mainPanel = new JPanel();
            JPanel pan3 = new JPanel();
            JPanel pan4 = new JPanel();
            JPanel pan4_5 = new JPanel();
            JLabel genrep = new JLabel("Borrowing Reports");
            JLabel datelbl = new JLabel("Report Date");
            JLabel mostborrlbl = new JLabel("Most Borrowed Books");
            JLabel totalborrlbl = new JLabel("Total Borrowed Books");
            JLabel totalfinelbl = new JLabel("Total Fine");
            JLabel cat = new JLabel("Category");
            JTable table = new JTable();
            JScrollPane tablescp = new JScrollPane(table);
            JTextField datef = new JTextField();
            JTextField totalborrf = new JTextField();
            JTextField mostborrf = new JTextField();
            JTextField totalfinef = new JTextField();
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

            public adminReports(String date, String mostBorrowedBook, int totalBooksBorrowed, double totalFineCollected,arryList list) {
                this.date = date;
                this.mostBorrowed = mostBorrowedBook;
                this.totalBooksBorrowed = totalBooksBorrowed;
                this.totalFine = totalFineCollected;
                this.list= list;
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

            public adminReports( LinkedListAccounts accounts, UserService userService,User user,LinkedlistBook book,arryList list ) {
                this.accounts = accounts;
                this.userService = userService;
                this.user = user;
                this.book = book;
                this.list = list;

                mainPanel.setSize(1280,1049);
                mainPanel.setLayout(null);
                mainPanel.setBackground(new Color(0x6F1D1B));

                pan3.setBackground(new Color(0x6F1D1B));
                pan3.setLayout(null);
                pan3.setPreferredSize(new Dimension(800, 875));



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



                mostborrlbl.setBounds(43, 112, 480, 50);
                mostborrlbl.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 32));
                mostborrlbl.setForeground(new Color(0xBB9457));

                totalborrlbl.setBounds(663, 112, 480, 50);
                totalborrlbl.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 30));
                totalborrlbl.setForeground(new Color(0xBB9457));




                totalfinelbl.setBounds(20, 378, 480, 30);
                totalfinelbl.setFont(new Font("Cambria", Font.BOLD, 30));
                totalfinelbl.setForeground(new Color(0xBB9457));



                mostborrf.setBounds(43, 158, 538, 50);
                mostborrf.setFont(new Font("Bebas Neue", Font.BOLD, 30));
                mostborrf.setBorder(null);
                
              String most=  book.displayMostBorrowedBooks();
              mostborrf.setText(most);



                    totalborrf.setBounds(663, 158, 538, 54);
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
  
            mainPanel.add(mostborrf);
        
        mainPanel.add(genrep);
  
        mainPanel.add(mostborrlbl);
        mainPanel.add(totalborrlbl);


    
        mainPanel.add(totalborrf);

        
         AddedTable();
         
        mainPanel.add(pan3);
        mainPanel.setVisible(true);
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
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new adminReports().setVisible(true));
    }
    }
