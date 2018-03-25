package br.com.carneiro.springbootjwtangular5.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Long salary;

    @Column
    private int age;

    public User(String username, String password, Long salary, int age) {
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.age = age;
    }
}
