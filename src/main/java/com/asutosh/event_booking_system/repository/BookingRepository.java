package com.asutosh.event_booking_system.repository;

import com.asutosh.event_booking_system.entity.Booking;
import com.asutosh.event_booking_system.entity.Event;
import com.asutosh.event_booking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findByUserAndEvent(User user, Event event);               //To prevent duplicate booking

    List<Booking> findByUser(User user);                                        // To show users booking history

}
