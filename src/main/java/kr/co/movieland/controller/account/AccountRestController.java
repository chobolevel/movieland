package kr.co.movieland.controller.account;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.enums.common.ApiExceptionType;
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

  @GetMapping("find-username")
  public ResponseEntity<?> findUsername(@RequestParam String name, @RequestParam String email) throws ApiException {
    Account findAccount = accountService.findOne(Account.builder().name(name).email(email).build());
    if(findAccount == null) {
      throw new ApiException(ApiExceptionType.ACCOUNT_NOT_EXIST);
    }
    return new ResponseEntity<>(findAccount.getUsername(), HttpStatus.OK);
  }

  @PostMapping("send-auth-number")
  public ResponseEntity<?> sendAuthNumber(@RequestBody Account account) throws ApiException, MessagingException {
    accountService.sendAuthNumber(account);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PostMapping("check-auth-number")
  public ResponseEntity<?> checkAuthNumber(@RequestBody Account account) throws ApiException {
    Account checkedAccount = accountService.checkAuthNumber(account);
    return new ResponseEntity<>(checkedAccount, HttpStatus.OK);
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
