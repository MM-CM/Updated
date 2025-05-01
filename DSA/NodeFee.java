/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;
import javax.swing.JCheckBox; 

public class NodeFee {
      JCheckBox checkbox; // The checkbox
    int fee;            // Fee associated with the checkbox
    NodeFee next;       // Reference to the next node

    public NodeFee(JCheckBox checkbox, int fee) {
        this.checkbox = checkbox;
        this.fee = fee;
        this.next = null;
    }
}
