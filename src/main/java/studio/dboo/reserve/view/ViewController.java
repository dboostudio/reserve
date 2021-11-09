package studio.dboo.reserve.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String index(Model model, Principal principal){
        return "index";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        return "sign-up";
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        model.addAttribute(account);
        return "account/profile";

    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        return "admin";
    }

    @GetMapping("/inn")
    public String inn(Model model, Principal principal){
        return "inn/inn";
    }

    @GetMapping("/room")
    public String room(Model model, Principal principal){
        return "inn/room";
    }

    @GetMapping("/calendar")
    public String calendar(Model model, Principal principal){
        return "inn/calendar";
    }

    @GetMapping("/reservation")
    public String reservation(Model model, Principal principal){
        return "inn/reservation";
    }
}
