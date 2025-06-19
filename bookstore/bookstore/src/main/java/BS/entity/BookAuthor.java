package BS.entity;

import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "book_author")
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "name")
    private String name;

    // Assuming you want a OneToMany relationship with Book
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public BookAuthor() {
    }

    public BookAuthor(int authorId, String name, List<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.books = books;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
