package studio.dboo.reserve.module.accounts;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.module.accounts.entity.Account;
import studio.dboo.reserve.infra.annotation.RestLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@RestLogger
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    //Account CRUD
    @GetMapping
    @ApiOperation(value = "getAccount", notes = "계정 조회")
    public ResponseEntity<Account> getAccount(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(principal.getName()));
    }

    @PostMapping
    @ApiOperation(value = "createAccount", notes = "계정 생성")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.createAccount(account));
    }

    @PutMapping
    @ApiOperation(value = "updateAccount", notes = "계정 수정")
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
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
