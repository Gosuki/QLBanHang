package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserById(Long userID);
    User findUsersByUsernameAndActive(String name, boolean active);

    User findUserByIdAndActive(Long id, boolean b);
    Optional<User> findUserByEmailAndActive(String email, boolean active);
}
