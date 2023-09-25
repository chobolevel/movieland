package kr.co.movieland.service.account.impl;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.entity.common.Mail;
import kr.co.movieland.enums.Account.AccountRoleType;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.account.AccountMapper;
import kr.co.movieland.service.account.AccountService;
import kr.co.movieland.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

  private final AccountMapper accountMapper;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JavaMailSender javaMailSender;
  private final RedisTemplate<String, String> redisTemplate;

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
    account.setRole(AccountRoleType.ROLE_USER);
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

  @Override
  public void sendAuthNumber(Account account) throws ApiException, MessagingException {
    if(account.getUsername() == null || account.getUsername().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "username", "String");
    } else if(account.getEmail() == null || account.getEmail().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "email", "String");
    }
    Account findAccount = accountMapper.findOne(account);
    if(findAccount == null) {
      throw new ApiException(ApiExceptionType.ACCOUNT_NOT_EXIST);
    }
    int authNumber = CommonUtil.generateAuthNumber();
    redisTemplate.opsForValue().set(findAccount.getUsername(), String.valueOf(authNumber), 3, TimeUnit.MINUTES);
    CommonUtil.sendAuthNumber(javaMailSender, Mail
        .builder()
        .to(findAccount.getEmail())
        .authNumber(authNumber)
        .build());
  }

  @Override
  public Account checkAuthNumber(Account account) throws ApiException {
    String authNumber = redisTemplate.opsForValue().get(account.getUsername());
    if(!account.getAuthNumber().equals(authNumber)) {
      throw new ApiException(ApiExceptionType.AUTH_NUMBER_NOT_MATCH);
    }
    return accountMapper.findOne(Account.builder().username(account.getUsername()).email(account.getEmail()).build());
  }

}
