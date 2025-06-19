package BS.service;

import BS.dao.BookOfferDAO;
import BS.dao.BookRequestDAO;
import BS.dao.UserProfileDAO;
import BS.entity.UserProfile;
import BS.entity.BookOffer;
import BS.entity.BookRequest;
import BS.entity.Book;
import BS.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private BookOfferDAO bookOfferDAO;

    @Autowired
    private BookRequestDAO bookRequestDAO;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserProfile> findUserProfile(String username) {
        return Optional.ofNullable(userProfileDAO.findByUsername(username));
    }

    @Override
    @Transactional
    public void saveUserProfile(UserProfile userProfile) {
        userProfileDAO.save(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookOffer> retrieveBookOffers(String username) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        return bookOfferDAO.findByOwner(userProfile.getUser());
    }

    @Override
    @Transactional
    public void addBookOffer(String username, BookOffer bookOffer) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        bookOffer.setOwner(userProfile.getUser());
        bookOfferDAO.save(bookOffer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String query) {
        // Implement search logic based on query
        // For demonstration, returning an empty list
        return List.of();
    }

    @Override
    @Transactional
    public void requestBook(int bookId, String username) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(new Book(bookId));
        bookRequest.setRequester(userProfile.getUser());
        bookRequestDAO.save(bookRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookRequest> retrieveBookRequests(String username) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        return bookRequestDAO.findByRequester(userProfile.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> retrieveRequestingUsers(int bookId) {
        return bookRequestDAO.findByBook(new Book(bookId))
                .stream()
                .map(BookRequest::getRequester)
                .map(user -> userProfileDAO.findByUsername(user.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBookOffer(String username, int bookId) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        bookOfferDAO.deleteByBookIdAndOwnerUsername(bookId, userProfile.getUser().getUsername());
    }

    @Override
    @Transactional
    public void deleteBookRequest(String username, int bookId) {
        UserProfile userProfile = userProfileDAO.findByUsername(username);
        bookRequestDAO.deleteByBookIdAndRequesterUsername(bookId, userProfile.getUser().getUsername());
    }
}
