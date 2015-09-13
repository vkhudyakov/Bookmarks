package bookmarks.controllers.rest;

import bookmarks.data.model.Account;
import bookmarks.data.model.Bookmark;
import bookmarks.data.repository.AccountRepository;
import bookmarks.data.repository.BookmarkRepository;
import bookmarks.controllers.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by vkhudiakov on 13/09/15.
 */
@RestController
@RequestMapping("/rest/{userId}/bookmarks")
public class BookmarkRestController {

    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        this.validateUser(userId);
        Account account = accountRepository.findByUsername(userId).get();
        Bookmark result = bookmarkRepository.save(new Bookmark(account, input.uri, input.description));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{bookmarkID}", method = RequestMethod.GET)
    Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkID) {
        this.validateUser(userId);
        return this.bookmarkRepository.findOne(bookmarkID);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable String userId) {
        this.validateUser(userId);
        return this.bookmarkRepository.findByAccountUsername(userId);
    }


    @Autowired BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }


    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}




