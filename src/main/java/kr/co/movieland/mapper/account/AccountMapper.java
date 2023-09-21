package kr.co.movieland.mapper.account;

import kr.co.movieland.entity.account.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

  List<Account> findAll(Account account);

  Account findOne(Account account);

  void create(Account account);

  void modify(Account account);

  void resign(Account account);

}
