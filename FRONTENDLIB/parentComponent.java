/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import FRONTENDADMIN.*;
import FRONTENDUSER.*;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public abstract class parentComponent extends JFrame {
    JFrame  frame;
    JPanel panel;
    JPanel panel2;
    JPanel panel3;
    JLabel label;

    JTextField textFld;
    JPasswordField pass;
     
    Image icon = Toolkit.getDefaultToolkit().getImage("images\\book.png");
       ImageIcon icons;
     
}
