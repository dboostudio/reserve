package studio.dboo.reserve.api.accounts;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.api.accounts.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    //Account CRUD
    @GetMapping
    @ApiOperation(value = "getAccount", notes = "계정 조회")
    public ResponseEntity<Account> getAccount(Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(account.getUserId()));
    }

    @PostMapping
    @ApiOperation(value = "createAccount", notes = "계정 생성")
    public ResponseEntity<Account> createAccount(@Valid Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.createAccount(account));
    }

    @PutMapping
    @ApiOperation(value = "putAccount", notes = "계정 수정")
    public ResponseEntity<?> updateAccount(@CurrentAccount Account account) {
        accountService.updateAccount(account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    @ApiOperation(value = "deleteAccount", notes = "계정 삭제")
    public ResponseEntity<String> deleteAccount(@CurrentAccount Account account){
        accountService.deleteAccount(account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    @ApiOperation(value = "login", notes = "로그인")
    public ResponseEntity<?> login(@RequestBody Account account){
        accountService.login(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "logout", notes = "로그아웃")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Account account){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
