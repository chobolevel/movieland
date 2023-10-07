package kr.co.movieland.service.account;

import kr.co.movieland.entity.account.AccountMovieTicket;
import kr.co.movieland.exception.ApiException;

public interface AccountMovieTicketService {

  void create(AccountMovieTicket accountMovieTicket) throws ApiException;

}
