package de.blackgen.ozobl3;

import javax.inject.Inject;
import org.openid4java.consumer.ConsumerManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class HttpConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private NoopAuthService openIdService;
  @Inject
  private AuthenticationHandler authenticationHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //TODO Not sure if this is needed, it was added to prevent a bug with a stateless openid config
    ConsumerManager consumerManager = new ConsumerManager();
    consumerManager.setMaxAssocAttempts(0);

    http.authorizeRequests()
        //Basically everypage is accessable
        .antMatchers("/**").permitAll()
        .and()
        .openidLogin().loginPage("/").permitAll()
        .authenticationUserDetailsService(openIdService)
        .consumerManager(consumerManager)
        .successHandler(authenticationHandler)
        .failureUrl("/?fail"); //TODO Add some error message on login failure
  }

}
