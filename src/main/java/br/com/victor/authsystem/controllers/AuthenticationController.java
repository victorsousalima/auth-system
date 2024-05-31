package br.com.victor.authsystem.controllers;

import br.com.victor.authsystem.dto.TokenResponse;
import br.com.victor.authsystem.dto.UserLoginRequest;
import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        var tokenDefault = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var authenticationDefault = manager.authenticate(tokenDefault);

        var userAuthenticade = (User) authenticationDefault.getPrincipal();

        var token = tokenService.generateToken(userAuthenticade);

        return ResponseEntity.ok(new TokenResponse(token));
    }

}
