package com.asutosh.event_booking_system.repository;

import com.asutosh.event_booking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String Email);       // for user profile

     boolean existsByEmail(String Email);            // At the time of sign up to check if email already exist in the db
}
