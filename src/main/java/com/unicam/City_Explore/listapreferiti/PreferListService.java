package com.unicam.City_Explore.listapreferiti;

import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestRepository;
import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.elementi.Element;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventRepository;
import com.unicam.City_Explore.notifica.NotificationListener;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourRepository;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferListService {

    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PreferListRepository preferListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationListener notificationListener;

    public PreferListService() {

    }

    public void savePOIToList(int idPOI, int idUser) {

        PointOfInterest poi = poiRepository.findById(idPOI).orElseThrow(() -> new RuntimeException("Punto di Interesse non trovato"));

        PreferList preferList = preferListRepository.findByUserId(idUser);
        User user = userRepository.findById(idUser).orElse(null);
        if(user != null && user.getRole() == Role.AUTHENTICATED_TOURIST) {
            if (preferList == null) {
                preferList = new PreferList();
                preferList.setUser(user);
                preferList.setElementList(new ArrayList<>());
            }

            preferList.getElementList().add(poi);
            preferListRepository.save(preferList);
        } else {
            notificationListener.handleDenialPermission(user);
        }
    }

    public void saveTourToList(int idTour, int idUser) {

        Tour tour = tourRepository.findById(idTour).orElseThrow(() -> new RuntimeException("Itinerario non trovato"));

        PreferList preferList = preferListRepository.findByUserId(idUser);
        User user = userRepository.findById(idUser).orElse(null);
        if(user != null && user.getRole() == Role.AUTHENTICATED_TOURIST) {
            if (preferList == null) {
                preferList = new PreferList();
                preferList.setUser(user);
                preferList.setElementList(new ArrayList<>());
            }

            preferList.getElementList().add(tour);
            preferListRepository.save(preferList);
        } else {
            notificationListener.handleDenialPermission(user);
        }
    }

    public void saveContestToList(int idContest, int idUser) {

        Contest contest = contestRepository.findById(idContest).orElseThrow(() -> new RuntimeException("Contest non trovato"));

        PreferList preferList = preferListRepository.findByUserId(idUser);
        User user = userRepository.findById(idUser).orElse(null);
        if(user != null && user.getRole() == Role.AUTHENTICATED_TOURIST) {
            if (preferList == null) {
                preferList = new PreferList();
                preferList.setUser(user);
                preferList.setElementList(new ArrayList<>());
            }

            preferList.getElementList().add(contest);
            preferListRepository.save(preferList);
        } else {
            notificationListener.handleDenialPermission(user);
        }
    }

    public void saveEventToList(int idEvent, int idUser) {

        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new RuntimeException("Evento non trovato"));

        PreferList preferList = preferListRepository.findByUserId(idUser);
        User user = userRepository.findById(idUser).orElse(null);
        if(user != null && user.getRole() == Role.AUTHENTICATED_TOURIST) {
            if (preferList == null) {
                preferList = new PreferList();
                preferList.setUser(user);
                preferList.setElementList(new ArrayList<>());
            }

            preferList.getElementList().add(event);
            preferListRepository.save(preferList);
        } else {
            notificationListener.handleDenialPermission(user);
        }
    }

    public List<AbstractElement> visualizePreferList(int idUser) {
        User user = userRepository.findById(idUser).orElse(null);

        if(user != null && user.getRole() == Role.AUTHENTICATED_TOURIST) {
            PreferList preferList = preferListRepository.findByUserId(idUser);
            if(preferList == null) {
                return new ArrayList<>();
            }
            return preferList.getElementList();
        } else {
            notificationListener.handleDenialPermission(user);
        }
        return null;
    }
}
