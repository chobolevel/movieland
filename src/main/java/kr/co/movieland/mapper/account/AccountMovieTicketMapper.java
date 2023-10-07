package kr.co.movieland.mapper.account;

import kr.co.movieland.entity.account.AccountMovieTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMovieTicketMapper {

  void create(List<AccountMovieTicket> accountMovieTicketList);

}
