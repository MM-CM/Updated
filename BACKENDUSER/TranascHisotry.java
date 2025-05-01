/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDUSER;
import DSA.NodeTransac;
import LogSigBackEnd.User;
import java.util.ArrayList;
import java.util.Date;

public class TranascHisotry {
    private ArrayList<NodeHistory> transactions;
    private LLhistory transactionHistory;  
    private User user;
    
    public TranascHisotry(User user){
         this.transactions = new ArrayList<>();
          this.transactionHistory = new LLhistory();
          this.user = user;
    }
    public void addTransaction(NodeHistory transaction) {
    transactionHistory.addNode(transaction);  // Add to linked list
    transactions.add(transaction);           // Add to ArrayList
}

public Object[][] getTransactionsAsData() {
    return transactionHistory.getHistoryData(user);  // Use linked list data
}
      public ArrayList<NodeHistory> getTransactionHistoryAsArrayList() {
        ArrayList<NodeHistory> historyList = new ArrayList<>();
        NodeHistory current = transactionHistory.getHead();  // Accessing head of linked list
        while (current != null) {
            historyList.add(current);  // Add each NodeHistory to ArrayList
            current = current.getNext();
        }
        return historyList;
    }

    // Display the transactions from the ArrayList
    public void displayTransactions() {
        // You can loop through the ArrayList to display the transactions
        for (NodeHistory history : transactions) {
            System.out.println("Transaction: " + history);
        }
    }

    // Get the ArrayList for display in a table
//    public Object[][] getTransactionsAsData() {
//        Object[][] data = new Object[transactions.size()][7];  // Assuming 7 fields for NodeHistory
//        for (int i = 0; i < transactions.size(); i++) {
//            NodeHistory history = transactions.get(i);
//            data[i] = new Object[] {
//                history.getTitle(),
//                history.getAuthor(),
//                history.getCd(),
//                history.getGenre(),
//                history.getBrrDate(),
//                history.getReturnDate(),
//                history.getPrice()
//            };
//        }
//        return data;
//    }
      public NodeHistory getHead() {
        return transactionHistory.getHead();  // Accessing head of linked list
    }
} 

