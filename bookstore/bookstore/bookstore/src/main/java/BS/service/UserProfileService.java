package BS.service;

import BS.entity.UserProfile;
import BS.entity.BookOffer;
import BS.entity.BookRequest;
import BS.entity.Book;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
   Optional<UserProfile> findUserProfile(String username);
   void saveUserProfile(UserProfile userProfile);
   List<BookOffer> retrieveBookOffers(String username);
   void addBookOffer(String username, BookOffer bookOffer);
   List<Book> searchBooks(String query);
   void requestBook(int bookId, String username);
   List<BookRequest> retrieveBookRequests(String username);
   List<UserProfile> retrieveRequestingUsers(int bookId);
   void deleteBookOffer(String username, int bookId);
   void deleteBookRequest(String username, int bookId);
}
