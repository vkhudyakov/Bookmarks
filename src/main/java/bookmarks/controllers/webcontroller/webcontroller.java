package bookmarks.controllers.webcontroller;

import bookmarks.data.model.Account;
import bookmarks.data.model.Bookmark;
import bookmarks.data.repository.AccountRepository;
import bookmarks.data.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.Collection;


/**
 * Created by vkhudiakov on 13/09/15.
 */

@Controller
@RequestMapping("/ManageBookmarks/{userID}")
public class webcontroller {

    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;


    @RequestMapping("/view")
    public String showBookmarks(@PathVariable String userID, Model model) {
        Collection<Bookmark> bookmarks = this.bookmarkRepository.findByAccountUsername(userID);
        Account account = accountRepository.findByUsername(userID).get();
        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("account", account);
        return "viewbookmarks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookmarks(@PathVariable String userID, Model model) {
        model.addAttribute("userID", userID);
        model.addAttribute("bookmarkForm", new Bookmark());
        return "addbookmark";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveaddBookmarks(@ModelAttribute("bookmarkForm") Bookmark bookmark, @PathVariable String userID, BindingResult result, Model model) {
        Account account = accountRepository.findByUsername(userID).get();
        Bookmark newBookmark = new Bookmark(account,bookmark.uri,bookmark.description);
        bookmarkRepository.save(newBookmark);
        return "result";
    }

    @Autowired
    public webcontroller(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }
}
