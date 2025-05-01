/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSA;

/**
 *
 * @author pc
 */
public class nodeeReport {
     String date, mostBorrowedBook;
    int totalBorrowedBooks;
    double totalFine;
    nodeeReport next;

    public nodeeReport(String date, String mostBorrowedBook,int totalBorrowedBooks, double totalFine) {
        this.date = date;
        this.mostBorrowedBook = mostBorrowedBook;
        this.totalBorrowedBooks = totalBorrowedBooks;
        this.totalFine = totalFine;
        this.next = null;
    }
}
