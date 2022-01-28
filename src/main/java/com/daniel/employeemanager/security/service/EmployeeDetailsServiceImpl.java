package com.daniel.employeemanager.security.service;

import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return EmployeeDetailsImpl.build(user);
    }
}
