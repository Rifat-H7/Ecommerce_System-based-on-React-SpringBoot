package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.UserRepository;
import org.ecom.ecomsboot.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id: " + userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws UserException {
        String email = jwtTokenProvider.getUsername(jwt);

        User user = userRepository.findByEmail(email).get();

        if(user == null){
            throw new UserException("user not found with email " + email);
        }
        return user;
    }



}
