/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;
 import java.util.Date;
public class NodeBook {
   private String title ;
     private String author ;
      private Boolean isAvailable ;
       private int ISBN ;
        private String genre ;
        private String index;
        private int BookID;
        private int quantity;
        private Date yrPublished;
        int data;
         private int borrowCount; 
        private int shelfNum;
        public  NodeBook next;
        private String status;
          
     NodeBook(int data){
     this.data = data;
         next = null;
     }
            
    NodeBook (String title, String author, int ISBN, String genre, boolean isAvailable,int BookId,int quantity,Date yrOfPublished,int shelfNum){
     this.ISBN = ISBN;
     this.author= author;
     this.title = title;
     this.genre = genre;
      this.isAvailable = isAvailable;
      this.quantity = quantity; 
      this.title = title;
      this.BookID =BookId;
      this.yrPublished = yrOfPublished;
      this.shelfNum = shelfNum;
        this.next = null;
        
    }
    public String getStatus() {
        return status;
    }
    public int getBorrowCount() {
        return borrowCount;
    }

    public void incrementBorrowCount() {
        this.borrowCount++;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getYr(){
        return yrPublished;
    }
    public int shelft(){
        return shelfNum;
    }
    public Date setYear(Date yr){
      return  this.yrPublished = yr;
    }
    public int setSheflNum(int shelf){
        return this.shelfNum = shelf;
    }

      public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public NodeBook getNext() {
        return next;
    }

    // Setter methods for modifying book properties
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setNext(NodeBook next) {
        this.next = next;
    }
    public int getBookId(){
        return BookID;
    }
    public void setBookID(int id){
        this.BookID = id;
    }
    public int getQuan(){
        return quantity;
    }
    public void getQuantity(int quanity){
        this.quantity = quanity;
    }

   

}