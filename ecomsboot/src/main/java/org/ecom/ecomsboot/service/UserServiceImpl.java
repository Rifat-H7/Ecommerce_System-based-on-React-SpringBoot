package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import org.ecom.ecomsboot.config.JwtProvider;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not found with id"+ userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromJwt(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException(email+" not found");
        }
        return user;
    }
}
