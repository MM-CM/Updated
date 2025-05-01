/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDUSER;
import java.util.Date;

public class NodeHistory {
    private String title;
    private String author;
    private int cd;
    private String genre;
    private  Date brrDate;
     private  Date returnDate;
     private double price;
     private String user;
     private int maxBorrow;
     private int days;
       NodeHistory next;
      
      
        public NodeHistory() {}
        public NodeHistory(String title, String author, int cd, String genre, Date brrDate, Date returnDate, double price) {
        this.title = title;
        this.author = author;
        this.cd = cd;
        this.genre = genre;
        this.brrDate = brrDate;
        this.returnDate = returnDate;
        this.price = price;
        this.next = null;  // Default value for next (null)
    }

    // Getters and setters for each field
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        if(user.equals("Teacher")){
            maxBorrow= 10;
            days = 10;
        }
        if(user.equals("Student")){
            maxBorrow=5;
            days=3;
        }
        else{
            maxBorrow = 0;
            days=0;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getBrrDate() {
        return brrDate;
    }

    public void setBrrDate(Date brrDate) {
        this.brrDate = brrDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and setter for the 'next' pointer
    public NodeHistory getNext() {
        return next;
    }

    public void setNext(NodeHistory next) {
        this.next = next;
    }

    // Utility method to calculate the number of days between borrow and return date
    public long getBorrowDurationInDays() {
        if (brrDate != null && returnDate != null) {
            return (returnDate.getTime() - brrDate.getTime()) / (1000 * 60 * 60 * 24); // Returns days
        }
        return 0;
    }
        public double calculateOverdueFine(Date currentDate) {
        if (returnDate != null && currentDate.after(returnDate)) {
            long diffInMillis = currentDate.getTime() - returnDate.getTime();
            long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);  // Convert milliseconds to days
            if (diffInDays > 0) {
                return diffInDays * 1;  // Example fine: $1 per day
            }
        }
        return 0;  // No fine if on time
    }

     
            
    
    
}

