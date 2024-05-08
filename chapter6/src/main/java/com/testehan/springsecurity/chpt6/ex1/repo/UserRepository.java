package com.testehan.springsecurity.chpt6.ex1.repo;

import com.testehan.springsecurity.chpt6.ex1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String u);
}
