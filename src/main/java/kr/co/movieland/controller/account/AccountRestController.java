package kr.co.movieland.controller.account;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {

  private final AccountService accountService;

  @GetMapping("list")
  public ResponseEntity<?> getAccountList() {
    return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getAccount(@PathVariable String id) {
    return new ResponseEntity<>(accountService.findOne(Account.builder().id(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createAccount(@RequestBody Account account) throws ApiException {
    accountService.create(account);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyAccount(@PathVariable String id, @RequestBody Account account) throws ApiException {
    account.setId(id);
    accountService.modify(account);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> resignAccount(@PathVariable String id) throws ApiException {
    accountService.resign(Account.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
