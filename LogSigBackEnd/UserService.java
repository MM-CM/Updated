/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogSigBackEnd;
import BACKENDUSER.LLhistory;
import BACKENDUSER.NodeHistory;
import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

 public class UserService implements Users {
  private static UserService instance; // Singleton instance
    private LinkedListAccounts linkedListAccounts; // LinkedList for managing accounts
    private List<NodeAccounts> accountList;
    private LLhistory nya =  new LLhistory();


    // Private constructor for Singleton pattern
    public UserService() {
        linkedListAccounts = new LinkedListAccounts();
        accountList = new ArrayList<>();
      
    }

    // Singleton instance getter
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    

    // Adds a new user to both LinkedList and ArrayList
    public int addUser(String username, String password, String contactNumber, String email, String role) {
        if (userExists(username)) {
            System.out.println("User already exists!");
            return 0; // Indicate failure due to existing user
        }

        int userID = generateRandomID();
           System.out.println("Generated User ID: " + userID);
           
        // Add to LinkedList
        
        linkedListAccounts.createAccount(userID,username, password,  contactNumber, role);

        // Add to ArrayList
      
        NodeAccounts newNode = new NodeAccounts(userID,username, password,  contactNumber, role);
        accountList.add(newNode);
       

         syncUsersWithLinkedList(); 
        System.out.println("User successfully added with ID: " + userID);
        
        LinkedListAccounts accounts = getLinkedListAccounts();
NodeAccounts current = accounts.getHead();  // Assuming getHead() gives the head of the linked list
while (current != null) {
    System.out.println("Account ID: " + current.getMemberID() + ", Username: " + current.getUserName());
    current = current.next;  // Traverse the linked list
}
        return userID;
    }
       public void updateUserTable(JTable table) {
       NodeAccounts currentNode = linkedListAccounts.getHead();

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Clear existing data

    if (currentNode == null) {
        System.out.println("No users found in the linked list.");
        return;
    }

    while (currentNode != null) {
        model.addRow(new Object[]{
            currentNode.getUserName(),
            currentNode.getContactNum(),
            currentNode.getEMail(),
            currentNode.getMemberID()
        });
        currentNode = currentNode.getNext();
    }

    System.out.println("User table updated successfully.");
}

    // Validates login credentials
    public boolean validateLogin(String username, String enteredPassword) {
      NodeAccounts user = linkedListAccounts.linearSearch(username);
    if (user == null) {
          JOptionPane.showMessageDialog(null, "User does not exist", "Message", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("User not found: " + username);
        return false;
    }

    if (user.isBlocked()) {
          JOptionPane.showMessageDialog(null, "User is blocked", "Message", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Account is blocked: " + username);
        return false;
    }

    if (user.getPassword().equals(enteredPassword)) {
      
        System.out.println("Login successful for user: " + username);
        user.resetFailedAttempts();
        return true;
    } else {
        System.out.println("Invalid password for user: " + username);
        user.incrementFailedAttempts();

        if (user.getFailedAttempts() >= 3) {
           JOptionPane.showMessageDialog(null, "User blocked due to multiple failed attempts" +username, "Message", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("User blocked due to multiple failed attempts: " + username);
            user.block();
        }
        return false;
    }

    }
    public boolean validatePassword(String username, String enteredPassword) {
    NodeAccounts user = linkedListAccounts.linearSearch(username);
    if (user != null) {
        return user.getPassword().equals(enteredPassword);
    }
    return false;
}

    // Checks if a user exists by username
    public boolean userExists(String username) {
       System.out.println("Checking if user exists: " + username);

    NodeAccounts user = linkedListAccounts.linearSearch(username);
    if (user != null) {
        System.out.println("User found in linked list: " + username);
        return true;
    }

    for (NodeAccounts account : accountList) {
        if (account.getUserName().equals(username)) {
            System.out.println("User found in ArrayList: " + username);
            return true;
        }
    }

    System.out.println("User not found: " + username);
    return false;
    }

    // Generates a unique ID
   public int generateRandomID() {
    int id = (int) (Math.random() * 10000); // Expanding range to a larger value to reduce collision
    while (!isUniqueID(id)) {
        id = (int) (Math.random() * 10000);
    }
       System.out.println("ITo id mo "+ id);
    return id;
}

    // Ensures the generated ID is unique
    private boolean isUniqueID(int id) {
        NodeAccounts current = linkedListAccounts.getHead();
        while (current != null) {
            if (current.getMemberID() == id) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    
    public void syncUsersWithLinkedList() {
        accountList.clear();
        NodeAccounts current = linkedListAccounts.getHead();
        while (current != null) {
            accountList.add(current);
            current = current.getNext();
        }
        System.out.println("Data synchronized between LinkedList and ArrayList.");
    }

    // Returns the ArrayList of users
    public List<NodeAccounts> getUsers() {
        return new ArrayList<>(accountList);
    }
    public LinkedListAccounts getLinkedListAccounts() {
    return linkedListAccounts;
}
   public User getUserByID(int userID) {
        for (NodeAccounts account : accountList) {  // Search through accountList
            if (account.getMemberID() == userID) {
                // You might need to map NodeAccounts to User or use a User instance in the future
                return new User(account.getUserName(), account.getPassword(), account.getContactNum(), account.getMemberID());  
            }
        }
        return null;  // Return null if the user doesn't exist
    }
  public void borrowBook(String userName, NodeHistory book) {
        User user = getUser (userName);
        if (user != null) {
            user.getHistory().addNode(book); // Add book to user's history
        } else {
            System.out.println("User  not found.");
        }
  }
 
    public User getUser(String username) {
        NodeAccounts node = linkedListAccounts.linearSearch(username);
        if (node != null) {
            // Create a User object from the NodeAccounts
            return new User(node.getUserName(), node.getPassword(), node.getContactNum(), node.getMemberID());
        }
        return null;  // Return null if the user is not found
    }
}
