/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDUSER;
import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LLhistory {
  private LinkedListAccounts linkedListAccounts; 
      private NodeHistory head;  // Head of the linked list

    // Constructor
    public LLhistory() {
        this.head = null;  // Initially, the list is empty
    }
   

    // Method to add a new NodeHistory at the end of the list
    public void addNode(NodeHistory newNode) {
        
        if (head == null) {
            head = newNode; 
              System.out.println("Added first node to history: " + newNode.getTitle());// If the list is empty, the new node becomes the head
        } else {
            NodeHistory current = head;
            // Traverse to the last node
            while (current.getNext() != null) {
                current = current.getNext();
               
            }
            // Add the new node at the end of the list
            current.setNext(newNode);
             System.out.println("Added node to history: " + newNode.getTitle());
        }
    
    }
//    public int getBorrowedBookCount(String userId) {
//    int count = 0;
//
//    for (BorrowedBook record : borrowedBooksList) { // Replace BorrowedBook with your class name for records
//        if (record.getUserId().equals(userId)) {
//            count++;
//        }
//    }



  

   
    
   
      public NodeHistory getHead() {
        return head;
    }

    // Method to retrieve a NodeHistory by index
    public NodeHistory getNode(int index) {
        NodeHistory current = head;
        int count = 0;
        // Traverse the list to find the node at the given index
        while (current != null) {
            if (count == index) {
                return current;  // Node found at index
            }
            count++;
            current = current.getNext();
        }
        return null;  // Return null if the index is out of bounds
    }

    // Method to remove a NodeHistory by index
    public boolean removeNode(int index) {
        if (head == null) {
            return false;  // List is empty
        }

        // Special case: if we need to remove the head node
        if (index == 0) {
            head = head.getNext();
            return true;
        }

        NodeHistory current = head;
        int count = 0;
        // Traverse the list to find the node to remove
        while (current != null && count < index - 1) {
            current = current.getNext();
            count++;
        }

        // If current is null or the next node is null, index is out of bounds
        if (current == null || current.getNext() == null) {
            return false;
        }

        // Remove the node at the given index
        current.setNext(current.getNext().getNext());
        return true;
    }

    // Method to display all nodes in the list (for debugging or printing)
    public void displayList() {
        NodeHistory current = head;
        while (current != null) {
            System.out.println(current);  // Print the node data (NodeHistory's toString method)
            current = current.getNext();
        }
    }

    // Method to get the size of the linked list
    public int size() {
        int size = 0;
        NodeHistory current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
     public Object[][] getHistoryData(User user) {
  
    NodeHistory current = this.head;  // Assuming `head` is the start of the linked list
    List<Object[]> historyDataList = new ArrayList<>();

  
     // Traverse the linked list and add each history entry to the list
    while (current != null) {
        Object[] row = {
            current.getTitle(),
            current.getAuthor(),
            current.getCd(),
            current.getGenre(),
            current.getBrrDate(),
            current.getReturnDate(),
            current.getPrice()
        };
        historyDataList.add(row);
        current = current.getNext();  // Move to the next node
    }

    // Convert List<Object[]> to a 2D array (Object[][]) for the table
    return historyDataList.toArray(new Object[0][0]);
    
    }

   


       public void displayHistory() {
    NodeHistory current = head;  // Assuming 'head' is the first node in the list
    while (current != null) {
        System.out.println("History Record: " + current);
        current = current.getNext();  // Traverse the linked list
    }
}
}
