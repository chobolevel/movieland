package kr.co.movieland.service.account.impl;

import kr.co.movieland.entity.account.AccountMovieTicket;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.account.AccountMovieTicketMapper;
import kr.co.movieland.service.account.AccountMovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountMovieTicketServiceImpl implements AccountMovieTicketService {

  private final AccountMovieTicketMapper accountMovieTicketMapper;

  @Override
  public void create(AccountMovieTicket accountMovieTicket) throws ApiException {
    if(accountMovieTicket.getAccountId() == null || accountMovieTicket.getAccountId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "accountId", "String");
    } else if(accountMovieTicket.getMovieTicketIdList() == null || accountMovieTicket.getMovieTicketIdList().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "movieTicketIdList", "List");
    }
    List<AccountMovieTicket> accountMovieTicketList = new ArrayList<>();
    for(String movieTicketId : accountMovieTicket.getMovieTicketIdList()) {
      accountMovieTicketList.add(AccountMovieTicket
          .builder()
          .accountId(accountMovieTicket.getAccountId())
          .movieTicketId(movieTicketId)
          .build());
    }
    accountMovieTicketMapper.create(accountMovieTicketList);
  }

}
