package com.cherkas.vladimir.logisticstransportations.repository;

import com.cherkas.vladimir.logisticstransportations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

}
