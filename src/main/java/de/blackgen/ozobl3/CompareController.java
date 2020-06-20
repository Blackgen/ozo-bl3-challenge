package de.blackgen.ozobl3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareController {

  @GetMapping("/login/compare")
  public String show(ModelMap modelMap) {
    return "Compare";
  }
}
