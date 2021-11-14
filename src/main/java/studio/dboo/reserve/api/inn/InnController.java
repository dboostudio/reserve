package studio.dboo.reserve.api.inn;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.api.accounts.CurrentAccount;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;

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

    @GetMapping("/list")
    @ApiOperation(value = "getInnList", notes = "숙소 리스트 정보 조회")
    public ResponseEntity<Object> getInnList(@CurrentAccount Account account){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInnList(account));
    }

    @GetMapping("/{innName}")
    @ApiOperation(value = "getInn", notes = "숙소 정보 조회")
    public ResponseEntity<Inn> getInn(@CurrentAccount Account account, String innName){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInn(account, innName));
    }

    @GetMapping("/")
    @ApiOperation(value = "getFirstInn", notes = "숙소 정보 조회")
    public ResponseEntity<Inn> getFirstInn(@CurrentAccount Account account, String innName){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInn(account));
    }

    @PostMapping
    @ApiOperation(value = "createInn", notes = "숙소 정보 생성")
    public ResponseEntity<Inn> createInn(@CurrentAccount Account account, @RequestBody Inn inn) {
        return ResponseEntity.status(HttpStatus.OK).body(innService.createInn(account, inn));
    }

    @PutMapping
    @ApiOperation(value = "updateInn", notes = "숙소 정보 업데이트")
    public ResponseEntity<Inn> updateInn(@CurrentAccount Account account, @RequestBody Inn inn) {
        return ResponseEntity.status(HttpStatus.OK).body(innService.updateInn(account, inn));
    }




}
