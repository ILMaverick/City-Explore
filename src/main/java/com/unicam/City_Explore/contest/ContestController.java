package com.unicam.City_Explore.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
public class ContestController {
    @Autowired
    private ContestService contestService;

    public ContestController() {
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
