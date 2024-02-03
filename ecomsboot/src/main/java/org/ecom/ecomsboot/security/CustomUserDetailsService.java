package org.ecom.ecomsboot.security;

import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.model.dto.UserPrincipal;
import org.ecom.ecomsboot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by email"));

        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;

    }



}
