package BS.dao;

import java.util.List;

import BS.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BS.entity.BookCategory;

@Repository
public interface BookCategoryDAO extends JpaRepository<BookCategory, Integer> {

    @Query("select b from BookCategory b where b.categoryId = :id")
    List<BookCategory> findById(@Param("id") String title);

    @Query("select bc from BookCategory bc where bc.name = :name")
    List<BookCategory> findByName(@Param("name") String name);

    @Modifying
    @Query("update BookCategory bc set bc.name = :name where bc.categoryId = :categoryId")
    void update(@Param("name") String name, @Param("categoryId") int categoryId);

    @Modifying
    @Query("delete from BookCategory bc where bc.categoryId = :categoryId")
    void delete(@Param("categoryId") int categoryId);
}
