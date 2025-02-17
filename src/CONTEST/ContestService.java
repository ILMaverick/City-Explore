package CONTEST;

import USER.User;

import java.time.LocalDate;
import java.util.List;

public class ContestService {
    private ContestRepository contestRepository;

    public ContestService() {
        this.contestRepository = new InMemoryContestRepository();
    }

    public Contest createContest(String name, String description, User author, String rules, String goal, String prize, LocalDate deadline) {
        Contest contest = new Contest(name, description, author);
        contest.setRules(rules);
        contest.setGoal(goal);
        contest.setPrize(prize);
        contest.setDeadline(deadline);
        contestRepository.save(contest);
        return contest;
    }

    public void saveContest(Contest contest) {
        contestRepository.save(contest);
    }

    public List<Contest> getAllContest() {
        return contestRepository.findAll();
    }

    public Contest getContestById(int id) {
        return contestRepository.findById(id);
    }

}
