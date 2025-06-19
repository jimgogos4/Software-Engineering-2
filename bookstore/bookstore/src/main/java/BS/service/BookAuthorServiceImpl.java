package BS.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import BS.dao.BookAuthorDAO;
import BS.entity.BookAuthor;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

	@Autowired
	private BookAuthorDAO bookAuthorDAO;

	@Override
	@Transactional(readOnly = true)
	public BookAuthor findById(int id) {
		return bookAuthorDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(BookAuthor bookAuthor) {
		bookAuthorDAO.save(bookAuthor);
	}

	@Override
	@Transactional
	public void delete(int authorId) {
		bookAuthorDAO.deleteById(authorId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookAuthor> findByName(String name) {
		return bookAuthorDAO.findByName(name);
	}

	@Override
	@Transactional
	public void update(BookAuthor bookAuthor) {
		bookAuthorDAO.save(bookAuthor);
	}
}
