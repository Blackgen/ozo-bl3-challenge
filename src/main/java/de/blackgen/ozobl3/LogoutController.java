package de.blackgen.ozobl3;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

  @RequestMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.getSession().invalidate();
    response.sendRedirect("/");
  }
}
