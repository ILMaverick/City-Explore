package CONTENUTI;

import java.util.List;

public class MultimediaContentController {

    private MultimediaContentService multimediaContentService;

    public MultimediaContentController(MultimediaContentService multimediaContentService) {
        this.multimediaContentService = multimediaContentService;
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

    public void close() {
        multimediaContentService.close();
    }

}
