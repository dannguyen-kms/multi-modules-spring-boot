package com;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Data Access Class. */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT DISTINCT u from User u where u.email = ?1")
  Optional<User> findByEmail(String email);
}
