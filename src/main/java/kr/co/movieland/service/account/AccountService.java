package kr.co.movieland.service.account;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.exception.ApiException;

import javax.mail.MessagingException;
import java.util.List;

public interface AccountService {

  List<Account> findAll();

  Account findOne(Account account);

  void create(Account account) throws ApiException;

  void modify(Account account) throws ApiException;

  void resign(Account account) throws ApiException;

  void sendAuthNumber(Account account) throws ApiException, MessagingException;

  Account checkAuthNumber(Account account) throws ApiException;

}
