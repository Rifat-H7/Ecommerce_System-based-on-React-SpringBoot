package org.ecom.ecomsboot.service.auth;

import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.model.dto.*;
import org.ecom.ecomsboot.response.AccountResponse;
import org.ecom.ecomsboot.response.JwtAuthResponse;
import org.ecom.ecomsboot.response.LoginResponse;

public interface AuthService {

    AccountResponse register(RegisterDTO registerDTO);
    JwtAuthResponse login(LoginDTO loginDTO);

    UserActive getUserActive(LoginDTO loginDTO);
    User getByEmail(String email);
    AccountResponse activeAccount(ActiveAccount activeAccount);
    AccountResponse checkEmail(LoginResponse loginResponse);
    AccountResponse resetPassword(NewPassword newPassword);


}
