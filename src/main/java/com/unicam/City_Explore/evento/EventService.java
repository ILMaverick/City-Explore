package com.unicam.City_Explore.evento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.notifica.NotificationListener;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private UserService userService;

    public EventService() {

    }

    public void initializer() {

        LocalDateTime time = LocalDateTime.of(2025,02,17, 15,48);
        createEvent("nome", "descrizione", "Raccolta fondi", "Gioco libero",
                "SilverSimonCorp", "Fantasy", "Giochi in presenza", 0.0, time, true);

    }


    public Event createEvent(String name, String description, String scope,
                             String activity, String organization, String theme, String category,
                             double price, LocalDateTime time, boolean isOpen) {

        User currentUser = userService.getCurrentUser();
        Event event = new Event(name, description, currentUser, scope, activity, organization, theme, category, price, time, isOpen);
        if (checkEventData(event)) {
            eventRepository.save(event);
            notificationListener.handleNewEvent(event);
            return event;
        } else {
            notificationListener.handleRefuseEvent(event);
        }
        return null;
    }


    public Event addEventToPOI(int idPOI, int idEvent) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        Event event = getEventById(idEvent);
        if (poi != null && event != null) {
            poi.getEvents().add(event);
            poiRepository.save(poi);
            event.getPointOfInterestList().add(poi);
            eventRepository.save(event);
            notificationListener.handleAddEventToPOI(event, poi);
            return event;
        } else {
            System.out.println("Punto di interesse o Evento non trovato.");
        }
        return null;
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(int idEvent, Event event) {
        Event eventSelected = eventRepository.findById(idEvent).orElse(null);
        if (eventSelected != null) {
            eventSelected.setName(event.getName());
            eventSelected.setDescription(event.getDescription());
            eventSelected.setScope(event.getScope());
            eventSelected.setActivity(event.getActivity());
            eventSelected.setOrganization(event.getOrganization());
            eventSelected.setTheme(event.getTheme());
            eventSelected.setCategory(event.getCategory());
            eventSelected.setTime(event.getTime());
            eventSelected.setPrice(event.getPrice());
            eventSelected.setIsOpen(eventSelected.getIsOpen());
            if(checkEventData(eventSelected)) {
                eventRepository.save(eventSelected);
                notificationListener.handleUpdateEvent(event);
                return eventSelected;
            } else {
                notificationListener.handleRefuseEvent(eventSelected);
            }
        }
        return null;
    }

    public List<PointOfInterest> getAllPoiFromEventRepository() {
        return poiRepository.findAll();
    }

    public List<Event> searchEventByName(String name) {
        if(name == null) return List.of();
        return eventRepository.searchByName(name);
    }

    public List<Event> searchEventByDescription(String description) {
        if(description == null) return List.of();
        return eventRepository.searchByDescription(description);
    }

    public void participateEvent(int idEvent, int idUser) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        User user = userService.getUserById(idUser);
        if(optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            user.getEventList().add(event);
            event.getParticipants().add(user);
            eventRepository.save(event);
            notificationListener.handleParticipationEvent(event, user);
        } else {
            throw new RuntimeException("Evento o Utente non trovato.");
        }
    }

    public void deleteParticipateEvent(int idEvent, int idUser, String reason) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        User user = userService.getUserById(idUser);
        if(optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            user.getEventList().remove(event);
            event.getParticipants().remove(user);
            eventRepository.save(event);
            notificationListener.handleDeleteParticipationEvent(event, user, reason);
        } else {
            throw new RuntimeException("Evento o Utente non trovato.");
        }
    }

    private boolean checkEventData(Event event) {
        if(event == null) return false;
        if(event.getName()==null) return false;
        if(event.getDescription()==null) return false;
        if(event.getScope()==null) return false;
        if(event.getActivity()==null) return false;
        if(event.getOrganization()==null) return false;
        if(event.getTheme()==null) return false;
        if(event.getCategory()==null) return false;
        return event.getTime()!=null;
    }



}

