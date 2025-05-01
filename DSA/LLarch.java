/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;
 import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class LLarch {
  
    private NodeBook archiveHead; // Head node for the archived books
    private JTable archiveTable; // JTable to display archived books

    // Constructor
    public LLarch() {
        archiveHead = null;
    }

    // Method to view archived books in the JTable
    public void viewArchivedBooks(DefaultTableModel tableModel) {
        if (archiveHead == null) {
            JOptionPane.showMessageDialog(null, "No books in archive.");
        } else {
            // Clear the existing rows
            tableModel.setRowCount(0);

            NodeBook current = archiveHead;
            while (current != null) {
                // Add each archived book as a row in the table
                Object[] row = {
                    current.getTitle(),
                    current.getAuthor(),
                    current.getISBN(),
                    current.getGenre(),
                    current.getStatus(),
                    current.getBookId()
                };
                tableModel.addRow(row); // Add the row to the table model
                current = current.getNext();
            }
        }
    }

    // Method to add a book to the archive (for testing purposes)
    public void addToArchive(NodeBook book) {
        if (archiveHead == null) {
            archiveHead = book;
        } else {
            NodeBook current = archiveHead;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(book);
        }
    }

    // Sample JTable setup
    public JTable createArchiveTable() {
        // Define the column names
        String[] columns = {"Title", "Author", "ISBN", "Genre", "Status", "Book ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Create the JTable
        archiveTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(archiveTable);
        
        // Return the table
        return archiveTable;
    }
}
