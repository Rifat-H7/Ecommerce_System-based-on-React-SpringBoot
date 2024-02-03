package org.ecom.ecomsboot.Controller;

import lombok.RequiredArgsConstructor;

import org.ecom.ecomsboot.model.dto.*;
import org.ecom.ecomsboot.response.AccountResponse;
import org.ecom.ecomsboot.response.JwtAuthResponse;
import org.ecom.ecomsboot.response.LoginResponse;
import org.ecom.ecomsboot.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AccountResponse> register(@RequestBody RegisterDTO registerDTO){
        AccountResponse newUser = authService.register(registerDTO);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    @PostMapping("/active")
    public UserActive getActiveCustomer(@RequestBody LoginDTO loginDTO){
        return authService.getUserActive(loginDTO);
    }


    @PostMapping("/activated")
    public AccountResponse activeAccountUser(@RequestBody ActiveAccount activeAccount){
        return authService.activeAccount(activeAccount);
    }


    @PostMapping("/check-email")
    public AccountResponse checkEmailUser(@RequestBody LoginResponse loginResponse){
        return authService.checkEmail(loginResponse);
    }

    @PostMapping("/reset-password")
    public AccountResponse resetPassword(@RequestBody NewPassword newPassword){
        return authService.resetPassword(newPassword);
    }



}
