package BS.service;

import BS.dao.BookDAO; // Assuming you have a BookDAO for Book entities
import BS.dao.BookRequestDAO;
import BS.entity.Book;
import BS.entity.BookRequest;
import BS.formdata.SearchFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO; // Ensure this DAO is defined correctly

	@Autowired
	private BookRequestDAO bookRequestDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllBooks() {
		return bookDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findByTitle(String title) {
		return bookDAO.findByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(int bookId) {
		return bookDAO.findById(bookId).orElse(null);
	}

	@Override
	@Transactional
	public void saveBook(Book book) {
		bookDAO.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> searchBooks(SearchFormData searchFormData) {
		// Implement search logic based on searchFormData
		// For demonstration, returning an empty list
		return List.of();
	}

	@Override
	@Transactional
	public void requestBook(int bookId, String username) {
		// Implement logic to request a book
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookRequest> findBookRequestsByUser(String username) {
		// Implement logic to find book requests by user
		return List.of();
	}

	@Override
	@Transactional
	public void acceptRequest(String username, int bookId) {
		// Implement logic to accept a book request
	}


}
