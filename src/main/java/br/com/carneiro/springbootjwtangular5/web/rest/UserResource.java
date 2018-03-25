package br.com.carneiro.springbootjwtangular5.web.rest;

import br.com.carneiro.springbootjwtangular5.domain.User;
import br.com.carneiro.springbootjwtangular5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signup")
    public User save(@RequestBody  User user) {
        return userService.save(user);
    }
}
