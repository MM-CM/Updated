/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDLIB;

 import DSA.NodeTransac;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class arryList {
     private NodeTransac head;
     private int size;
  public static ArrayList<String> UserID = new ArrayList<>();
     public static ArrayList<String> Status = new ArrayList<>();
   public  static ArrayList<Integer> ISBN = new ArrayList<>();
   public static ArrayList<Date> DueDate = new ArrayList<>();
   public static ArrayList<Date> BrrwDate = new ArrayList<>();
   public  static ArrayList<String> TransacId = new ArrayList<>();
  public  static ArrayList<Double> Payment = new ArrayList<>();
  public static ArrayList<String> user = new ArrayList<>();
  private ArrayList<Double> AddtionalFee = new ArrayList<>();
public double totalFine = 0.0;
   
    
    public arryList(){
      head = null;  
      size =0;
    }
           
       public static String [] Header ={"Transaction ID", "ISBN", " UserID", "BorrowDate","DueDate","Amount", "Status"};
     public static String [] choices ={"Select Option", "Overdue","Paid"};
     public static String [] sotring ={"Select Option", "Weekly","Monthly", "Yearly"};
        
     
      private static ArrayList<NodeTransac> transactions = new ArrayList<>();
       public static ArrayList<NodeTransac> transactionsRecords = new ArrayList<>();
       
       
      
    public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate, double amount, String status) {
    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate, amount, status);
    transactions.add(transaction);
       TransacId.add(transactionID);
}
      public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate,Date returnDate,String genre) {
              System.out.println("Adding transaction with Genre: " + genre);
    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate,returnDate,genre);
    transactions.add(transaction);
       TransacId.add(transactionID);
      }
      public void addPay(double payment){
        Payment.add(payment);
    System.out.println("Ito payment mo {arr}" + Payment);

    // Create a new transaction node with the payment amount
    NodeTransac newNode = new NodeTransac(payment);

    // If the head is null, initialize the head
    if (head == null) {
        head = newNode; // Assign the new node to head if list is empty
    } else {
        // Traverse the list to find the last node
        NodeTransac current = head;
        while (current.next != null) {
            current = current.next; // Move to the next node
        }
        // Append the new node to the end of the list
        current.next = newNode;
    }

    // Increment the size of the linked list
    size++;
    
    // If the payment is an overdue fine (negative), we will add it only to the total fine calculation
    if (payment < 0) {
        // You can call a method to update the total fine here
        updateTotalFine(payment);
    }
}
      public void updateTotalFine(double fineAmount) {
    // Add the overdue fine to the total fine
    totalFine += fineAmount;
    System.out.println("The overdue fine is added: " + fineAmount);
    System.out.println("Total Fine: " + totalFine);
}
      public double calculateTotalPayments() {
    double total = 0;

    // Loop through the Payment ArrayList
    for (double payment : Payment) {
        if (payment >= 0) { // Only include regular payments
            total += payment;
        }
    }
    return total;
}
    
      //
          
          
      
//}
           public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate,String genre) {
    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate,genre);
    transactions.add(transaction);
       TransacId.add(transactionID);
}

public static void updateTransaction(int index, String status, double overdue) {
    if (index >= 0 && index < transactions.size()) {
        NodeTransac transaction = transactions.get(index);
        transaction.setstatus(status);
        transaction.setPrice(overdue);
    }
}
public static void updateTransaction(int index, NodeTransac updatedTransaction) {
    if (index >= 0 && index < transactions.size()) {
        transactions.set(index, updatedTransaction);
        System.out.println("Transaction updated successfully.");
    } else {
        System.out.println("Invalid index. No transaction updated.");
    }
}

public static void removeTransaction(int index) {
    if (index >= 0 && index < transactions.size()) {
        transactions.remove(index);
    } else {
        System.out.println("Invalid index: " + index);
    }
}

public static ArrayList<String> getTransactionIDs() {
    ArrayList<String> ids = new ArrayList<>();
    for (NodeTransac transaction : transactions) {
        ids.add(transaction.getID());
    }
    return ids;
}

public int findTransactionIndexById(String transacId) {
    for (int i = 0; i < transactions.size(); i++) {
        if (transactions.get(i).getID().equals(transacId)) {
            return i; // Returns the index of the matching transaction
        }
    }
    return -1; // Returns -1 if the transaction ID is not found
}

public static ArrayList<Integer> getISBNs() {
    ArrayList<Integer> isbns = new ArrayList<>();
    for (NodeTransac transaction : transactions) {
        isbns.add(transaction.code());
    }
    return isbns;
}
public void getUser(String user){
    if (user.equals("Teacher")){
        
    }
}
public int getTransactionCount() {
 
    return transactions.size();
}
   
//
//    public static ArrayList<NodeTransac> getTransactions() {
//        return transactions;
//    }

    public static ArrayList<NodeTransac> getTransactions() {
      return transactions;
    }
  public void quickSortTransactionsById() {
          ArrayList<NodeTransac> bune = this.getTransactionRecords();
     quickSort(bune,0, bune.size() - 1);
      System.out.println("Nag sosort namn bading");// Initial call to QuickSort
}

// QuickSort helper method
private void quickSort(ArrayList<NodeTransac> transactions, int low, int high) {
    if (low < high) {
        // Find the pivot index
        int pivotIndex = partition(transactions, low, high);

        // Recursively sort the two halves
        quickSort(transactions, low, pivotIndex - 1); // Left half
        quickSort(transactions, pivotIndex + 1, high); // Right half
    }
}

// Partition method
private int partition(ArrayList<NodeTransac> transactions, int low, int high) {
    String pivot = transactions.get(high).getID();
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (transactions.get(j).getID().compareTo(pivot) < 0) {
            i++;
            swap(transactions, i, j);
        }
    }

    swap(transactions, i + 1, high);
    return i + 1;
}

// Swap method
private void swap(ArrayList<NodeTransac> transactions, int i, int j) {
    NodeTransac temp = transactions.get(i);
    transactions.set(i, transactions.get(j));
    transactions.set(j, temp);
}
 

  public static void archiveTransaction(NodeTransac transaction) {
    transactionsRecords.add(transaction);
}
public void updateTransactionStatus(int index, String status, double fine, DefaultTableModel tb) {
    // Validate index and list size
    if (index < 0 || index >= transactions.size()) {
        System.out.println("Index out of bounds or transaction not found. Index: " + index);
        return;
    }

    // Get the transaction and update it
    NodeTransac transaction = transactions.get(index);
    transaction.setstatus(status);
    transaction.setPrice(fine);

    // Reflect updates in the table model if necessary
    if (tb != null && index < tb.getRowCount()) {
        tb.setValueAt(status, index, tb.findColumn("Status"));
        tb.setValueAt(fine, index, tb.findColumn("Amount"));
    } else {
        System.out.println("Table model update skipped: invalid index or missing table.");
    }
}

public static ArrayList<NodeTransac> getTransactionRecords() {
    return transactionsRecords;
}
public void populateTable(DefaultTableModel tb) {
    tb.setRowCount(0);  // Clear existing rows

    // Get the updated transaction records after sorting
    ArrayList<NodeTransac> transactions = this.getTransactionRecords();
    for (NodeTransac transaction : transactions) {
        Object[] rowData = {
            transaction.getUserId(),
            transaction.code(),
            transaction.getID(),
            transaction.getDueDate(),
            transaction.borrowDate(),
            transaction.getPrice(),
            transaction.getstatus()
        };
        tb.addRow(rowData); // Add the new row to the table
    }
}
}
