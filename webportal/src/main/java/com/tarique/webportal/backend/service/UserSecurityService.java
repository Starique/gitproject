package com.tarique.webportal.backend.service;

import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Mehnuma on 1/22/2017.
 */
@Service
public class UserSecurityService implements UserDetailsService{

    /** The Application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            LOG.warn(" User {} Not found ", username);
            throw new UsernameNotFoundException("UserName " + username + " Not Found");
        }
        return user;
    }
}
