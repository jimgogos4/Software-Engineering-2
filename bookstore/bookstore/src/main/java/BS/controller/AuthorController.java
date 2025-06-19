package BS.controller;

import BS.entity.BookAuthor;
import BS.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private BookAuthorService bookAuthorService;

    @GetMapping
    public String listAuthors(Model model) {
        List<BookAuthor> authors = bookAuthorService.findByName("");
        model.addAttribute("authors", authors);
        return "author/author-list";
    }

    @GetMapping("/form")
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new BookAuthor());
        return "author/author-form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") BookAuthor author) {
        bookAuthorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        BookAuthor author = bookAuthorService.findById(id);
        model.addAttribute("author", author);
        return "author/author-form";
    }

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
}
