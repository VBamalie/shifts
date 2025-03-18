package com.shifts.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Employee;
import com.shifts.backend.repository.EmployeeRepo;



@Service
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;
    
    public MyUserDetailsService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepo.findByEmail(username);

        if ( user == null ) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
}
