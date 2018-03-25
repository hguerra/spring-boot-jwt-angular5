package br.com.carneiro.springbootjwtangular5.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {

    private String username;

    private String password;
}
