package com.unicam.City_Explore.contenuti;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MultimediaContentController {

    @Autowired
    private MultimediaContentService multimediaContentService;

    public MultimediaContentController() {
    }
    public void initializer() {
        multimediaContentService.initializer();
    }

    public void createMultimediaContent() {
        //return multimediaContentService.createMultimediaContent();
        multimediaContentService.createMultimediaContent();
    }

    public void loadMultimediaContentToPOI() {
        multimediaContentService.loadMultimediaContentToPOI();
    }

    public MultimediaContent updateMultimediaContent(int idMC, MultimediaContent multimediaContent) {
        return multimediaContentService.updateMultimediaContent(idMC, multimediaContent);
    }

    public void displayAllMultimediaContent() {
        List<MultimediaContent> multimediaContentList = multimediaContentService.getAllMultimediaContent();
        if(multimediaContentList.isEmpty()) {
            System.out.println("Nessun Contenuto Multimediale salvato.");
        } else {
            System.out.println("Elenco Contenuti Multimediali salvati:");
            for(MultimediaContent multimediaContent: multimediaContentList) {
                System.out.println(multimediaContent);
            }
        }
    }

    public void close() {
        multimediaContentService.close();
    }

}
