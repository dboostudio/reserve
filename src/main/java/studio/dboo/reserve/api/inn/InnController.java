package studio.dboo.reserve.api.inn;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.infra.exception.ReserveException;

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

    @GetMapping("/list")
    @ApiOperation(value = "getInnList", notes = "숙소 리스트 정보 조회")
    public ResponseEntity<List<Inn>> getInnList() throws ReserveException {
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInnList());
    }

    @GetMapping("/{innName}")
    @ApiOperation(value = "getInn", notes = "숙소 정보 조회")
    public ResponseEntity<Inn> getInn(@PathVariable String innName){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getInnByInnName(innName));
    }

    @GetMapping
    @ApiOperation(value = "getFirstInn", notes = "숙소 정보 조회")
    public ResponseEntity<Inn> getFirstInn(){
        return ResponseEntity.status(HttpStatus.OK).body(innService.getMyFirstInn());
    }

    @PostMapping
    @ApiOperation(value = "createInn", notes = "숙소 정보 생성")
    public ResponseEntity<Inn> createInn(@RequestBody Inn inn) {
        innService.createInn(inn);
        return ResponseEntity.status(HttpStatus.OK).body(inn);
    }

    @PutMapping
    @ApiOperation(value = "updateInn", notes = "숙소 정보 업데이트")
    public ResponseEntity<Inn> updateInn(@RequestBody Inn inn) {
        return ResponseEntity.status(HttpStatus.OK).body(innService.updateInn(inn));
    }
}
