package com.asutosh.event_booking_system.service;


import com.asutosh.event_booking_system.entity.Event;
import com.asutosh.event_booking_system.exception.BadRequestException;
import com.asutosh.event_booking_system.exception.ResourceNotFoundException;
import com.asutosh.event_booking_system.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event createEvent(Event event) {

        // Business rule: availableSeats starts equal to totalSeats
        event.setAvailableSeats(event.getTotalSeats());

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
    }



    public Event updateEvent(Long eventId, Event updatedEvent) {

        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        int bookedSeats =
                existingEvent.getTotalSeats() - existingEvent.getAvailableSeats();

        // Prevent invalid seat reduction
        if (updatedEvent.getTotalSeats() < bookedSeats) {
            throw new BadRequestException(
                    "Total seats cannot be less than already booked seats");
        }

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setEventDate(updatedEvent.getEventDate());

        // Recalculate available seats safely
        int newAvailableSeats =
                updatedEvent.getTotalSeats() - bookedSeats;

        existingEvent.setTotalSeats(updatedEvent.getTotalSeats());
        existingEvent.setAvailableSeats(newAvailableSeats);

        return eventRepository.save(existingEvent);
    }

    // DELETE EVENT
    public void deleteEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        eventRepository.delete(event);
    }
}


