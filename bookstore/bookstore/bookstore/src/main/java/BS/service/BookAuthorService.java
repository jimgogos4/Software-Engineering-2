package BS.service;

import java.util.List;
import BS.entity.BookAuthor;

public interface BookAuthorService {
	List<BookAuthor> findByName(String name);
	BookAuthor findById(int id);
	void delete(int authorId);
	void save(BookAuthor bookAuthor);
	void update(BookAuthor bookAuthor);
}
