package studio.dboo.reserve.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by dboo on 2021/11/05
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Controller
public class RootViewRedirectionController {

    @RequestMapping("/")
    public String root(Model model, Principal principal) {
        transferUsernameToModel(model, principal);
        return "index";
    }

    private void transferUsernameToModel(Model model, Principal principal) {
        if(principal == null){
            model.addAttribute("userId", "Welcome To Dboo's Log");
        } else {
            model.addAttribute("userId", principal.getName());
        }
    }
}
