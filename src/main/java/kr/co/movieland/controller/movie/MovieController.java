package kr.co.movieland.controller.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("movie")
public class MovieController {

  @GetMapping("list")
  public String list() {
    return "/movie/list";
  }

  @GetMapping("upload")
  public String upload() {
    return "/movie/upload";
  }

  @GetMapping("detail")
  public String detail(@RequestParam("id") String id, Model model) {
    model.addAttribute("id", id);
    return "/movie/detail";
  }

}
