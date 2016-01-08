package thtesche.authserver;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthserverApplication extends WebMvcConfigurerAdapter {

   @RequestMapping("/user")
   public Principal user(Principal user) {
      return user;
   }

   public static void main(String[] args) {
      SpringApplication.run(AuthserverApplication.class, args);
   }

   @Configuration
   @EnableAuthorizationServer
   protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

      @Autowired
      private AuthenticationManager authenticationManager;

      @Override
      public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
         endpoints.authenticationManager(authenticationManager);
      }

      @Override
      public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
         super.configure(security);
         security.checkTokenAccess("permitAll()");
      }

      @Override
      public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         clients.inMemory()
                 .withClient("acme")
                 .secret("acmesecret")
                 .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
                 .scopes("users");
      }
   }
}
