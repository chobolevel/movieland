package kr.co.movieland.controller.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
