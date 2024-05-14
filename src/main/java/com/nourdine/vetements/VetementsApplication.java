package com.nourdine.vetements;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.nourdine.vetements.entities.Role;
import com.nourdine.vetements.entities.User;
import com.nourdine.vetements.entities.Vetement;
import com.nourdine.vetements.service.UserService;
import com.nourdine.vetements.service.VetementService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class VetementsApplication implements CommandLineRunner {
	@Autowired
	VetementService vetementService;

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(VetementsApplication.class, args);
	}

	/*
	 * @PostConstruct void init_users() {
	 * 
	 * userService.addRole(new Role(null,"ADMIN")); userService.addRole(new
	 * Role(null,"AGENT")); userService.addRole(new Role(null,"USER"));
	 * 
	 * userService.saveUser(new User(null,"admin","123",true,null));
	 * userService.saveUser(new User(null,"nadhem","123",true,null));
	 * userService.saveUser(new User(null,"user1","123",true,null));
	 * 
	 * userService.addRoleToUser("admin", "ADMIN");
	 * userService.addRoleToUser("nadhem", "USER");
	 * userService.addRoleToUser("nadhem", "AGENT");
	 * userService.addRoleToUser("user1", "USER"); }
	 */
	
	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Vetement.class);
		// System.out.println("Password Encoded BCRYPT :******************** ");
		// System.out.println(passwordEncoder.encode("123"));

	}

}
