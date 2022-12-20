package com.fwitter.services;

import com.fwitter.models.ApplicationUser;
import com.fwitter.models.Role;
import com.fwitter.repositories.RoleRepository;
import com.fwitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApplicationUser registerUser(ApplicationUser applicationUser) {
        Set<Role> roles = applicationUser.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        applicationUser.setAuthorities(roles);

        return userRepository.save(applicationUser);
    }
}
