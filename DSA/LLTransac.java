/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

/**
 *
 * @author pc
 */
public class LLTransac {
   private NodeTransac head;

    // Constructor
    public LLTransac() {
        this.head = null;
    }

    // Add a new transaction at the end of the list
    public void add(NodeTransac newTransac) {
        if (head == null) {
            head = newTransac;
        } else {
            NodeTransac current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.next = newTransac;
        }
    }

    // Remove a transaction by transaction ID
    public boolean remove(String transacId) {
        if (head == null) {
            return false; // List is empty
        }

        // If the head is the transaction to be removed
        if (head.getID().equals(transacId)) {
            head = head.getNext();
            return true;
        }

        // Traverse the list to find the transaction
        NodeTransac current = head;
        while (current.getNext() != null) {
            if (current.getNext().getID().equals(transacId)) {
                current.next = current.getNext().getNext();
                return true;
            }
            current = current.getNext();
        }

        return false; // Transaction not found
    }

    // Find a transaction by transaction ID
    public NodeTransac find(String transacId) {
        NodeTransac current = head;
        while (current != null) {
            if (current.getID().equals(transacId)) {
                return current;
            }
            current = current.getNext();
        }
        return null; // Not found
    }

    // Display all transactions
    public void display() {
        NodeTransac current = head;
        if (current == null) {
            System.out.println("No transactions in the list.");
            return;
        }

        System.out.println("Transactions:");
        while (current != null) {
            System.out.println("Transaction ID: " + current.getID());
            System.out.println("User ID: " + current.getUserId());
            System.out.println("ISBN: " + current.code());
            System.out.println("Borrow Date: " + current.borrowDate());
            System.out.println("Due Date: " + current.getDueDate());
            System.out.println("Price: " + current.getPrice());
            System.out.println("Status: " + current.getstatus());
            System.out.println("-------------------------");
            current = current.getNext();
        }
    }
}
