package com.asutosh.event_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private LocalDateTime eventDate;

    private int totalSeats;

    private int availableSeats;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
