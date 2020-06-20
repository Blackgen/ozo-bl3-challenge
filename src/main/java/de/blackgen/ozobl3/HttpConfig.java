package de.blackgen.ozobl3;

import javax.inject.Inject;
import org.openid4java.consumer.ConsumerManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class HttpConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private OpenIdService openIdService;
  @Inject
  private AuthenticationHandler authenticationHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ConsumerManager consumerManager = new ConsumerManager();
    consumerManager.setMaxAssocAttempts(0);
    http.authorizeRequests().antMatchers("/**").permitAll()
        .and()
        .openidLogin()
        .loginPage("/").permitAll()
        .authenticationUserDetailsService(openIdService)
        .consumerManager(consumerManager)
        .successHandler(authenticationHandler)
//        .consumer(new TestConsumer())
        .failureUrl("/?fail");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
  }
}
