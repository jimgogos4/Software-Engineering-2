package BS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import BS.entity.BookAuthor;
import BS.service.BookAuthorService;
import BS.entity.*;
import BS.formdata.SearchFormData;
import BS.service.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BookAuthorService bookAuthorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private BookService bookService;


    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private BookOfferService bookOfferService;

    @Autowired
    private BookRequestService bookRequestService;

    private String currentUsername; // This should be set properly, maybe from session or security context

    @GetMapping({"/mainMenu", "/"})
    public String getUserMainMenu(Model model) {
        User currentUser = userService.findByUsername(currentUsername).orElse(null);
        model.addAttribute("user", currentUser);
        return "user/main-menu";
    }

    @GetMapping("/profile")
    public String retrieveProfile(Model model) {
        UserProfile userProfile = userProfileService.findUserProfile(currentUsername).orElse(null);
        model.addAttribute("userProfile", userProfile);
        return "user/profile";
    }

    @PostMapping("/saveUserProfile")
    public String saveUserProfile(@ModelAttribute("userProfile") UserProfile userProfile, Model model) {
        userProfile.setUsername(currentUsername);
        userProfileService.saveUserProfile(userProfile);
        return "redirect:/users/mainMenu";
    }

    @GetMapping("/showBooks")
    public String listBooksOffer(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "book/book-list";
    }

    @GetMapping("/offerForm")
    public String showOfferForm(Model model) {
        model.addAttribute("bookOffer", new Book());
        return "book/offer-form";
    }

    @PostMapping("/saveOffer")
    public String saveOffer(@ModelAttribute("bookOffer") Book bookOffer, Model model) {
        bookService.saveBook(bookOffer);
        return "redirect:/users/showBooks";
    }

    @GetMapping("/searchForm")
    public String showSearchForm(Model model) {
        model.addAttribute("searchFormData", new SearchFormData());
        return "book/search-form";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("searchFormData") SearchFormData searchFormData, Model model) {
        List<Book> books = bookService.searchBooks(searchFormData);
        model.addAttribute("books", books);
        return "book/book-list";
    }



    // Methods for managing Book Categories

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<BookCategory> categories = bookCategoryService.findByName("");
        model.addAttribute("categories", categories);
        return "book/book-list";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") BookCategory category, Model model) {
        bookCategoryService.save(category);
        return "redirect:/users/categories";
    }

    // Methods for Book Offers and Requests

    @GetMapping("/bookOffers")
    public String listBookOffers(Model model) {
        User currentUser = userService.findByUsername(currentUsername).orElse(null);
        List<BookOffer> bookOffers = bookOfferService.findByOwner(currentUser);
        model.addAttribute("bookOffers", bookOffers);
        return "book/offer-form";
    }
    @PostMapping("/requestBook")
    public String requestBook(@RequestParam("bookId") int bookId, Model model) {
        bookService.requestBook(bookId, currentUsername);
        return "redirect:/users/showBooks";
    }

    @GetMapping("/showUserBookRequests")
    public String showUserBookRequests(Model model) {
        List<BookRequest> bookRequests = bookService.findBookRequestsByUser(currentUsername);
        model.addAttribute("bookRequests", bookRequests);
        return "book/book-requests";
    }

    @GetMapping("/showRequestingUsers")
    public String showRequestingUsersForBookOffer(@RequestParam("bookId") int bookId, Model model) {
        List<UserProfile> requestingUsers = userProfileService.retrieveRequestingUsers(bookId);
        model.addAttribute("requestingUsers", requestingUsers);
        return "book/requesting-users";
    }

    @PostMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("username") String username, @RequestParam("bookId") int bookId, Model model) {
        bookService.acceptRequest(username, bookId);
        return "redirect:/users/showUserBookRequests";
    }

    @PostMapping("/deleteBookOffer")
    public String deleteBookOffer(@RequestParam("username") String username, @RequestParam("bookId") int bookId, Model model) {
        bookOfferService.deleteByBookIdAndOwnerUsername(bookId, username);
        return "redirect:/users/showBooks";
    }

    @PostMapping("/deleteBookRequest")
    public String deleteBookRequest(@RequestParam("username") String username, @RequestParam("bookId") int bookId, Model model) {
        bookRequestService.deleteByBookIdAndRequesterUsername(bookId, username);
        return "redirect:/users/showUserBookRequests";
    }

    @PostMapping("/updateOfferDescription")
    public String updateOfferDescription(@RequestParam("description") String description, @RequestParam("offerId") int offerId, Model model) {
        bookOfferService.updateDescription(description, offerId);
        return "redirect:/users/bookOffers";
    }

    @GetMapping("/bookRequests")
    public String listBookRequests(Model model) {
        User currentUser = userService.findByUsername(currentUsername).orElse(null);
        List<BookRequest> bookRequests = bookRequestService.findByRequester(currentUser);
        model.addAttribute("bookRequests", bookRequests);
        return "book/book-requests";
    }

    @PostMapping("/updateBookRequest")
    public String updateBookRequest(@ModelAttribute("book") Book book, @RequestParam("requestId") int requestId, Model model) {
        bookRequestService.update(book, requestId);
        return "redirect:/users/bookRequests";
    }

    // Methods for managing Users and Authors
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute("author") BookAuthor author) {
        bookAuthorService.update(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") int id) {
        bookAuthorService.delete(id);
        return "redirect:/authors";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") BookAuthor author) {
        bookAuthorService.save(author);
        return "redirect:/authors";
    }


    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/user-list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.saveUser(user);
        return "redirect:/auth/signup";
    }
    /* auth templates kai gia user kai gia auth signin kai signup apla den exei kalo onoma to category :) */

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username, Model model) {
        userService.deleteUser(username);
        return "redirect:/users/list";
    }
}
