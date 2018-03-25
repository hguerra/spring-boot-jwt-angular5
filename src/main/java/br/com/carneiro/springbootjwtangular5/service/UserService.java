package br.com.carneiro.springbootjwtangular5.service;

import br.com.carneiro.springbootjwtangular5.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Iterable<User> all();

    void delete(User user);

    Optional<User> find(Long id);
}
