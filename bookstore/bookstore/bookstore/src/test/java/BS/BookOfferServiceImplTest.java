package BS.service;

import BS.dao.BookOfferDAO;
import BS.entity.Book;
import BS.entity.BookOffer;
import BS.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookOfferServiceImplTest {

    @Mock
    private BookOfferDAO bookOfferDAO;

    @InjectMocks
    private BookOfferServiceImpl bookOfferService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        String id = "1";
        List<BookOffer> expectedBookOffers = List.of(new BookOffer());
        when(bookOfferDAO.findById(id)).thenReturn(expectedBookOffers);

        List<BookOffer> actualBookOffers = bookOfferService.findById(id);

        assertEquals(expectedBookOffers, actualBookOffers);
        verify(bookOfferDAO, times(1)).findById(id);
    }

    @Test
    public void testFindByOwner() {
        User owner = new User();
        List<BookOffer> expectedBookOffers = List.of(new BookOffer());
        when(bookOfferDAO.findByOwner(owner)).thenReturn(expectedBookOffers);

        List<BookOffer> actualBookOffers = bookOfferService.findByOwner(owner);

        assertEquals(expectedBookOffers, actualBookOffers);
        verify(bookOfferDAO, times(1)).findByOwner(owner);
    }

    @Test
    public void testFindByBook() {
        Book book = new Book();
        List<BookOffer> expectedBookOffers = List.of(new BookOffer());
        when(bookOfferDAO.findByBook(book)).thenReturn(expectedBookOffers);

        List<BookOffer> actualBookOffers = bookOfferService.findByBook(book);

        assertEquals(expectedBookOffers, actualBookOffers);
        verify(bookOfferDAO, times(1)).findByBook(book);
    }

    @Test
    public void testSave() {
        BookOffer bookOffer = new BookOffer();
        bookOfferService.save(bookOffer);

        verify(bookOfferDAO, times(1)).save(bookOffer);
    }

    @Test
    public void testUpdateDescription() {
        String description = "New Description";
        int offerId = 1;
        bookOfferService.updateDescription(description, offerId);

        verify(bookOfferDAO, times(1)).set(description, offerId);
    }

    @Test
    public void testDelete() {
        int offerId = 1;
        bookOfferService.delete(offerId);

        verify(bookOfferDAO, times(1)).delete(offerId);
    }

    @Test
    public void testDeleteByBookIdAndOwnerUsername() {
        int bookId = 1;
        String username = "testUser";
        bookOfferService.deleteByBookIdAndOwnerUsername(bookId, username);

        verify(bookOfferDAO, times(1)).deleteByBookIdAndOwnerUsername(bookId, username);
    }
}
