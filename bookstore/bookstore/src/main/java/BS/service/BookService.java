package BS.service;

import BS.entity.Book;
import BS.entity.BookRequest;
import BS.formdata.SearchFormData;

import java.util.List;

public interface BookService {
	List<Book> findAllBooks();
	Book findById(int bookId);
	Book findByTitle(String title);
	void saveBook(Book book);
	List<Book> searchBooks(SearchFormData searchFormData);
	void requestBook(int bookId, String username);
	List<BookRequest> findBookRequestsByUser(String username);
	void acceptRequest(String username, int bookId);

}
