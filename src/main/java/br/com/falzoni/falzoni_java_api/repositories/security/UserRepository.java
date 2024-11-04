package br.com.falzoni.falzoni_java_api.repositories.security;

import br.com.falzoni.falzoni_java_api.domain.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
}
