package com.dn.shoptech;

import com.dn.shoptech.model.Role;
import com.dn.shoptech.model.User;
import com.dn.shoptech.repository.UserRepo;
import com.dn.shoptech.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class ShoptechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoptechApplication.class, args);
	}

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Bean
	 CommandLineRunner runner(UserService userService, UserRepo userRepo){
		return args -> {
//			Set<Role> roles = new HashSet<>();
//			roles.add(new Role(4,"ROLE_USER"));
//			User u1 = new User("a", passwordEncoder.encode("a"),"a","a" ,"a@gmail.com",null,null,true,null);
//			userService.save(u1);

		};
	 }
}
