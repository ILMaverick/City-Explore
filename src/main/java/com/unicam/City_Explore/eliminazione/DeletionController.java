package com.unicam.City_Explore.eliminazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeletionController {
    @Autowired
    private DeletionService deletionService;

    public DeletionController() {
    }

    public void deletePOI() {
        deletionService.deletePOI();
    }

    public void deleteTour() {
        deletionService.deleteTour();
    }

    public void deleteContest() {
        deletionService.deleteContest();
    }

    public void deleteEvent() {
        deletionService.deleteEvent();
    }

    public void deleteMultimediaContent() {
        deletionService.deleteMultimediaContent();
    }

    public void close() {
        deletionService.close();
    }
}
