package BS.entity;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String name;

    // Assuming you want a OneToMany relationship with Book
    @OneToMany(mappedBy = "category")
    private List<Book> books;

    // Constructor
    public BookCategory() {
    }

    public BookCategory(int categoryId, String name, List<Book> books) {
        this.categoryId = categoryId;
        this.name = name;
        this.books = books;
    }

    // Getters and setters

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
