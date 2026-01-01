package com.asutosh.event_booking_system.service;

import com.asutosh.event_booking_system.entity.Booking;
import com.asutosh.event_booking_system.entity.Event;
import com.asutosh.event_booking_system.entity.User;
import com.asutosh.event_booking_system.exception.BadRequestException;
import com.asutosh.event_booking_system.exception.ResourceNotFoundException;
import com.asutosh.event_booking_system.exception.UnauthorizedException;
import com.asutosh.event_booking_system.repository.BookingRepository;
import com.asutosh.event_booking_system.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    // BOOK EVENT
    @Transactional
    public Booking bookEvent(User user, Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        // Rule 1: Prevent duplicate booking
        bookingRepository.findByUserAndEvent(user, event)
                .ifPresent(b -> {
                    throw new RuntimeException("Event already booked");
                });

        // Rule 2: Seat availability
        if (event.getAvailableSeats() <= 0) {
            throw new BadRequestException("No seats available for this event");
        }

        // Update seat count
        event.setAvailableSeats(event.getAvailableSeats() - 1);

        try {
            Booking booking = Booking.builder()
                    .user(user)
                    .event(event)
                    .bookingDate(LocalDateTime.now())
                    .build();

            event.setAvailableSeats(event.getAvailableSeats() - 1);

            return bookingRepository.save(booking);

        } catch (DataIntegrityViolationException ex) {
            // Unique constraint on (user_id, event_id)
            throw new BadRequestException("You have already booked this event");
        }
    }

    // CANCEL BOOKING
    @Transactional
    public void cancelBooking(User user, Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        // Rule 3: Only owner can cancel
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not allowed to cancel this booking");
        }

        // Restore seat
        Event event = booking.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + 1);

        bookingRepository.delete(booking);
    }

    // GET USER BOOKINGS
    public List<Booking> getMyBookings(User user) {
        return bookingRepository.findByUser(user);
    }
}
