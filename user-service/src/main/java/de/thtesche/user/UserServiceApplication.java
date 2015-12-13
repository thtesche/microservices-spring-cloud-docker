package de.thtesche.user;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

  @Autowired
  UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @PostConstruct
  public void init() {
    Set<User> users = new HashSet<>();
    users.add(new User("Mick", "Mudder", "muddy", "mick@mudder.com"));
    users.add(new User("Dennis", "Dorgen", "dorgy", "denis@dorgen.com"));

    userRepository.save(users);
  }
}
