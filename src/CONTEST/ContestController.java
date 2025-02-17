package CONTEST;

import java.util.List;

public class ContestController {
    private ContestService contestService;

    public ContestController() {
        this.contestService = new ContestService();
    }

    public void createContest() {
        contestService.createContest();
    }

    public void displayAllContest() {
        List<Contest> contestList = contestService.getAllContest();
        if(contestList.isEmpty()) {
            System.out.println("Nessun Contest salvato.");
        } else {
            System.out.println("Elenco Contest salvati:");
            for(Contest contest: contestList) {
                System.out.println(contest);
            }
        }
    }

    public void close() {
        contestService.close();
    }
}
