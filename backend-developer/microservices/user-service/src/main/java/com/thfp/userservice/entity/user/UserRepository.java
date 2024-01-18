package com.thfp.userservice.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, String> {
    // jpa descriptive methods
    // jpql
    @Query("SELECT u FROM User u WHERE u.createdAt < :datetime")
    User findAllByCreatedAtBefore(LocalDateTime dateTime);
}
