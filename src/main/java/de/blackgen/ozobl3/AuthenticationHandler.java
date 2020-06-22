package de.blackgen.ozobl3;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

  @Inject
  private SteamFetcher steamFetcher;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException {
    String principal = ((OpenIDAuthenticationToken) authentication).getIdentityUrl();
    String id = principal.replace("https://steamcommunity.com/openid/id/", "");
    request.getSession().setAttribute("user", steamFetcher.fetch(id));
    response.sendRedirect("/");
  }
}
