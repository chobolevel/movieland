package kr.co.movieland.security.service;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.mapper.account.AccountMapper;
import kr.co.movieland.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final AccountMapper accountMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account findAccount = accountMapper.findOne(Account.builder().username(username).build());

    if(findAccount == null) {
      throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
    }

    return new CustomUserDetails(findAccount);
  }
}
