package kr.co.movieland.controller.sign;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign")
@RequiredArgsConstructor
public class SignRestController {

  private final AccountService accountService;

  @PostMapping("up")
  public ResponseEntity<?> signUp(@RequestBody Account account) throws ApiException {
    accountService.create(account);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

}
