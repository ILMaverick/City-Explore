package com.speriamochemelacavo.City_Explore.CONTEST;

import java.util.List;

public class ContestController {
    private ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    public void initializer() {
        contestService.initializer();
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
    public void searchContestByName() {
        contestService.searchContestByName();
    }

    public void searchContestByDescription() {
        contestService.searchContestByDescription();
    }

    public void close() {
        contestService.close();
    }
}
