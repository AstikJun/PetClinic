package com.example.demo.service.userServ;

import com.example.demo.entity.User;
import com.example.demo.repository.roleRepos.RoleRepository;
import com.example.demo.repository.userRepos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepo.save(user);
    }
public  User getUserByEmail(String email){
     return    userRepo.getFirstByEmail(email);
}
    @Override
    public User register(User user){
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setTypeOfAnimal(user.getTypeOfAnimal());
        user.setActive(1);
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getFirstByRoleName("ROLE_USER"))));
        user.setPhone(user.getPhone());
        System.out.println(roleRepository.getFirstByRoleName("USER"));
        return userRepo.save(user);
     }
    @Override
    public void deleteUserById(Integer id) {
     userRepo.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
     userRepo.deleteAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
