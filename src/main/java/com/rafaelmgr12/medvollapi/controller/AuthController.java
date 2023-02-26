package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.domain.user.AuthDataDTO;
import com.rafaelmgr12.medvollapi.domain.user.User;
import com.rafaelmgr12.medvollapi.infra.security.JWTTokenDTO;
import com.rafaelmgr12.medvollapi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid AuthDataDTO data){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = manager.authenticate(token);

        String jwt = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok( new JWTTokenDTO(jwt));
    }

}
