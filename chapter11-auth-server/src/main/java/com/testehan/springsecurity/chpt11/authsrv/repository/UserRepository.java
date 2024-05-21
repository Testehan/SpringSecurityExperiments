package com.testehan.springsecurity.chpt11.authsrv.repository;

import com.testehan.springsecurity.chpt11.authsrv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
