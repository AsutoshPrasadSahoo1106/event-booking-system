package com.asutosh.event_booking_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private int totalSeats;
    private int availableSeats;
}
