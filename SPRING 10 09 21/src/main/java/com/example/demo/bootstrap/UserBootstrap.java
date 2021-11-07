package com.example.demo.bootstrap;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.roleRepos.RoleRepository;
import com.example.demo.repository.userRepos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class UserBootstrap implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);


        User admin = new User();
        admin.setActive(1);
        admin.setEmail("serikovastik@gmail.com");
        admin.setFirstName("Astan");
        admin.setLastName("Serikov");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setPhone("+996555427590");
        admin.setTypeOfAnimal("Cat");
        admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
        userRepository.save(admin);
//
//        User user = new User();
//        user.setActive(1);
//        user.setEmail("user@gmail.com");
//        user.setFirstName("User");
//        user.setLastName("UserLastName");
//        user.setPassword("123");
//        user.setPhone("+996554427590");
//        user.setTypeOfAnimal("DOG");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        userRepository.save(user);
//        System.out.println(admin);
//        System.out.println(user);



    }
}
