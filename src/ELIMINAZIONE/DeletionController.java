package ELIMINAZIONE;

public class DeletionController {

    private DeletionService deletionService;

    public DeletionController(DeletionService deletionService) {
        this.deletionService = deletionService;
    }

    public void deletePOI() {
        deletionService.deletePOI();
    }

    public void close() {
        deletionService.close();
    }
}
