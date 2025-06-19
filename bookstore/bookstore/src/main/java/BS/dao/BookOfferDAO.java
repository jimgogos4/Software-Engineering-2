package BS.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import BS.entity.BookOffer;
import BS.entity.User;
import BS.entity.Book;

@Repository
public interface BookOfferDAO extends JpaRepository<BookOffer, Integer> {

	@Query("select b from BookOffer b where b.offerId = :id")
	List<BookOffer> findById(@Param("id") String title);

	@Query("select bo from BookOffer bo where bo.owner = :owner")
	public List<BookOffer> findByOwner(@Param("owner") User owner);

	@Query("select bo from BookOffer bo where bo.book = :book")
	public List<BookOffer> findByBook(@Param("book") Book book);

	@Modifying
	@Query("update BookOffer bo set bo.description = :description where bo.offerId = :offerId")
	public void set(@Param("description") String description, @Param("offerId") int offerId);

	@Modifying
	@Query("delete from BookOffer bo where bo.offerId = :offerId")
	public void delete(@Param("offerId") int offerId);

	@Modifying
	@Query("delete from BookOffer bo where bo.book.bookId = :bookId and bo.owner.username = :username")
	void deleteByBookIdAndOwnerUsername(@Param("bookId") int bookId, @Param("username") String username);

}
