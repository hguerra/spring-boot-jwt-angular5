package br.com.carneiro.springbootjwtangular5.repository;

import br.com.carneiro.springbootjwtangular5.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
