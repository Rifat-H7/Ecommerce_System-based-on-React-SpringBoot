package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User findUserById(Long userId) throws UserException;
    public User findUserByJwt(String jwt) throws UserException;
}
