package DSA;
import java.sql.Date;

public class BookInfo {
    private int BookID;
    private String title;
    private String author;
    private Boolean isAvailable;
    private String ISBN;
    private String genre;
    private int quantity;
    private Date yrPublished;
    private int borrowCount;
    private int shelfNum;
    private String Bookstatus;
    public NodeBook next;

    // Constructor with parameters
    public BookInfo(int BookID, String title, String author, String ISBN, String genre, int quantity, boolean isAvailable, Date yrPublished, int shelfNum, String BookStatus) {
        this.BookID = BookID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.yrPublished = yrPublished;
        this.shelfNum = shelfNum;
        this.Bookstatus = BookStatus;
        this.next = null;
    }

    // Getter and Setter methods
    public int getBookId() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getYrPublished() {
        return yrPublished;
    }

    public void setYrPublished(Date yrPublished) {
        this.yrPublished = yrPublished;
    }

    public int getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(int shelfNum) {
        this.shelfNum = shelfNum;
    }

    public String getStatus() {
        return Bookstatus;
    }

    public void setStatus(String Bookstatus) {
        this.Bookstatus = Bookstatus;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void incrementBorrowCount() {
        this.borrowCount++;
    }

    public NodeBook getNext() {
        return next;
    }

    public void setNext(NodeBook next) {
        this.next = next;
    }
}