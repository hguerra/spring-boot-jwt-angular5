package br.com.carneiro.springbootjwtangular5.web.rest;

import br.com.carneiro.springbootjwtangular5.domain.User;
import br.com.carneiro.springbootjwtangular5.dto.LoginUser;
import br.com.carneiro.springbootjwtangular5.security.AuthToken;
import br.com.carneiro.springbootjwtangular5.security.JwtTokenUtil;
import br.com.carneiro.springbootjwtangular5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    @Autowired
    public AuthenticationResource(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(),
                loginUser.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.find(loginUser.getUsername());

        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token));
    }
}
