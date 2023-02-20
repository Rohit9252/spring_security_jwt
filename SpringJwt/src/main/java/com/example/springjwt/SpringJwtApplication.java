package com.example.springjwt;

import com.example.springjwt.model.MyRoles;
import com.example.springjwt.model.Role;
import com.example.springjwt.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SpringJwtApplication {

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);
    }


    @Bean
    public CommandLineRunner run() throws Exception {
       return agrs ->{
            roleRepo.save(new Role(1, MyRoles.ADMIN));
            roleRepo.save(new Role( 2, MyRoles.USER));
       };
    }


}
