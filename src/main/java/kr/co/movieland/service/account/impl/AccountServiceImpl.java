package kr.co.movieland.service.account.impl;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.account.AccountMapper;
import kr.co.movieland.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

  private final AccountMapper accountMapper;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public List<Account> findAll() {
    return accountMapper.findAll(Account.builder().build());
  }

  @Override
  public Account findOne(Account account) {
    return accountMapper.findOne(account);
  }

  @Override
  public void create(Account account) throws ApiException {
    if(account.getUsername() == null || account.getUsername().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "username", "String");
    } else if(account.getPassword() == null || account.getPassword().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "password", "String");
    } else if(account.getName() == null || account.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    } else if(account.getEmail() == null || account.getEmail().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "email", "String");
    }
    account.setId(UUID.randomUUID().toString());
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    accountMapper.create(account);
  }

  @Override
  public void modify(Account account) throws ApiException {
    if(account.getId() == null || account.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    accountMapper.modify(account);
  }

  @Override
  public void resign(Account account) throws ApiException {
    if(account.getId() == null || account.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    accountMapper.resign(account);
  }

}
