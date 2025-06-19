package BS.service;

import java.util.List;

import BS.entity.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import BS.dao.BookCategoryDAO;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryDAO bookCategoryDAO;

    @Override
    @Transactional(readOnly = true)
    public BookCategory findById(int id) {
        return bookCategoryDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCategory> findByName(String name) {
        return bookCategoryDAO.findByName(name);
    }

    @Override
    @Transactional
    public void update(BookCategory bookCategory) {
        bookCategoryDAO.save(bookCategory);
    }

    @Override
    @Transactional
    public void save(BookCategory bookCategory) {
        bookCategoryDAO.save(bookCategory);
    }

    @Override
    @Transactional
    public void delete(int categoryId) {
        bookCategoryDAO.deleteById(categoryId);
    }
}
