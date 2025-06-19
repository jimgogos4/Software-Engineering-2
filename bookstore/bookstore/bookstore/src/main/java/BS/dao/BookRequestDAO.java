package BS.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BS.entity.BookRequest;
import BS.entity.User;
import BS.entity.Book;

@Repository
public interface BookRequestDAO extends JpaRepository<BookRequest, Integer> {

	@Query("select b from BookRequest b where b.requestId = :id")
	List<BookRequest> findById(@Param("ID") String title);

	@Query("select br from BookRequest br where br.requester = :requester")
	public List<BookRequest> findByRequester(@Param("requester") User requester);

	@Query("select br from BookRequest br where br.book = :book")
	public List<BookRequest> findByBook(@Param("book") Book book);

	@Query("select br from BookRequest br where br.book.title = :title")
	List<BookRequest> findByTitle(@Param("title") String title);

	@Modifying
	@Query("update BookRequest br set br.book = :book where br.requestId = :requestId")
	public void updateBook(@Param("book") Book book, @Param("requestId") int requestId);

	@Modifying
	@Query("delete from BookRequest br where br.requestId = :requestId")
	public void delete(@Param("requestId") int requestId);

	@Modifying
	@Query("delete from BookRequest br where br.book.bookId = :bookId and br.requester.username = :username")
	void deleteByBookIdAndRequesterUsername(@Param("bookId") int bookId, @Param("username") String username);
}
