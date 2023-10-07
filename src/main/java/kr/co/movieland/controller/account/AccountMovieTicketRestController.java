package kr.co.movieland.controller.account;

import kr.co.movieland.entity.account.AccountMovieTicket;
import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.account.AccountMovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account-movie-ticket")
@RequiredArgsConstructor
public class AccountMovieTicketRestController {

  private final AccountMovieTicketService accountMovieTicketService;

  @PostMapping("")
  public ResponseEntity<?> ticketing(@RequestBody AccountMovieTicket accountMovieTicket) throws ApiException {
    accountMovieTicketService.create(accountMovieTicket);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

}
