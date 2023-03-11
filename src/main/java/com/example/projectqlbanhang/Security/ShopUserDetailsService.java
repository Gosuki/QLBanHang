package com.example.projectqlbanhang.Security;

import com.example.projectqlbanhang.Entity.User;
import com.example.projectqlbanhang.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ShopUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmailAndActive(username,true)
                .map(ShopUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("Not found user"));
    }
}
