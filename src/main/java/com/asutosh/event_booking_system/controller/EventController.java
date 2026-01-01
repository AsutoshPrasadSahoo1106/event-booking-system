package com.asutosh.event_booking_system.controller;

import com.asutosh.event_booking_system.dto.EventCreateRequest;
import com.asutosh.event_booking_system.dto.EventResponse;
import com.asutosh.event_booking_system.dto.EventUpdateRequest;
import com.asutosh.event_booking_system.entity.Event;
import com.asutosh.event_booking_system.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;

    // CREATE EVENT (ORGANIZER)
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody EventCreateRequest request) {

        Event event = modelMapper.map(request, Event.class);
        Event savedEvent = eventService.createEvent(event);

        EventResponse response =
                modelMapper.map(savedEvent, EventResponse.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL EVENTS (PUBLIC)
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {

        List<EventResponse> responses = eventService.getAllEvents()
                .stream()
                .map(event -> modelMapper.map(event, EventResponse.class))
                .toList();

        return ResponseEntity.ok(responses);
    }

    // GET EVENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(
            @PathVariable Long id) {

        Event event = eventService.getEventById(id);
        EventResponse response =
                modelMapper.map(event, EventResponse.class);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody EventUpdateRequest request) {

        Event updatedEvent =
                modelMapper.map(request, Event.class);

        Event savedEvent =
                eventService.updateEvent(eventId, updatedEvent);

        EventResponse response =
                modelMapper.map(savedEvent, EventResponse.class);

        return ResponseEntity.ok(response);
    }

    // DELETE EVENT (ORGANIZER)
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long eventId) {

        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build(); // 204
    }
}
