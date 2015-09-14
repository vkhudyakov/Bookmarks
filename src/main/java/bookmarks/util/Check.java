package bookmarks.util;

import bookmarks.controllers.exceptions.UserNotFoundException;
import bookmarks.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vkhudiakov on 14/09/15.
 */
@Component
public class Check {

    private AccountRepository accountRepository;

    public void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Autowired
    public Check(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
