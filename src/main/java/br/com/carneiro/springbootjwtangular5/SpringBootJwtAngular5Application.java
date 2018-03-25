package br.com.carneiro.springbootjwtangular5;

import br.com.carneiro.springbootjwtangular5.domain.User;
import br.com.carneiro.springbootjwtangular5.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  Basic JWT authentication
 *
 *  Reference:
 *  http://www.devglan.com/spring-security/spring-boot-jwt-auth
 */
@SpringBootApplication
public class SpringBootJwtAngular5Application {

    @Bean
    public CommandLineRunner seed(UserService userService){
        return args -> {
            userService.save(new User("user1", "1234", 5000L, 23));
            userService.save(new User("user2", "1234", 2000L, 28));
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAngular5Application.class, args);
	}
}
