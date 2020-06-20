package de.blackgen.ozobl3;

import java.util.ArrayList;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenIdService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

  @Inject
  private SteamFetcher steamFetcher;


  @Override
  public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
      throws UsernameNotFoundException {

    return new User("Test", "test", new ArrayList<>());

  }
}
