package com.pawan.ecommerce.repo;


import com.pawan.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> getByEmail(String user);

    //Optional<User> findByEmail(String email);
}
