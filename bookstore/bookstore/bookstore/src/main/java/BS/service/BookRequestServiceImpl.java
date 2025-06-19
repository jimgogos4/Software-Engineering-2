package BS.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import BS.dao.BookRequestDAO;
import BS.entity.BookRequest;
import BS.entity.User;
import BS.entity.Book;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
    private BookRequestDAO bookRequestDAO;



    @Override
    @Transactional(readOnly = true)
    public List<BookRequest> findById(String id) {
        return bookRequestDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(BookRequest bookRequest) {
        bookRequestDAO.save(bookRequest);
    }

    @Override
    @Transactional
    public void delete(int requestId) {
        bookRequestDAO.delete(requestId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookRequest> findByTitle(String title) {
        return bookRequestDAO.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookRequest> findByRequester(User requester) {
        return bookRequestDAO.findByRequester(requester);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookRequest> findByBook(Book book) {
        return bookRequestDAO.findByBook(book);
    }

    @Override
    @Transactional
    public void update(Book book, int requestId) {
        bookRequestDAO.updateBook(book, requestId);
    }

    @Override
    @Transactional
    public void deleteByBookIdAndRequesterUsername(int bookId, String username) {
        bookRequestDAO.deleteByBookIdAndRequesterUsername(bookId, username);
    }
}
