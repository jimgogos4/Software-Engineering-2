package BS.entity;

import java.util.Set;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private BookAuthor author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BookCategory category;

    @ManyToMany(mappedBy = "requestedBooks", fetch = FetchType.LAZY)
    private List<UserProfile> requestingUsers = new ArrayList<>();

    @Column(name = "summary")
    private String summary;

    public Book() {
    }

    public Book(String title, BookAuthor author, BookCategory category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Book(int bookId) {
        this.bookId = bookId;
    }

    // Getters and setters

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookAuthor getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthor author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public List<UserProfile> getRequestingUsers() {
        return requestingUsers;
    }

    public void setRequestingUsers(List<UserProfile> requestingUsers) {
        this.requestingUsers = requestingUsers;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
