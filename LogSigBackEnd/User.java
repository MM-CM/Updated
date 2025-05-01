/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogSigBackEnd;
import BACKENDUSER.LLhistory;

public class User {
    private String username;
    private String password;
    private String contactNumber;
    private int userID;
  
    private LLhistory history;
    
    public User(){
        
    }

    public User(String username, String password, String contactNumber, int userID) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
   
        this.userID = userID;
          this.history = new LLhistory();
    }

    // Getters and setters can be added as needed
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getUserID() {
        return userID;
    }
     public LLhistory getHistory() {
    if (history == null) {
        System.out.println("History is null for user: " + this.userID);
    } else {
        System.out.println("History retrieved for user: " + this.userID);
    }
    return history;
    }
         public void setHistory(LLhistory history) {
        this.history = history;
    }
  

}
