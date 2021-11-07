package com.example.demo.repository.userRepos;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getFirstByEmail(String username);

}
