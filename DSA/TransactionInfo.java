/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class TransactionInfo {
    private String transactionId;
    private int isbn;
    private String userId;
    private Date borrowDate;
    private Date dueDate;
    private double fine;
    private String genre;

    public TransactionInfo(String transactionId, int isbn, String userId, Date borrowDate, Date dueDate, double fine, String genre) {
        this.transactionId = transactionId;
        this.isbn = isbn;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.fine = fine;
        this.genre = genre;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public int getIsbn() { return isbn; }
    public String getUserId() { return userId; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }
    public double getFine() { return fine; }
    public String getGenre() { return genre; }

    // Setters
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setIsbn(int isbn) { this.isbn = isbn; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void setFine(double fine) { this.fine = fine; }
    public void setGenre(String genre) { this.genre = genre; }
}

