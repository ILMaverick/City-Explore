package CONTEST;

import INTERFACES.AbstractElement;
import USER.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contest extends AbstractElement {

    private String rules;
    private String goal;
    private String prize;
    private List<User> participationContestList = new ArrayList<>();
    private LocalDate deadline;
    public Contest(String name, String description, User author) {
        super(name, description, author);
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public List<User>  getParticipationContestList() {
        return participationContestList;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id='" + super.getId() + "\'" +
                ", name='" + super.getName() + "\'" +
                ", description='" + super.getDescription() + "\'" +
                ", author='" + super.getAuthor() + "\'" +
                ", rules='" + rules + '\'' +
                ", goal='" + goal + '\'' +
                ", prize='" + prize + '\'' +
                ", participationContestList='" + participationContestList + '\'' +
                ", deadline='" + deadline +
                '}';
    }
}
