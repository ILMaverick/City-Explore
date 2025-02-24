package com.unicam.City_Explore.poi;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.validazione.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.notifica.NotificationListener;

@Service
public class POIService {
    private Scanner scanner;
    @Autowired
    private PointOfInterestFactory poiFactory;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;

    public POIService() {
    }

    //Inizializza dei Punti di Interesse
    public void initializer() {
    	
        createPOIFromUser("primo", "primo", 1, 1, POIType.Turismo);
        createPOIFromUser("secondo", "secondo", 2, 2, POIType.Alloggio);
        createPOIFromUser("terzo", "terzo", 3, 3, POIType.Natura);
    }

    public PointOfInterest createPOIFromUser(String name, String description, double lat, double lon, POIType type) {
    	User author = this.userService.getCurrentUser();
    	PointOfInterest poi = poiFactory.create(name, description, lat, lon, author, type);
        if(checkPOIData(poi)) {
            Optional<PointOfInterest> optionalExistPointOfInterest = poiRepository.findByNameAndLatitudeAndLongitude(name, lat, lon);
            if(optionalExistPointOfInterest.isPresent()) {
                PointOfInterest existPOI= optionalExistPointOfInterest.get();
                notificationListener.handleDuplicatePOI(existPOI, author);
                return existPOI;
            }
            poiRepository.save(poi);
            notificationListener.handleCreatePOI(poi);
            if (author.getRole() == Role.CONTRIBUTOR) {
                validationService.sendPOIForValidation(poi);
            } else {
                validationService.approvePOI(poi.getId());
            }
            
        } else {
            notificationListener.handleRefusePOI(poi);
        }
        return poi;
    }

    public PointOfInterest createPOIFromOSM(OverpassElement element, POIType type) {
    	User author = this.userService.getCurrentUser();
    	PointOfInterest poi = poiFactory.createFromOverpassElement(element, author, type);
        if(checkPOIData(poi)) {
            Optional<PointOfInterest> optionalExistPointOfInterest = poiRepository.findByNameAndLatitudeAndLongitude(poi.getName(), poi.getLatitude(), poi.getLongitude());
            if(optionalExistPointOfInterest.isPresent()) {
                PointOfInterest existPOI= optionalExistPointOfInterest.get();
                notificationListener.handleDuplicatePOI(existPOI, author);
                return existPOI;
            }
            poiRepository.save(poi);
            notificationListener.handleCreatePOI(poi);
            if (author.getRole() == Role.CONTRIBUTOR) {
                validationService.sendPOIForValidation(poi);
            } else {
                validationService.approvePOI(poi.getId());
            }
            return poi;
        } else {
            notificationListener.handleRefusePOI(poi);
        }
        return null;
    }

    public PointOfInterest updatePOI(int idPOI, String name, String description, String lat, String lon, String type) {
        PointOfInterest selectedPOI = getPOIById(idPOI);
        if (!description.isEmpty()) {
            selectedPOI.setDescription(description);
        }if (!name.isEmpty()) {
            selectedPOI.setName(name);
        }
        if (!lat.isEmpty()) {
            try {
                double latitude = Double.parseDouble(lat);
                selectedPOI.setLatitude(latitude);
            } catch (NumberFormatException e) {
            }
        }
        if (!lon.isEmpty()) {
            try {
                double longitude = Double.parseDouble(lon);
                selectedPOI.setLongitude(longitude);
            } catch (NumberFormatException e) {
            }
        }if (!type.isEmpty()) {
            try {
                POIType nuovoTipo = POIType.fromString(type);
                selectedPOI.setType(nuovoTipo);
            } catch (IllegalArgumentException e) {
            }
        }
        if(checkPOIData(selectedPOI)) {
            poiRepository.save(selectedPOI);
            notificationListener.handleUpdatePOI(selectedPOI);
        } else {
            notificationListener.handleRefusePOI(selectedPOI);
        }
        return getPOIById(selectedPOI.getId());
    }

    public List<PointOfInterest> searchPOIByName(String name) {
        if(name == null) return List.of();
        return poiRepository.searchByName(name);
    }

    public List<PointOfInterest> searchPOIByDescription(String description) {
        if(description == null) return List.of();
        return poiRepository.searchByDescription(description);
    }

    public List<PointOfInterest> searchPOIByType(POIType type) {
        if(type == null) return List.of();
        return poiRepository.searchByType(type);
    }

    public List<PointOfInterest> getAllPOIs() {
        return poiRepository.findAll();
    }

    public PointOfInterest getPOIById(int id) {
        return poiRepository.findById(id).orElse(null);
    }

    private boolean checkPOIData(PointOfInterest pointOfInterest) {
        if(pointOfInterest == null) return false;
        if(pointOfInterest.getName()==null) return false;
        if(pointOfInterest.getDescription()==null) return false;
        return pointOfInterest.getLatitude() != 0 || pointOfInterest.getLongitude() != 0;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
