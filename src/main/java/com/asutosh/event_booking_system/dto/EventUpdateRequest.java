package com.asutosh.event_booking_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventUpdateRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime eventDate;

    @Min(1)
    private int totalSeats;
}