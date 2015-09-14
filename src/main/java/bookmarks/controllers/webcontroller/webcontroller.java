package bookmarks.controllers.webcontroller;

import bookmarks.data.model.Account;
import bookmarks.data.model.Bookmark;
import bookmarks.data.repository.AccountRepository;
import bookmarks.data.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;

import java.util.Collection;


/**
 * Created by vkhudiakov on 13/09/15.
 */

@Controller
@RequestMapping("/ManageBookmarks")
public class webcontroller {

    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;


    @RequestMapping("/view")
    public String showBookmarks(Principal principal, Model model) {
        String userID = principal.getName();
        Collection<Bookmark> bookmarks = this.bookmarkRepository.findByAccountUsername(userID);
        Account account = accountRepository.findByUsername(userID).get();
        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("account", account);
        return "viewbookmarks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookmarks(Principal principal, Model model) {
        String userID = principal.getName();
        model.addAttribute("userID", userID);
        model.addAttribute("bookmarkForm", new Bookmark());
        return "addbookmark";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveaddBookmarks(@ModelAttribute("bookmarkForm") Bookmark bookmark, Principal principal, BindingResult result, Model model) {
        String userID = principal.getName();
        Account account = accountRepository.findByUsername(userID).get();
        Bookmark newBookmark = new Bookmark(account,bookmark.uri,bookmark.description);
        bookmarkRepository.save(newBookmark);
        return "redirect:/ManageBookmarks/view";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteBookmarks(@RequestParam("bid") Long bid, Principal principal, Model model) {
        bookmarkRepository.delete(bookmarkRepository.findOne(bid));
        return "result";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editBookmarks(@RequestParam("bid") Long bid, Principal principal, Model model) {
        Bookmark bookmark = bookmarkRepository.findOne(bid);
        model.addAttribute("bid", bid);
        model.addAttribute("bookmarkForm", bookmark);
        return "editbookmark";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBookmarks(@RequestParam("bid") Long bid,@ModelAttribute("bookmarkForm") Bookmark bookmark, Principal principal, Model model) {
        String userID = principal.getName();
        Account account = accountRepository.findByUsername(userID).get();
        Bookmark updated_bookmark = bookmarkRepository.findOne(bid);
        updated_bookmark.setUri(bookmark.uri);
        updated_bookmark.setDescription(bookmark.description);
        bookmarkRepository.save(updated_bookmark);
        return "redirect:/ManageBookmarks/view";

    }

    @Autowired
    public webcontroller(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }
}
