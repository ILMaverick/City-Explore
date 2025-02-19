package CONTENUTI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MultimediaContentController {

    @Autowired
    private MultimediaContentService multimediaContentService;

    public MultimediaContentController() {
    }
    public void initializer() {
        multimediaContentService.initializer();
    }

    public void createMultimediaContent() {
        multimediaContentService.createMultimediaContent();
    }

    public void loadMultimediaContentToPOI() {
        multimediaContentService.loadMultimediaContentToPOI();
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

    public void searchMultimediaContentByName() {
        multimediaContentService.searchMultimediaContentByName();
    }

    public void searchMultimediaContentByDescription() {
        multimediaContentService.searchMultimediaContentByDescription();
    }

    public void close() {
        multimediaContentService.close();
    }

}
