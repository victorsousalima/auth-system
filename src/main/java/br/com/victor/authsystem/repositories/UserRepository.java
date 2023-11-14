package br.com.victor.authsystem.repositories;

import br.com.victor.authsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
