package ELIMINAZIONE;

public class DeletionController {

    private final DeletionService deletionService;

    public DeletionController(DeletionService deletionService) {
        this.deletionService = deletionService;
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
