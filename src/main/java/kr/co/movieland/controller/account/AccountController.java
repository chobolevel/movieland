package kr.co.movieland.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

  @GetMapping("find-username")
  public String findUsername() {
    return "/account/find-username";
  }

  @GetMapping("find-password")
  public String findPassword() {
    return "/account/find-password";
  }

  @GetMapping("change-password/{id}")
  public String changePassword(@PathVariable String id, Model model) {
    model.addAttribute("accountId", id);
    return "/account/change-password";
  }

  @GetMapping("profile")
  public String profile() {
    return "/account/profile";
  }

}
