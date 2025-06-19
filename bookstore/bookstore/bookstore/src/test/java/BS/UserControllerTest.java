package BS.controller;

import BS.entity.*;
import BS.formdata.SearchFormData;
import BS.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserProfileService userProfileService;

    @Mock
    private BookService bookService;

    @Mock
    private BookCategoryService bookCategoryService;

    @Mock
    private BookOfferService bookOfferService;

    @Mock
    private BookRequestService bookRequestService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Set the currentUsername field using reflection
        Field field = UserController.class.getDeclaredField("currentUsername");
        field.setAccessible(true);
        field.set(userController, "testUser");
    }

    @Test
    public void testRetrieveProfile() throws Exception {
        UserProfile userProfile = new UserProfile("testUser", "Test User", "123 Main St", 25, "123-456-7890");
        when(userProfileService.findUserProfile(anyString())).thenReturn(Optional.of(userProfile));

        mockMvc.perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userProfile", userProfile))
                .andExpect(view().name("user/profile"));
    }

    @Test
    public void testSaveUserProfile() throws Exception {
        UserProfile userProfile = new UserProfile("testUser", "Test User", "123 Main St", 25, "123-456-7890");

        mockMvc.perform(post("/users/saveUserProfile")
                        .flashAttr("userProfile", userProfile))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/mainMenu"));

        verify(userProfileService, times(1)).saveUserProfile(userProfile);
    }

    @Test
    public void testListBooksOffer() throws Exception {
        List<Book> books = new ArrayList<>();
        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/users/showBooks"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("book/book-list"));
    }

    @Test
    public void testSaveOffer() throws Exception {
        Book bookOffer = new Book();
        mockMvc.perform(post("/users/saveOffer")
                        .flashAttr("bookOffer", bookOffer))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/showBooks"));

        verify(bookService, times(1)).saveBook(bookOffer);
    }

    @Test
    public void testSearchBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        when(bookService.searchBooks(any(SearchFormData.class))).thenReturn(books);

        mockMvc.perform(post("/users/search")
                        .flashAttr("searchFormData", new SearchFormData()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("book/book-list"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        mockMvc.perform(post("/users/register")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/signup"));

        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testListBookOffers() throws Exception {
        User currentUser = new User();
        List<BookOffer> bookOffers = new ArrayList<>();
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(currentUser));
        when(bookOfferService.findByOwner(currentUser)).thenReturn(bookOffers);

        mockMvc.perform(get("/users/bookOffers"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookOffers", bookOffers))
                .andExpect(view().name("book/offer-form"));
    }
}
