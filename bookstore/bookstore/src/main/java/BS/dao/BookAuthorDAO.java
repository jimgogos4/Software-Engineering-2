package BS.dao;

import java.util.List;

import BS.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BS.entity.BookAuthor;

@Repository
public interface BookAuthorDAO extends JpaRepository<BookAuthor, Integer> {

    @Query("select b from BookAuthor b where b.authorId = :id")
    List<BookAuthor> findById(@Param("id") String title);

    @Query("select ba from BookAuthor ba where ba.name = :name")
    List<BookAuthor> findByName(@Param("name") String name);

    @Modifying
    @Query("update BookAuthor ba set ba.name = :name where ba.authorId = :authorId")
    void update(@Param("name") String name, @Param("authorId") int authorId);

    @Modifying
    @Query("delete from BookAuthor ba where ba.authorId = :authorId")
    void delete(@Param("authorId") int authorId);
}
