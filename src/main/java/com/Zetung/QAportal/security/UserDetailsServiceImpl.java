package com.Zetung.QAportal.security;

import com.Zetung.QAportal.model.UserCustomer;
import com.Zetung.QAportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    public UserDetailsServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        UserCustomer userCustomer = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found"));
        return SecurityUser.fromUser(userCustomer);
    }
}
