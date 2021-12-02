package studio.dboo.reserve.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.accounts.entity.Account;

import java.security.Principal;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ViewController {

    private final AccountService accountService;

    @GetMapping("/")
    public ModelAndView index(Model model, Principal principal){
        return new ModelAndView("index");
    }

    @GetMapping("/sign-up")
    public ModelAndView signUp(Model model){
        return new ModelAndView("sign-up");
    }

    @GetMapping("/login")
    public ModelAndView login(Model model, Principal principal) {
        return new ModelAndView("login");
    }

    @GetMapping("/profile")
    public ModelAndView profile(Model model, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        model.addAttribute(account);
        return new ModelAndView("account/profile");
    }

    @GetMapping("/admin")
    public ModelAndView admin(Model model, Principal principal){
        return new ModelAndView("admin");
    }

    @GetMapping("/inn")
    public ModelAndView inn(Model model, Principal principal){
        return new ModelAndView( "inn/inn");
    }

    @GetMapping("/room")
    public ModelAndView room(Model model, Principal principal){
        return new ModelAndView("room/room");
    }

    @GetMapping("/calendar")
    public ModelAndView calendar(Model model, Principal principal){
        return new ModelAndView("calendar/calendar");
    }

    @GetMapping("/reservation")
    public ModelAndView reservation(Model model, Principal principal){
        return new ModelAndView("reservation/reservation");
    }
}
