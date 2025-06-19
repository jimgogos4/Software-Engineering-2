package BS.service;

import java.util.List;
import BS.entity.BookRequest;
import BS.entity.User;
import BS.entity.Book;

public interface BookRequestService {

    List<BookRequest> findById(String id);

    List<BookRequest> findByRequester(User requester);

    List<BookRequest> findByBook(Book book);

    List<BookRequest> findByTitle(String title);

    void save(BookRequest bookRequest);

    void update(Book book, int requestId);

    void delete(int requestId);

    void deleteByBookIdAndRequesterUsername(int bookId, String username);
}
