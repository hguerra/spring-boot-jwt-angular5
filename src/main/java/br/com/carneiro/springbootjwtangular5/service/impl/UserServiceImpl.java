package br.com.carneiro.springbootjwtangular5.service.impl;

import br.com.carneiro.springbootjwtangular5.domain.User;
import br.com.carneiro.springbootjwtangular5.repository.UserRepository;
import br.com.carneiro.springbootjwtangular5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptEncoder) {
        this.repository = repository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Iterable<User> all() {
        return repository.findAll();
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public User find(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
