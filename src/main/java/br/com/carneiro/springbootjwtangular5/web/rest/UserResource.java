package br.com.carneiro.springbootjwtangular5.web.rest;

import br.com.carneiro.springbootjwtangular5.domain.User;
import br.com.carneiro.springbootjwtangular5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> users() {
        return userService.all();
    }

    @GetMapping("/users/{id}")
    public Optional<User> user(@PathVariable Long id) {
        return userService.find(id);
    }
}
