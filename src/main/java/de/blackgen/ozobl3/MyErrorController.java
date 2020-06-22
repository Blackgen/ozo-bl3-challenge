package de.blackgen.ozobl3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class MyErrorController extends AbstractHandlerExceptionResolver {

  @Override
  protected ModelAndView doResolveException(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {
    // In case of an error the session will be deleted.
    // During development an error was client session would not exist after restart.
    request.getSession().invalidate();
    return new ModelAndView();
  }

}