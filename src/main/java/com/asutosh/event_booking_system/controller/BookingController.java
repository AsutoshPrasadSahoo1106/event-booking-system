package com.asutosh.event_booking_system.controller;


import com.asutosh.event_booking_system.dto.BookingResponse;
import com.asutosh.event_booking_system.entity.Booking;
import com.asutosh.event_booking_system.entity.User;
import com.asutosh.event_booking_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    // BOOK EVENT (USER)
    @PostMapping("/{eventId}")
    public ResponseEntity<BookingResponse> bookEvent(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId) {

        Booking booking = bookingService.bookEvent(user, eventId);

        BookingResponse response =
                modelMapper.map(booking, BookingResponse.class);
        response.setEventId(booking.getEvent().getId());
        response.setEventTitle(booking.getEvent().getTitle());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // CANCEL BOOKING
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(
            @AuthenticationPrincipal User user,
            @PathVariable Long bookingId) {

        bookingService.cancelBooking(user, bookingId);
        return ResponseEntity.noContent().build(); // 204
    }

    // VIEW MY BOOKINGS
    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> myBookings(
            @AuthenticationPrincipal User user) {

        List<BookingResponse> responses =
                bookingService.getMyBookings(user)
                        .stream()
                        .map(booking -> {
                            BookingResponse r =
                                    modelMapper.map(booking, BookingResponse.class);
                            r.setEventId(booking.getEvent().getId());
                            r.setEventTitle(booking.getEvent().getTitle());
                            return r;
                        })
                        .toList();

        return ResponseEntity.ok(responses);
    }
}
