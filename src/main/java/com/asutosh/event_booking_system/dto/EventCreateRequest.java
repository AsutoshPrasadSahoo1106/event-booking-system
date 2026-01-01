package com.asutosh.event_booking_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreateRequest {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime eventDate;

    @Min(1)
    private int totalSeats;
}
