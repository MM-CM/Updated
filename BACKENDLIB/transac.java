/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDLIB;
 import java.util.Date;

   public abstract interface transac {
    
    
      public void addBorrowRecord(String userId, int isbn, String transacId, Date borrowDate, Date dueDate, javax.swing.table.DefaultTableModel tb) ;
        // Add transaction to arryList
     
    
 public void returnBook(String transacId, double fine, javax.swing.table.DefaultTableModel tb);
        // Find the transaction in arryList
            // Update Fine column
        
    

  public void addRecord(String userId, int code, String id, Date dueDate, Date borrowDate, double fine, String status,javax.swing.table.DefaultTableModel tb);
  public void updateTransactionStatus(int index, String status, double fine);
}
