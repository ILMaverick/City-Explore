package com.unicam.City_Explore.contenuti;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.notifica.NotificationListener;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourRepository;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.User;
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
    private Scanner scanner;

    public MultimediaContentService() {
        scanner = new Scanner(System.in);
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

    public void createMultimediaContent() {
        System.out.println("=== Creazione di un Contenuto Multimediale ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci il formato: ");
        String formatString = scanner.nextLine();
        FormatFileEnum format = FormatFileEnum.formatFile(formatString);

        System.out.print("Inserisci la durata: ");
        float duration = scanner.nextFloat();

        System.out.print("Inserisci la dimensione: ");
        float dimension = scanner.nextFloat();

        System.out.print("Inserisci la risoluzione: ");
        float resolution = scanner.nextFloat();

        User currentUser = getCurrentUser();

        MultimediaContent multimediaContent = createMultimediaContent(name, description, currentUser, format, duration, dimension, resolution);

        System.out.println("Contenuto Multimediale creato in data: " + multimediaContent.getDataCreation());
        System.out.println(multimediaContent);
    }

    public void loadMultimediaContentToPOI() {

        System.out.println("=== Caricamento Contenuto Multimediale su un Punto di Interesse ===");

        System.out.print("Inserisci l'ID del Punto di Interesse: ");
        int idPOI = scanner.nextInt();

        System.out.print("Inserisci l'ID del Contenuto: ");
        int idMC = scanner.nextInt();

        PointOfInterest pointOfInterest = loadMultimediaContentToPOI(idPOI, idMC);

        System.out.println("Contenuto Multimediale caricato al Punto di Interesse: ");
        System.out.println(pointOfInterest);

    }

    public void searchMultimediaContentByName() {
        System.out.println("=== Ricerca Contenuti Multimediali tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<MultimediaContent> multimediaContentList = searchMultimediaContentByName(name);
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto Multimediale con questo nome.");
        } else {
            System.out.println("Elenco Contenuti Multimediali con il nome cercato:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    public void searchMultimediaContentByDescription() {
        System.out.println("=== Ricerca Contenuti Multimediali tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<MultimediaContent> multimediaContentList = searchMultimediaContentByDescription(description);
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto Multimediale con questa descrizione.");
        } else {
            System.out.println("Elenco Contenuti Multimediali con la descrizione cercata:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    public MultimediaContent createMultimediaContent(String name, String description, User author, FormatFileEnum format, float duration, float dimension, float resolution) {
        MultimediaContent multimediaContent = new MultimediaContent(name, description, author);
        if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTORIZED_CONTRIBUTOR || author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
            multimediaContent.setFormatFileEnum(format);
            multimediaContent.setDuration(duration);
            multimediaContent.setDimension(dimension);
            multimediaContent.setResolution(resolution);
            multimediaContent.setDataCreation(LocalDateTime.now());
            notificationListener.handleCreateMultimediaContent(multimediaContent);
            multimediaContentRepository.save(multimediaContent);
            return multimediaContent;
        } else {
            notificationListener.handleDenialPermission(author);

        }
        return null;
    }

    public PointOfInterest loadMultimediaContentToPOI(int idPOI, int idMC) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(poi != null & multimediaContent != null) {
            User author = multimediaContent.getAuthor();
            if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTORIZED_CONTRIBUTOR ||
                    author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
                multimediaContent.setPointOfInterest(poi);
                multimediaContentRepository.save(multimediaContent);
                poi.getMultimediaContentList().add(multimediaContent);
                notificationListener.handleLoadMultimediaContentToPOI(poi, multimediaContent);
                if(author.getRole() == Role.CONTRIBUTOR) {
                    validationService.sendMultimediaContentForValidation(multimediaContent);
                } else {
                    validationService.approveMultimediaContent(multimediaContent.getId());
                }
                poiRepository.save(poi);
                return poi;
            }
            else{
                notificationListener.handleDenialPermission(author);
            }
        }
        return null;
    }

    public Tour loadMultimediaContentToTour(int idTour, int idMC) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(tour != null & multimediaContent != null) {
            User author = multimediaContent.getAuthor();
            if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTORIZED_CONTRIBUTOR ||
                    author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
                multimediaContent.setTour(tour);
                multimediaContentRepository.save(multimediaContent);
                tour.getMultimediaContentList().add(multimediaContent);
                notificationListener.handleLoadMultimediaContentToTour(tour, multimediaContent);
                if(author.getRole() == Role.CONTRIBUTOR) {
                    validationService.sendMultimediaContentForValidation(multimediaContent);
                } else {
                    validationService.approveMultimediaContent(multimediaContent.getId());
                }
                tourRepository.save(tour);
                return tour;
            }
            else{
                notificationListener.handleDenialPermission(author);
            }
        }
        return null;
    }

    public MultimediaContent updateMultimediaContent(int idMC, MultimediaContent multimediaContent) {
        MultimediaContent multimediaContentSelected = getMultimediaContentById(idMC);
        if(multimediaContentSelected != null && multimediaContentSelected.getStatus()== Status.UPDATED) {
            User author = multimediaContent.getAuthor();
<<<<<<< Updated upstream
            if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTORIZED_CONTRIBUTOR ||
                    author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
=======
            if(author.getRole() == Role.CURATOR || author.getRole() == Role.ADMINISTRATOR) {
>>>>>>> Stashed changes
                multimediaContentSelected.setName(multimediaContent.getName());
                multimediaContentSelected.setDescription(multimediaContent.getDescription());
                multimediaContentSelected.setFormatFileEnum(multimediaContent.getFormatFileEnum());
                multimediaContentSelected.setDuration(multimediaContent.getDimension());
                multimediaContentSelected.setResolution(multimediaContent.getResolution());
                multimediaContentSelected.setDataCreation(LocalDateTime.now());
                notificationListener.handleUpdateMultimediaContent(multimediaContent);
                multimediaContentSelected.setStatus(Status.APPROVED);
                multimediaContentRepository.save(multimediaContentSelected);
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

    public List<MultimediaContent> searchMultimediaContentByName(String name) {
        return multimediaContentRepository.searchByName(name);
    }

    public List<MultimediaContent> searchMultimediaContentByDescription(String description) {
        return multimediaContentRepository.searchByDescription(description);
    }

    private User getCurrentUser() {
        User user = new User();
        user.setName("utente");
        user.setSurname("demo");
        user.setUsername("utente_demo");
        user.setEmail("utente_demo.mail@gmail.com");
        user.setPassword("1234567890");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }


}
