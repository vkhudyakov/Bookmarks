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
        return "redirect:/ManageBookmarks/"+userID+"/view";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteBookmarks(@RequestParam("bid") Long bid, @PathVariable String userID, Model model) {
        bookmarkRepository.delete(bookmarkRepository.findOne(bid));
        return "result";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editBookmarks(@RequestParam("bid") Long bid, @PathVariable String userID, Model model) {
        Bookmark bookmark = bookmarkRepository.findOne(bid);
        model.addAttribute("bid", bid);
        model.addAttribute("bookmarkForm", bookmark);
        return "editbookmark";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBookmarks(@RequestParam("bid") Long bid,@ModelAttribute("bookmarkForm") Bookmark bookmark, @PathVariable String userID, Model model) {
        Account account = accountRepository.findByUsername(userID).get();
        Bookmark updated_bookmark = bookmarkRepository.findOne(bid);
        updated_bookmark.setUri(bookmark.uri);
        updated_bookmark.setDescription(bookmark.description);
        bookmarkRepository.save(updated_bookmark);
        return "redirect:/ManageBookmarks/"+userID+"/view";

    }

    @Autowired
    public webcontroller(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }
}
