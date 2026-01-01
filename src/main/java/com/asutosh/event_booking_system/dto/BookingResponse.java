package com.asutosh.event_booking_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long bookingId;
    private Long eventId;
    private String eventTitle;
    private LocalDateTime bookingDate;
}
