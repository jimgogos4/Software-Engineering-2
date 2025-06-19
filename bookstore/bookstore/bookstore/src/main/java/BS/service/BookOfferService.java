package BS.service;

import java.util.List;
import BS.entity.BookOffer;
import BS.entity.User;
import BS.entity.Book;

public interface BookOfferService {

    List<BookOffer> findById(String id);

    List<BookOffer> findByOwner(User owner);

    List<BookOffer> findByBook(Book book);

    void save(BookOffer bookOffer);

    void updateDescription(String description, int offerId);

    void delete(int offerId);

    void deleteByBookIdAndOwnerUsername(int bookId, String username);
}
