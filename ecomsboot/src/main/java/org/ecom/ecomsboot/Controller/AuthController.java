package org.ecom.ecomsboot.Controller;

import org.ecom.ecomsboot.config.JwtProvider;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.UserRepository;
import org.ecom.ecomsboot.request.LoginRequest;
import org.ecom.ecomsboot.response.AuthResponse;

import org.ecom.ecomsboot.service.CustomerUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private CustomerUserServiceImpl customerUserServiceImpl;
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException {
        String email=user.getEmail();
        String password=user.getPassword();
        String firstString=user.getFirstName();
        String lastString=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);
        if(isEmailExist!=null){
            throw new UserException("User already exist with email: ");

        }
        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstString);
        createdUser.setLastName(lastString);
        User savedUser=userRepository.save(createdUser);
        Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup successfully");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login successfully");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customerUserServiceImpl.loadUserByUsername(username);
        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
