package com.thtesche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
   @EnableAuthorizationServer
public class AuthserverApplication extends WebMvcConfigurerAdapter {

   public static void main(String[] args) {
      SpringApplication.run(AuthserverApplication.class, args);
   }

//   @Configuration
//   @EnableAuthorizationServer
//   protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//      @Autowired
//      private AuthenticationManager authenticationManager;
//
//      @Override
//      public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//         endpoints.authenticationManager(authenticationManager);
//      }
//
//      @Override
//      public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//         clients.inMemory()
//            .withClient("exampleclient")
//            .secret("highlySecret")
//            .authorizedGrantTypes("authorization_code", "refresh_token",
//               "password").scopes("read","write");
//      }
//
//   }
}
