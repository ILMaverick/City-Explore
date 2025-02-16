package CONTENUTI;

import POI.InMemoryPOIRepository;
import USER.User;

import java.util.List;
import java.util.Scanner;

public class MultimediaContentController {

    //private Scanner scanner;
    private MultimediaContentService multimediaContentService;

    public MultimediaContentController() {
        //scanner = new Scanner(System.in);
        this.multimediaContentService = new MultimediaContentService();
    }
    public void loadMultimediaContent() {
        multimediaContentService.loadMultimediaContent();
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
/**
    private User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
 */
}
