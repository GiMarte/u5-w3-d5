package gianmarte.u5w3d5.repository;

import gianmarte.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
