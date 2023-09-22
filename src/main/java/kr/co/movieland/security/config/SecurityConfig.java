package kr.co.movieland.security.config;

import kr.co.movieland.security.handler.CustomAuthFailureHandler;
import kr.co.movieland.security.handler.CustomAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final CustomAuthSuccessHandler successHandler;
  private final CustomAuthFailureHandler failureHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().disable()
        .authorizeRequests()
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginPage("/sign/in")
        .loginProcessingUrl("/sign/in")
        .successHandler(successHandler)
        .failureHandler(failureHandler)
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true);
    http
        .rememberMe()
        .rememberMeParameter("remember")
        .tokenValiditySeconds(60*60)
        .alwaysRemember(false)
        .userDetailsService(userDetailsService);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
