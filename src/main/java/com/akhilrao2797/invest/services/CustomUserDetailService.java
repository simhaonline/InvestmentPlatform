package com.akhilrao2797.invest.services;

import com.akhilrao2797.invest.models.user.User;
import com.akhilrao2797.invest.respository.AnalystRepository;
import com.akhilrao2797.invest.respository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AnalystRepository analystRepository;
    private Logger LOG = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String name) {
        LOG.info("Entered UserDetails.loadUserByUsername");
        User user = customerRepository.findByName(name);
        if(user == null)
            user = analystRepository.findByName(name);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
