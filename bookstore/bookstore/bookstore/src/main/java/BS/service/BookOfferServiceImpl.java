package BS.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import BS.dao.BookOfferDAO;
import BS.entity.BookOffer;
import BS.entity.User;
import BS.entity.Book;

@Service
public class BookOfferServiceImpl implements BookOfferService {

    @Autowired
    private BookOfferDAO bookOfferDAO;

    @Override
    @Transactional(readOnly = true)
    public List<BookOffer> findById(String id) {
        return bookOfferDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookOffer> findByOwner(User owner) {
        return bookOfferDAO.findByOwner(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookOffer> findByBook(Book book) {
        return bookOfferDAO.findByBook(book);
    }

    @Override
    @Transactional
    public void save(BookOffer bookOffer) {
        bookOfferDAO.save(bookOffer);
    }

    @Override
    @Transactional
    public void updateDescription(String description, int offerId) {
        bookOfferDAO.set(description, offerId);
    }

    @Override
    @Transactional
    public void delete(int offerId) {
        bookOfferDAO.delete(offerId);
    }

    @Override
    @Transactional
    public void deleteByBookIdAndOwnerUsername(int bookId, String username) {
        bookOfferDAO.deleteByBookIdAndOwnerUsername(bookId, username);
    }
}
