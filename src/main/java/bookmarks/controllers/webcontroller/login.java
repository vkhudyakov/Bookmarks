package bookmarks.controllers.webcontroller;

import bookmarks.data.model.Account;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by vkhudiakov on 14/09/15.
 */
@Controller
@RequestMapping("/login")
public class login {


    @RequestMapping(method = RequestMethod.GET)
    public String UserLogin(Account account, Model model) {
        model.addAttribute("Account", account);
        return "login";
    }
}
