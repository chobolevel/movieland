package kr.co.movieland.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

  @GetMapping("find-username")
  public String findUsername() {
    return "/account/find-username";
  }

}
