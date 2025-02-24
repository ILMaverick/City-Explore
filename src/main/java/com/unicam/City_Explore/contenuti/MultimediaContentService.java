package com.unicam.City_Explore.contenuti;

import java.time.LocalDateTime;
import java.util.List;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.notifica.NotificationListener;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourRepository;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.validazione.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultimediaContentService {
    @Autowired
    private MultimediaContentRepository multimediaContentRepository;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;

    public MultimediaContentService() {
    }

    public void initializer() {
        User user = new User();
        user.setName("Simone");
        user.setSurname("Stacchiotti");
        user.setUsername("SilverSimon");
        user.setEmail("simone.stacchiotti.email@gmail.com");
        user.setRole(Role.ADMINISTRATOR);

        MultimediaContent mc1 = new MultimediaContent("foto", "descrizione",user);
        MultimediaContent mc2 = new MultimediaContent("video", "secondo", user);
        MultimediaContent mc3 = new MultimediaContent("immagine", "terzo", user);

        mc1.setFormatFileEnum(FormatFileEnum.IMAGE);
        mc1.setDuration(1);
        mc1.setDimension(1);
        mc1.setResolution(100);
        multimediaContentRepository.save(mc1);

        mc2.setFormatFileEnum(FormatFileEnum.AUDIO);
        mc2.setDuration(2);
        mc2.setDimension(2);
        mc2.setResolution(200);
        multimediaContentRepository.save(mc2);

        mc3.setFormatFileEnum(FormatFileEnum.VIDEO);
        mc3.setDuration(3);
        mc3.setDimension(3);
        mc3.setResolution(400);
        multimediaContentRepository.save(mc3);

    }

    public MultimediaContent createMultimediaContent(String name, String description, FormatFileEnum format, float duration, float dimension, float resolution) {
        User author = userService.getCurrentUser();
        MultimediaContent multimediaContent = new MultimediaContent(name, description, author);
        multimediaContent.setFormatFileEnum(format);
        multimediaContent.setDuration(duration);
        multimediaContent.setDimension(dimension);
        multimediaContent.setResolution(resolution);
        multimediaContent.setDataCreation(LocalDateTime.now());
        multimediaContentRepository.save(multimediaContent);
        notificationListener.handleCreateMultimediaContent(multimediaContent);
        return multimediaContent;
    }

    public PointOfInterest loadMultimediaContentToPOI(int idPOI, int idMC) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(poi != null & multimediaContent != null) {
            User author = multimediaContent.getAuthor();
            multimediaContent.setAttachedElement(poi);
            multimediaContentRepository.save(multimediaContent);
            poi.getMultimediaContentList().add(multimediaContent);
            poiRepository.save(poi);
            notificationListener.handleLoadMultimediaContentToPOI(poi, multimediaContent);
            if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTHENTICATED_TOURIST) {
                validationService.sendMultimediaContentForValidation(multimediaContent);
            } else {
                validationService.approveMultimediaContent(multimediaContent.getId());
            }
            return poi;
        }
		return null;
    }

    public Tour loadMultimediaContentToTour(int idTour, int idMC) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(tour != null & multimediaContent != null) {
            User author = multimediaContent.getAuthor();
            multimediaContent.setAttachedElement(tour);
            multimediaContentRepository.save(multimediaContent);
            tour.getMultimediaContentList().add(multimediaContent);
            tourRepository.save(tour);
            notificationListener.handleLoadMultimediaContentToTour(tour, multimediaContent);
            if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTHENTICATED_TOURIST) {
                validationService.sendMultimediaContentForValidation(multimediaContent);
            } else {
                validationService.approveMultimediaContent(multimediaContent.getId());
            }
            return tour;
        }
        return null;
    }

    public MultimediaContent updateMultimediaContent(int idMC, MultimediaContent multimediaContent) {
        MultimediaContent multimediaContentSelected = getMultimediaContentById(idMC);
        if(multimediaContentSelected != null && multimediaContentSelected.getStatus()== Status.UPDATED) {
            User author = multimediaContent.getAuthor();
            if(author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
                multimediaContentSelected.setName(multimediaContent.getName());
                multimediaContentSelected.setDescription(multimediaContent.getDescription());
                multimediaContentSelected.setFormatFileEnum(multimediaContent.getFormatFileEnum());
                multimediaContentSelected.setDuration(multimediaContent.getDimension());
                multimediaContentSelected.setResolution(multimediaContent.getResolution());
                multimediaContentSelected.setDataCreation(LocalDateTime.now());
                multimediaContentSelected.setStatus(Status.APPROVED);
                multimediaContentRepository.save(multimediaContentSelected);
                notificationListener.handleUpdateMultimediaContent(multimediaContent);
                return multimediaContentSelected;
            } else{
                notificationListener.handleDenialPermission(author);
            }
        }
        return null;
    }

    public List<MultimediaContent> getAllMultimediaContent() {
        return multimediaContentRepository.findAll();
    }

    public MultimediaContent getMultimediaContentById(int id) {
        return multimediaContentRepository.findById(id).orElse(null);
    }
    
    public List<MultimediaContent> searchContentByName(String name) {
        if(name == null) return List.of();
        return multimediaContentRepository.searchByName(name);
    }

    public List<MultimediaContent> searchContentByDescription(String description) {
        if(description == null) return List.of();
        return multimediaContentRepository.searchByDescription(description);
    }


}
