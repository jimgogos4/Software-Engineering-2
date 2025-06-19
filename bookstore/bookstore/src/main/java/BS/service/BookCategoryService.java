package BS.service;

import java.util.List;
import BS.entity.BookCategory;

public interface BookCategoryService {
	List<BookCategory> findByName(String name);
	BookCategory findById(int id);
	void delete(int categoryId);
	void save(BookCategory bookCategory);
	void update(BookCategory bookCategory);
}
