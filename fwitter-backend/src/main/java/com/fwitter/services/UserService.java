package com.fwitter.services;

import com.fwitter.exceptions.EmailAlreadyTakenException;
import com.fwitter.models.ApplicationUser;
import com.fwitter.models.RegistrationObject;
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

    public ApplicationUser registerUser(RegistrationObject registrationObject){

        ApplicationUser applicationUser = new ApplicationUser();

        applicationUser.setFirstName(registrationObject.getFirstName());
        applicationUser.setLastName(registrationObject.getLastName());
        applicationUser.setEmail(registrationObject.getEmail());
        applicationUser.setDateOfBirth(registrationObject.getDateOfBirth());

        String username = applicationUser.getFirstName() + applicationUser.getLastName();

        boolean nameTaken = true;

        String tempName = "";
        while(nameTaken) {
            tempName = generateUsername(username);
            if (userRepository.findByUsername(tempName).isEmpty()) {
                nameTaken = false;
            }
        }

        applicationUser.setUsername(tempName);

        try {
            return userRepository.save(applicationUser);
        } catch (Exception exception) {
            throw new EmailAlreadyTakenException();
        }

    }

    private String generateUsername(String username) {
        //Generates a 9 digit number
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return username+generatedNumber;
    }
}
