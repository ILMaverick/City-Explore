package CONTEST;

import ELEMENT.AbstractElement;
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
                "\n, name='" + super.getName() + "\'" +
                "\n, description='" + super.getDescription() + "\'" +
                "\n, author='" + super.getAuthor() + "\'" +
                "\n, rules='" + rules + '\'' +
                "\n, goal='" + goal + '\'' +
                "\n, prize='" + prize + '\'' +
                "\n, participationContestList='" + participationContestList + '\'' +
                "\n, deadline='" + deadline +
                "\n}";
    }
}
