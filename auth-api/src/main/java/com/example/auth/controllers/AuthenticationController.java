package com.example.auth.controllers;

import com.example.auth.domain.user.AuthenticationDTO;
import com.example.auth.domain.user.LoginResponseDTO;
import com.example.auth.domain.user.RegisterDTO;
import com.example.auth.domain.user.User;
import com.example.auth.infra.security.TokenService;
import com.example.auth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        System.out.println(data.login()+data.senha());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.senha());
        System.out.println(data.senha());
        System.out.println(usernamePassword);
        var auth = authenticationManager.authenticate(usernamePassword);
        System.out.println("Teste");

        var token = tokenService.generateToken((User) auth.getPrincipal());

        System.out.println(auth);
        System.out.println(token);
        return ResponseEntity.ok().body( new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body("Usuario ja existte ");
        System.out.println("hello");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}