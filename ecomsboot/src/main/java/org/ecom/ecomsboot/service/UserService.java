package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findUserById(Long userId) throws UserException;

    User findUserByJwt(String jwt) throws UserException;
}
