package dev.gideon.maintenance.dynamic.repository.interfaces;

import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findByUsername(String username);
}
