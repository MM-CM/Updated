/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;
 import javax.swing.JCheckBox;

public class LL {
  private NodeFee head; // Head of the linked list

    // Add a new node to the linked list
    public void add(JCheckBox checkbox, int fee) {
        NodeFee newNode = new NodeFee(checkbox, fee);
        if (head == null) {
            head = newNode; // If the list is empty, set head to the new node
        } else {
            NodeFee current = head;
            while (current.next != null) { // Traverse to the end of the list
                current = current.next;
            }
            current.next = newNode; // Add the new node at the end
        }
    }

    // Calculate the total fee based on selected checkboxes
    public double calculateTotalFee() {
       int totalFee = 0;
    NodeFee current = head;
    while (current != null) {
        if (current.checkbox.isSelected()) {
            totalFee += current.fee;
            System.out.println("Selected Fee: " + current.fee); // Debug log
        }
        current = current.next;
    }
    System.out.println("Total Fee: " + totalFee);
    return totalFee;
}
    
    
    public double calculateTotalFee(double totalFee) {
   
    NodeFee current = head;
    while (current != null) {
        if (current.checkbox.isSelected()) {
            totalFee += current.fee;
            System.out.println("Selected Fee: " + current.fee); // Debug log
        }
        current = current.next;
    }
    System.out.println("Total Fee: " + totalFee);
    return totalFee;
}
}