package BS.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BS.entity.Book;
import BS.entity.BookAuthor;
import BS.entity.BookCategory;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.bookId = :id")
    List<Book> findById(@Param("id") String title);

    @Query("select b from Book b where b.title = :title")
    Book findByTitle(@Param("title") String title);

    @Modifying
    @Query("update Book b set b.title = :title, b.author = :author, b.category = :category, b.summary = :summary where b.bookId = :bookId")
    void update(@Param("title") String title, @Param("author") BookAuthor author, @Param("category") BookCategory category, @Param("summary") String summary, @Param("bookId") int bookId);

    @Modifying
    @Query("delete from Book b where b.bookId = :bookId")
    void delete(@Param("bookId") int bookId);
}
