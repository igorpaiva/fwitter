package com.fwitter;

import com.fwitter.models.ApplicationUser;
import com.fwitter.models.Role;
import com.fwitter.repositories.RoleRepository;
import com.fwitter.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class FwitterBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(FwitterBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {
			Role role = new Role();
			role.setRoleId(1);
			role.setAuthority("USER");
			roleRepository.save(role);

			ApplicationUser user = new ApplicationUser();
			user.setFirstName("unknown");
			user.setLastName("nwonknu");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByAuthority("USER").get());
			user.setAuthorities(roles);
			userRepository.save(user);
		};
	}

}
