package studio.dboo.reserve.api.inn;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.dboo.reserve.api.accounts.CurrentAccount;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;

import java.util.List;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inn")
public class InnController {

    private final InnService innService;

    @GetMapping
    @ApiOperation(value = "getInn", notes = "숙소 정보 조회")
    public ResponseEntity<Inn> getInn(@CurrentAccount Account account){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInn(account));
    }

}
